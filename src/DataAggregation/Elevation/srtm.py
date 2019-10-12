
"""
Created on Sat Oct 12 15:04:42 2019

@author: siddharth bharthulwar
"""



import aggregation as ag
import numpy as np
import cv2 as cv 
import matplotlib.pyplot as plt
from osgeo import gdal

SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"



DENDSM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n39_w105_1arc_v3.tif"
DENDSM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n39_w106_1arc_v3.tif"


img1 = ag.load(SRTM1, 0)
img2 = ag.load(SRTM2, 0)
img3 = ag.load(SRTM3, 0)
img4 = ag.load(SRTM4, 0)

hIm1 = np.hstack((img1, img2))
hIm2 = np.hstack((img3, img4))
imgTot = np.vstack((hIm1, hIm2))


den1 = ag.load(DENDSM1, 0)
den2 = ag.load(DENDSM2, 0)
dentotal = np.hstack((den2, den1))
plt.imshow(dentotal)
plt.show()

#greyimg = cv.cvtColor(image, cv2.)
print(ag.getBounds(DENDSM2))