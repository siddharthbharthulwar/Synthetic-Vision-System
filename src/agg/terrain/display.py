from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng
import time as time

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn2.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"


a = TerrainGrid((ehamr), (1,1) ,1)
a.arrayValues = a.arrayValues[0:3000, 7000:10000]
#a.classification(2.7, 1, 4, 300, 2, True, 40, 150)

c = cv.threshold(a.arrayValues, 2.4, 200, cv.THRESH_BINARY)[1].astype('uint8')


plt.imshow(a.arrayValues, vmin = -5, vmax = 60)
plt.imshow(ma.masked_values(c, 0), cmap = 'gist_gray_r')
plt.show()
'''
c = cv.erode(c, np.ones((5,5), np.uint8), iterations = 1)
c = cv.dilate(c, np.ones((5,5), np.uint8), iterations = 1)
'''
n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(c, connectivity=4)
print(n_labels)



