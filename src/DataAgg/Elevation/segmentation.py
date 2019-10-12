
"""
Created on Sat Sep 21 15:53:42 2019

@author: siddharth bharthulwar
"""



import aggregation as ag
import numpy as np
import cv2 as cv 
import matplotlib.pyplot as plt
from osgeo import gdal

"""
1, 2, 3
4, 5, 6
7, 8, 9

"""

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30HZ2\\r5_30hz2.tif"
DSM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30HZ1\\r5_30hz1.tif"
DSM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30GZ2\\r5_30gz2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"

LAPTOPDSM = "C:\\Users\\siddh\\Documents\\DSMS\\R5_37FZ1\\r5_37fz1.tif"

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

plt.imshow(imgTot)
plt.show()

#greyimg = cv.cvtColor(image, cv2.)
print(ag.getBounds(DSM9))