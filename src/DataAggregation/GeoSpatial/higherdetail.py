
"""
Created on Sat Sep 21 15:53:42 2019

@author: siddharth bharthulwar
"""



import aggregation as ag
import numpy as np
import cv2 as cv 
import numpy.ma as ma
import matplotlib.pyplot as plt

HIGH = "D:\\Documents\\School\\2019-20\\ISEF 2020\\HIGHAHN\\R_37HN1\\r_37hn1.tif"
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


p1 = ag.TerrainGrid((DSM7), (1, 1), 1)
yikes = p1.getValues()
yikes = yikes.astype('uint8')
#th3 = cv.adaptiveThreshold(p1.getValues(), 255, cv.ADAPTIVE_THRESH_GAUSSIAN_C, cv.THRESH_BINARY, 4, 2)

print(yikes.dtype)
yik = cv.adaptiveThreshold(yikes, 100, cv.ADAPTIVE_THRESH_GAUSSIAN_C, cv.THRESH_BINARY, 31, 2)
yik = yik.astype('float32')
yika = ma.masked_values(yik, 100)
plt.imshow(p1.getValues(), vmin = -10, vmax = 50, alpha = 0.9)

plt.imshow(yika, cmap = 'gist_gray_r', alpha = 0.5)

plt.show()
#plt.imshow(gridThreshold(p1, 5, 10, cv.THRESH_BINARY), cmap = 'binary', alpha = 0.2)
