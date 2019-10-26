
"""
Created on Mon Oct 14 2019 at 3:56 PM
@author: siddharth bharthulwar
"""



import aggregation as ag
import numpy as np
import cv2 as cv 
import numpy.ma as ma
import matplotlib.pyplot as plt
import rasterio as rio
import affine as affine


HIGH = "D:\\Documents\\School\\2019-20\\ISEF 2020\\HIGHAHN\\R_37HN1\\r_37hn1.tif"

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"

LAPTOPDSM = "C:\\Users\\siddh\\Documents\\DSMS\\R5_37FZ1\\r5_37fz1.tif"


SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"

'''
p1 = ag.TerrainGrid((DSM4, DSM5, DSM6, DSM7, DSM8, DSM9),(3, 2), 1)
a = p1.arrayThreshold(5, 5.5, cv.THRESH_TOZERO)


p2 = ag.TerrainGrid((SRTM1, SRTM2, SRTM3, SRTM4), (2, 2), 0)
plt.imshow(p2.values)

plt.imshow(a)
plt.show()

'''
yikes = ag.TerrainGrid((DSM4, DSM5, DSM6, DSM7, DSM8, DSM9), (3, 2), 1)
a = ag.tileDimensions(yikes.transformBounds)
print(a)
yikes.show('viridis', -20, 20)