from terraingrid import TerrainGrid
import numpy as np 
import cv2 as cv 
from skimage.io import imread
import matplotlib.pyplot as plt
from skimage.color import rgb2gray
import scipy.ndimage as ndi
import numpy.ma as ma
import random as rng
import time as time
from scipy import signal as sig


import tsp


def erodilate(val, kernel, iterate):
    img_erosion = cv.erode(val, np.ones((kernel, kernel), np.uint8), iterations = iterate)
    img_errdil = cv.dilate(np.array(img_erosion), np.ones((kernel, kernel), np.uint8), iterations = iterate)
    return img_errdil

def gradient_x(imggray):
    ##Sobel operator kernels.
    kernel_x = np.array([[-1, 0, 1],[-2, 0, 2],[-1, 0, 1]])
    return sig.convolve2d(imggray, kernel_x, mode='same')
def gradient_y(imggray):
    kernel_y = np.array([[1, 2, 1], [0, 0, 0], [-1, -2, -1]])
    return sig.convolve2d(imggray, kernel_y, mode='same')

def harrisresponse(imggray):
    I_x = gradient_x(imggray)
    I_y = gradient_y(imggray)
    Ixx = ndi.gaussian_filter(I_x**2, sigma=1)
    Ixy = ndi.gaussian_filter(I_y*I_x, sigma=1)
    Iyy = ndi.gaussian_filter(I_y**2, sigma=1)
    k = 0.05
    # determinant
    detA = Ixx * Iyy - Ixy ** 2
    # trace
    traceA = Ixx + Iyy
        
    harris_response = (detA - k * traceA ** 2) 
    return harris_response




rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn1.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"


path = r"C:\Users\siddh\Documents\DSMS\R_25GN1\r_25gn1.tif"

a = TerrainGrid((rd0), (1,1), 1)
a.show(-5, 50)


c = cv.threshold(a.arrayValues, 2.4, 200, cv.THRESH_BINARY)[1].astype('uint8')
c = cv.erode(c, np.ones((2,2), np.uint8), iterations = 1)

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(c, connectivity=4)
print(n_labels)

variance = []

maxharris = []

harris = harrisresponse(c)
plt.imshow(harris)
plt.show()

buildings = []
vegetation = [] 

inbuildings = []
invegetation = []
histogram = []
above = []
unique = np.delete(np.unique(labels), 0)


incount = 0
bray = np.gradient(a.arrayValues)[0]
plt.imshow(bray, vmin = -2, vmax = 2)
plt.show()

blda = np.zeros(labels.shape, np.uint8)
vega = np.zeros(labels.shape, np.uint8)
start = time.time()


for i in unique:
    print(i)
    if (stats[i, 4] > 600):

        org = ma.masked_not_equal(labels, i) / i
        var = np.std(org * bray)
        variance.append(var)
        har = (org * harris)
        maxharris.append(har.mean())
        histogram.append(var)
        if (var > 1.75):
            vegetation.append(i)
            invegetation.append(incount)
            incount +=1 
            vega = np.add(vega, org.filled(0))
        else:
            
            buildings.append(i)
            inbuildings.append(incount)
            incount +=1
            blda = np.add(blda,org.filled(0))


plt.scatter(variance, maxharris)
plt.show()


