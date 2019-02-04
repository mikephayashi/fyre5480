#https://www.geeksforgeeks.org/detection-specific-colorblue-using-opencv-python/

# Python program for Detection of a
# specific color(blue here) using OpenCV with Python
import cv2
import argparse
import numpy as np
import imutils
from imutils import contours
from skimage import measure

# construct the argument parse and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-i", "--image", required=True,
	help="path to the image file")
args = vars(ap.parse_args())
frame = cv2.imread(args["image"])

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
labels = measure.label(mask, neighbors=8, background=0)
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
cnts = imutils.grab_contours(cnts)
cnts = contours.sort_contours(cnts)[0]

cX = 0
cY = 0
radius = 0

#x center, y center, radius
target_Cicle = (width/2, height/2, 40)

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

#Prints
"""cv2.imshow('mask',mask)
cv2.waitKey(0)
cv2.imshow('res',res)
cv2.waitKey(0)
cv2.imshow("cnts", frame)
cv2.waitKey(0)"""
print("Size: ", width, " by ", height)
np.set_printoptions(threshold=np.inf)
#print(np.nonzero(mask))
print("Circle: ", "cX: ", cX, " cy: ", cY, " radius: ", radius)
print(" Relative Position", "x: ", cX/width, "y: ", cY/height)
print("Percent Deviation", "x: ", cX/target_Cicle[0], "y: ", cY/target_Cicle[1], "radius: ", radius/target_Cicle[2])
