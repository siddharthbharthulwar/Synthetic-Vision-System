from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"


a = TerrainGrid((rd0), (1,1), 1)
a.arrayValues = a.arrayValues[10000:12000, 7000:9000]

c = cv.threshold(a.arrayValues, 5, 200, cv.THRESH_BINARY)[1].astype('uint8')

b = a.erodilate(3.6, np.ones((2,2), np.uint8) , 1).astype('uint8')

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity=8)



contours, hierarchy = cv.findContours(b, cv.RETR_LIST, cv.CHAIN_APPROX_SIMPLE)[-2:]
idx = 0
for cnt in contours:
    idx +=1 
    x, y, w, h = cv.boundingRect(cnt)
    roi = a.arrayValues[y:y+h, x:x+w]
    color = (rng.randint(0, 256), rng.randint(0, 256), rng.randint(0, 256))
    cv.drawContours(a.arrayValues, [cnt], 0, color, -1)

cv.imshow('image', a.arrayValues)
cv.waitKey(0)


