import json
import time
import sys
import cv2
import argparse
import numpy as np
from scikitImage.skimage.measure._label import label

from cscore import CameraServer, VideoSource, UsbCamera, MjpegServer
from networktables import NetworkTablesInstance

#   JSON format:
#   {
#       "team": <team number>,
#       "ntmode": <"client" or "server", "client" if unspecified>
#       "cameras": [
#           {
#               "name": <camera name>
#               "path": <path, e.g. "/dev/video0">
#               "pixel format": <"MJPEG", "YUYV", etc>   // optional
#               "width": <video mode width>              // optional
#               "height": <video mode height>            // optional
#               "fps": <video mode fps>                  // optional
#               "brightness": <percentage brightness>    // optional
#               "white balance": <"auto", "hold", value> // optional
#               "exposure": <"auto", "hold", value>      // optional
#               "properties": [                          // optional
#                   {
#                       "name": <property name>
#                       "value": <property value>
#                   }
#               ],
#               "stream": {                              // optional
#                   "properties": [
#                       {
#                           "name": <stream property name>
#                           "value": <stream property value>
#                       }
#                   ]
#               }
#           }
#       ]
#   }

configFile = "/boot/frc.json"

class CameraConfig: pass

team = None
server = False
cameraConfigs = []

"""Report parse error."""
def parseError(str):
    print("config error in '" + configFile + "': " + str, file=sys.stderr)

"""Read single camera configuration."""
def readCameraConfig(config):
    cam = CameraConfig()

    # name
    try:
        cam.name = config["name"]
    except KeyError:
        parseError("could not read camera name")
        return False

    # path
    try:
        cam.path = config["path"]
    except KeyError:
        parseError("camera '{}': could not read path".format(cam.name))
        return False

    # stream properties
    cam.streamConfig = config.get("stream")

    cam.config = config

    cameraConfigs.append(cam)
    return True

"""Read configuration file."""
def readConfig():
    global team
    global server

    # parse file
    try:
        with open(configFile, "rt") as f:
            j = json.load(f)
    except OSError as err:
        print("could not open '{}': {}".format(configFile, err), file=sys.stderr)
        return False

    # top level must be an object
    if not isinstance(j, dict):
        parseError("must be JSON object")
        return False

    # team number
    try:
        team = j["team"]
    except KeyError:
        parseError("could not read team number")
        return False

    # ntmode (optional)
    if "ntmode" in j:
        str = j["ntmode"]
        if str.lower() == "client":
            server = False
        elif str.lower() == "server":
            server = True
        else:
            parseError("could not understand ntmode value '{}'".format(str))

    # cameras
    try:
        cameras = j["cameras"]
    except KeyError:
        parseError("could not read cameras")
        return False
    for camera in cameras:
        if not readCameraConfig(camera):
            return False

    return True

"""Start running the camera."""
def startCamera(config):
    print("Starting camera '{}' on {}".format(config.name, config.path))
    inst = CameraServer.getInstance()
    camera = UsbCamera(config.name, config.path)
    server = inst.startAutomaticCapture(camera=camera, return_server=True)

    camera.setConfigJson(json.dumps(config.config))
    camera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen)

    if config.streamConfig is not None:
        server.setConfigJson(json.dumps(config.streamConfig))

    return camera

def grab_contours(cnts):
    # if the length the contours tuple returned by cv2.findContours
    # is '2' then we are using either OpenCV v2.4, v4-beta, or
    # v4-official
    if len(cnts) == 2:
        cnts = cnts[0]

    # if the length of the contours tuple is '3' then we are using
    # either OpenCV v3, v4-pre, or v4-alpha
    elif len(cnts) == 3:
        cnts = cnts[1]

    # otherwise OpenCV has changed their cv2.findContours return
    # signature yet again and I have no idea WTH is going on
    else:
        raise Exception(("Contours tuple must have length 2 or 3, "
            "otherwise OpenCV changed their cv2.findContours return "
            "signature yet again. Refer to OpenCV's documentation "
            "in that case"))

    # return the actual contours array
    return cnts

def sort_contours(cnts, method="left-to-right"):
    # initialize the reverse flag and sort index
    reverse = False
    i = 0

    # handle if we need to sort in reverse
    if method == "right-to-left" or method == "bottom-to-top":
        reverse = True

    # handle if we are sorting against the y-coordinate rather than
    # the x-coordinate of the bounding box
    if method == "top-to-bottom" or method == "bottom-to-top":
        i = 1

    # construct the list of bounding boxes and sort them from top to
    # bottom
    boundingBoxes = [cv2.boundingRect(c) for c in cnts]
    (cnts, boundingBoxes) = zip(*sorted(zip(cnts, boundingBoxes),
                                        key=lambda b: b[1][i], reverse=reverse))

    # return the list of sorted contours and bounding boxes
    return cnts, boundingBoxes

