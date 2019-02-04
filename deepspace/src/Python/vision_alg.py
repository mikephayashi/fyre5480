#https://www.pyimagesearch.com/2014/09/29/finding-brightest-spot-image-using-python-opencv/
# import the necessary packages
import numpy as np
import argparse
import cv2

# construct the argument parse and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-i", "--image", help = "path to the image file")
ap.add_argument("-r", "--radius", type = int,
	help = "radius of Gaussian blur; must be odd")
args = vars(ap.parse_args())

# load the image and convert it to grayscale
image = cv2.imread(args["image"])
height, width, channels = image.shape
orig = image.copy()
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

# the area of the image with the largest intensity value
(minVal, maxVal, minLoc, maxLoc) = cv2.minMaxLoc(gray)
cv2.circle(image, maxLoc, 5, (255, 0, 0), 2)

print("Width: ", width, " Height: ", height)
print("Circle", " minVal: ", minVal, " maxVal: ", maxVal, " minLoc: ", minLoc, " maxLoc: ", maxLoc)

# display the results of the naive attempt
cv2.imshow("Naive", image)
cv2.waitKey(0)
