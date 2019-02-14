#!/usr/bin/env python3
#----------------------------------------------------------------------------
# Run this one - main file to upload to Raspberry Pi
#----------------------------------------------------------------------------

import json
import time
import timeit
import sys
import cv2
import numpy as np
from copy import deepcopy

from cscore import CameraServer, VideoSource, UsbCamera, MjpegServer
from networktables import NetworkTablesInstance

configFile = "/boot/frc.json"

class CameraConfig: pass

team = "5480"
server = "roborio-5480-frc.local"
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

    return camera, inst

"""https://robotpy.readthedocs.io/en/stable/vision/code.html"""
def imageProcessing(cam):
    # cs = CameraServer.getInstance()
    # cs.enableLogging()
    # print("log enabled")
    #
    # # Capture from the first USB Camera on the system
    # camera = cs.startAutomaticCapture()
    # print("automatic capture started")
    # camera.setResolution(320, 240)
    # print("resolution set")

    cs = cam

    # Get a CvSink. This will capture images from the camera
    cvSink = cs.getVideo()
    # print("got video")

    # (optional) Setup a CvSource. This will send images back to the Dashboard
    outputStream = cs.putVideo("Name", 320, 240)
    # print("got output stream")

    # Allocating new images is very expensive, always try to preallocate
    img = np.zeros(shape=(240, 320, 3), dtype=np.uint8)
    # print("allocated image")

    while True:
        # Tell the CvSink to grab a frame from the camera and put it
        # in the source image.  If there is an error notify the output
        # print("infinite loop started")
        time, img = cvSink.grabFrame(img)
        # print("got time and image")
        if time == 0:
            # Send the output the error.
            outputStream.notifyError(cvSink.getError())
            print("got error")
            # skip the rest of the current iteration
            continue



        #---------------------------
        # Image Process Logic [Start]
        #---------------------------

        start = timeit.default_timer()

        # load the image, convert it to grayscale, and blur it
        image = img
        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        blurred = cv2.GaussianBlur(gray, (11, 11), 0)

        # threshold the image to reveal light regions in the
        # blurred image
        thresh = cv2.threshold(blurred, 200, 255, cv2.THRESH_BINARY)[1] ### When Testing, measure light intensity that is reflected +++ remove outliers ###

        # perform a series of erosions and dilations to remove
        # any small blobs of noise from the thresholded image
        thresh = cv2.erode(thresh, None, iterations=2)
        thresh = cv2.dilate(thresh, None, iterations=4)

        #Preparing coordinates for K kmeans xC = x-coordinates
        f2, f1 = np.where(thresh == 255) ####CX, where not found####
        rows, columns = thresh.shape

        # print("Printing lengths")
        # print(len(f2))
        # print(len(f1))

        if f1!=None and f2!=None and len(f1) != 0 and len(f2) != 0:

            #Taking sample of data to redue time
            # f2 = np.random.choice(a=f2,size=round(f2.size/2))
            # f1 = np.random.choice(a=f1,size=round(f1.size/2))

            #Orient Coordinates
            def orient_x_coords(a):
                return rows-a
            def orient_y_coords(a):
                return columns-a
            f2 = np.apply_along_axis(orient_x_coords, 0, f2) #Doesn't need this one
            # f1 = np.apply_along_axis(orient_y_coords, 0, f1)

            #Prints
            np.set_printoptions(threshold=np.nan)
            # print("Rows: ", rows, " Columns: ", columns)
            # print("xC Length", len(f1))
            # print("xC", xC)
            # print("yC Length", len(f2))
            # print("yC", yC)
            # print(zip(xC,yC))

            #-----------------------
            #K means cluster [Start]
            #-----------------------

            # Getting the values and plotting it
            X = np.array(list(zip(f1, f2)))

            # Euclidean Distance Caculator
            def dist(a, b, ax=1):
                return np.linalg.norm(a - b, axis=ax)

            # Number of clusters
            k = 2
            # X coordinates of random centroids
            C_x = np.random.randint(0, np.max(X)-20, size=k)
            # Y coordinates of random centroids
            C_y = np.random.randint(0, np.max(X)-20, size=k)
            C = np.array(list(zip(C_x, C_y)), dtype=np.float32)
#            print("Centroids", C)

            # To store the value of centroids when it updates
            C_old = np.zeros(C.shape)
            # Cluster Lables(0, 1, 2)
            clusters = np.zeros(len(X))
            # Error func. - Distance between new centroids and old centroids
            error = dist(C, C_old, None)
            # print("Error: ", error)

            leftArea = 0
            rightArea = 0

            # Loop will run till the error becomes zero
            while error != 0:
                # Assigning each value to its closest cluster
                for i in range(len(X)):
                    distances = dist(X[i], C)
                    cluster = np.argmin(distances)
                    clusters[i] = cluster
                    # print(cluster) #Prints 0s
                # Storing the old centroid values
                C_old = deepcopy(C)
                # print(k)
                # Finding the new centroids by taking the average value
                for i in range(k):
                    points = [X[j] for j in range(len(X)) if clusters[j] == i]
                    # print("Points", points) ###Leave this in or it gives a warning and breaks, reason uknown???ß
                    C[i] = np.mean(points, axis=0)
                    if i == 0:
                        leftArea = len(points)
                    elif i == 1:
                        rightArea = len(points)
                #   print(C)
                error = dist(C, C_old, None)
                if error != isinstance(error, int):
                    break
                print(error)

            centroid_x = 0
            centroid_y = 0

            # Plotting along with the Centroids
            for i in range(k):
                    points = np.array([X[j] for j in range(len(X)) if clusters[j] == i])
                    centroid_x = C[:, 0]
                    centroid_y = C[:, 1]

            stop = timeit.default_timer()
            print('Time: ', stop - start)
            print("Centroid", " x: ", centroid_x, " y: ", centroid_y)
            print("left area: ", leftArea, " right area: ", rightArea)

            #---------------------------
            # Image Process Logic [End]
            #---------------------------

            # sd = ntinst.getTable('datatable')
            # sd.putNumber('X', centroid_x)
            # sd.putNumber('Y', centroid_y)
            # sd.putNumber('leftArea', leftArea)
            # sd.putNumber('rightArea', rightArea)

            # (optional) send some image back to the dashboard
            # outputStream.putFrame(img)

if __name__ == "__main__":
    if len(sys.argv) >= 2:
        configFile = sys.argv[1]

    # read configuration
    if not readConfig():
        sys.exit(1)
    
    # start NetworkTables
    global ntinst
    ntinst = NetworkTablesInstance.getDefault()
    if server:
        print("Setting up NetworkTables server")
        ntinst.startServer()
    else:
        print("Setting up NetworkTables client for team {}".format(team))
        ntinst.startClientTeam(team)
    
    # start cameras
    cameras = []

    camera, inst = startCamera(cameraConfigs[0])

    # for cameraConfig in cameraConfigs:
    #     camera, inst = startCamera(cameraConfig)
    #     cameras.append(camera)

    print("Camera started")

    #image Processing
    imageProcessing(inst)

    print("Image Processed")

    # # loop forever
    # while True:
    #     time.sleep(20)
