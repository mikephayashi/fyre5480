### Tutorial Used
### https://www.pyimagesearch.com/2016/10/31/detecting-multiple-bright-spots-in-an-image-with-python-and-opencv/

"""
Make More Efficient:

Sample Image = AdobeStock.jpg, k = 1
Time(seconds), Centroid(y,x)
39396 Pixels = 1.419823949000147 seconds, [[1187.  455.]]
Sample/2
1. 1.038249075999829, [[ 586. 1071.]]
2. 0.9493230450002557, [[515. 409.]]

"""


import timeit

start = timeit.default_timer()

# import the necessary packages
import numpy as np
import argparse
import imutils
import cv2
import sys
from copy import deepcopy
from matplotlib import pyplot as plt
plt.rcParams['figure.figsize'] = (16, 9)
plt.style.use('ggplot')

# construct the argument parse and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-i", "--image", required=True,
	help="path to the image file")
args = vars(ap.parse_args())

# load the image, convert it to grayscale, and blur it
image = cv2.imread(args["image"])
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
print("Rows: ", rows, " Columns: ", columns)
print("xC Length", len(f1))
# print("xC", xC)
print("yC Length", len(f2))
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
k = 1
# X coordinates of random centroids
C_x = np.random.randint(0, np.max(X)-20, size=k)
# Y coordinates of random centroids
C_y = np.random.randint(0, np.max(X)-20, size=k)
C = np.array(list(zip(C_x, C_y)), dtype=np.float32)
print("Centroids", C)

# Plot before k means cluster
# plt.scatter(f1, f2, c='#050505', s=7)
# plt.scatter(C_x, C_y, marker='*', s=200, c='g')
# plt.show()

# To store the value of centroids when it updates
C_old = np.zeros(C.shape)
# Cluster Lables(0, 1, 2)
clusters = np.zeros(len(X))
# Error func. - Distance between new centroids and old centroids
error = dist(C, C_old, None)
# Loop will run till the error becomes zero
while error != 0:
    # Assigning each value to its closest cluster
    for i in range(len(X)):
        distances = dist(X[i], C)
        cluster = np.argmin(distances)
        clusters[i] = cluster
    # Storing the old centroid values
    C_old = deepcopy(C)
    # Finding the new centroids by taking the average value
    for i in range(k):
        points = [X[j] for j in range(len(X)) if clusters[j] == i]
        # print("Points", points) ###Leave this in or it gives a warning and breaks, reason uknown???ß
        C[i] = np.mean(points, axis=0)
    error = dist(C, C_old, None)

# Plotting along with the Centroids
colors = ['r', 'g', 'b', 'y', 'c', 'm']
fig, ax = plt.subplots()
for i in range(k):
        points = np.array([X[j] for j in range(len(X)) if clusters[j] == i])
        ax.scatter(points[:, 0], points[:, 1], s=7, c=colors[i])
ax.scatter(C[:, 0], C[:, 1], marker='*', s=200, c='#050505')

stop = timeit.default_timer()

print('Time: ', stop - start)

#Show Plot
plt.show()
