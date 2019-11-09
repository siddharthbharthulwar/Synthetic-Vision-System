
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



p1 = ag.TerrainGrid((SRTM3), (1, 1), 0)
plt.imshow(p1.arrayThreshold(3,5.5, cv.THRESH_TOZERO))
plt.show()