"""https://robotpy.readthedocs.io/en/stable/vision/code.html"""
def imageProcessing():
    cs = CameraServer.getInstance()
    cs.enableLogging()

    # Capture from the first USB Camera on the system
    camera = cs.startAutomaticCapture()
    camera.setResolution(320, 240)

    # Get a CvSink. This will capture images from the camera
    cvSink = cs.getVideo()

    # (optional) Setup a CvSource. This will send images back to the Dashboard
    outputStream = cs.putVideo("Name", 320, 240)

    # Allocating new images is very expensive, always try to preallocate
    img = np.zeros(shape=(240, 320, 3), dtype=np.uint8)

    while True:
        # Tell the CvSink to grab a frame from the camera and put it
        # in the source image.  If there is an error notify the output.
        time, img = cvSink.grabFrame(img)
        if time == 0:
            # Send the output the error.
            outputStream.notifyError(cvSink.getError());
            # skip the rest of the current iteration
            continue


        #---------------------------
        # Image Process Logic [Start]
        #---------------------------

        #image to Frame
        frame = img

        # Converts images from RGB to HSV
        hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
        lower_green = np.array([0,200,0])
        upper_green = np.array([255,255,255])

        # Here we are defining range of greencolor in HSV
        # This creates a mask of green coloured
        # objects found in the frame.
        mask = cv2.inRange(hsv, lower_green, upper_green) #mask object -> nd array
        height, width, channels = frame.shape

        # perform a connected component analysis on the thresholded
        # image, then initialize a mask to store only the "large"
        # components
        labels = label(mask, neighbors=8, background=0) #should be measure.label
        # measure.labels #http://scikit-image.org/docs/dev/api/skimage.measure.html#skimage.measure.label
        mask = np.zeros(mask.shape, dtype="uint8")
        #shape #https://docs.opencv.org/3.0-beta/doc/py_tutorials/py_core/py_basic_ops/py_basic_ops.html
        #np.zeros https://docs.scipy.org/doc/numpy-1.15.0/reference/generated/numpy.zeros.html

        # loop over the unique components
        for label in np.unique(labels):
        	# if this is the background label, ignore it
        	if label == 0:
        		continue

        	# otherwise, construct the label mask and count the
        	# number of pixels
        	labelMask = np.zeros(mask.shape, dtype="uint8")
        	labelMask[labels == label] = 255
        	numPixels = cv2.countNonZero(labelMask)

        	# if the number of pixels in the component is sufficiently
        	# large, then add it to our mask of "large blobs"
        	if numPixels > 300:
        		mask = cv2.add(mask, labelMask)

        # find the contours in the mask, then sort them from left to
        # right
        cnts = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL,
        	cv2.CHAIN_APPROX_SIMPLE)
        cnts = grab_contours(cnts) #imutilis.grab_contours
        cnts = sort_contours(cnts)[0] #imutilis.sort_contours

        cX = 0
        cY = 0
        radius = 0

        # loop over the contours
        for (i, c) in enumerate(cnts):
        	# draw the bright spot on the image
        	(x, y, w, h) = cv2.boundingRect(c)
        	((cX, cY), radius) = cv2.minEnclosingCircle(c)
        	cv2.circle(frame, (int(cX), int(cY)), int(radius),
        		(0, 0, 255), 3)
        	cv2.putText(frame, "#{}".format(i + 1), (x, y - 15),
        		cv2.FONT_HERSHEY_SIMPLEX, 0.45, (0, 0, 255), 2)

        # The bitwise and of the frame and mask is done so
        # that only the green coloured objects are highlighted
        # and stored in res
        res = cv2.bitwise_and(frame,frame, mask= mask)


        #---------------------------
        # Image Process Logic [End]
        #---------------------------

        #x center, y center, radius
        target_Cicle = (width/2, height/2, 40)
        print("Circle: ", "cX: ", cX, " cy: ", cY, " radius: ", radius)
        print(" Relative Position", "x: ", cX/width, "y: ", cY/height)
        print("Percent Deviation", "x: ", cX/target_Cicle[0], "y: ", cY/target_Cicle[1], "radius: ", radius/target_Cicle[2])

        # As a client to connect to a robot
        NetworkTables.initialize(server='roborio-5480-frc.local')
        #https://robotpy.readthedocs.io/projects/pynetworktables/en/stable/api.html
        sd = NetworkTables.getTable('SmartDashboard')
        sd.putNumber('X', cX)
        sd.putNumber('Y', cY)
        sd.putNumber('Radius', radius)
        sd.putNumber("Frame Width", width)
        sd.putNumber("Frame Height", height)
        #otherNumber = sd.getNumber('otherNumber')

        # (optional) send some image back to the dashboard
        outputStream.putFrame(img)

if __name__ == "__main__":
    if len(sys.argv) >= 2:
        configFile = sys.argv[1]

    # read configuration
    if not readConfig():
        sys.exit(1)

    # start NetworkTables
    ntinst = NetworkTablesInstance.getDefault()
    if server:
        print("Setting up NetworkTables server")
        ntinst.startServer()
    else:
        print("Setting up NetworkTables client for team {}".format(team))
        ntinst.startClientTeam(team)

    # start cameras
    cameras = []
    for cameraConfig in cameraConfigs:
        cameras.append(startCamera(cameraConfig))

    #image Processing
    imageProcessing()

    # loop forever
    while True:
        time.sleep(10)
