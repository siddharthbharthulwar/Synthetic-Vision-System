
"""
Created on Mon Oct 14 2019 at 3:56 PM
@author: siddharth bharthulwar
"""



import aggregation as ag
import numpy as np
import cv2 as cv 
import numpy.ma as ma
import matplotlib.pyplot as plt


DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"

LAPTOPDSM = "C:\\Users\\siddh\\Documents\\DSMS\\R5_37FZ1\\r5_37fz1.tif"

def gridThreshold(terrainGrid, treshVal, max, treshType):
    ret, tresh1 = cv.threshold(terrainGrid.getValues(), treshVal, max, treshType)
    arraya = ma.masked_values(tresh1, 0)
    return arraya

'''
def thresholdShow(thresholdedArray, normalGrid, vmin, vmax, opacity):
    plt.imshow(nor)
'''


p1 = ag.TerrainGrid((DSM4, DSM5, DSM6, DSM7, DSM8, DSM9), (3, 2), 1)
plt.imshow(p1.getValues(), vmin = -10, vmax = 100)

plt.imshow(gridThreshold(p1, 5, 10, cv.THRESH_BINARY), cmap = 'binary', alpha = 0.5)

plt.show()