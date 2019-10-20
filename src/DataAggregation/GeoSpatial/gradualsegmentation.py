
"""
Created on Sat Oct 19 at 10 29 PM

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


def viewImage(image):
    cv.namedWindow('Display', cv.WINDOW_NORMAL)
    cv.imshow('Display', image)
    cv.waitKey(0)
    cv.destroyAllWindows()
def grayscale_17_levels (image):
    high = 100
    while(1):  
        low = high - 15
        col_to_be_changed_low = np.array([low])
        col_to_be_changed_high = np.array([high])
        curr_mask = cv.inRange(image, col_to_be_changed_low,col_to_be_changed_high)
        image[curr_mask > 0] = (high)
        high -= 15
        if(low == 0 ):
            break


imageyug = ag.TerrainGrid((DSM7, DSM8), (2, 1), 1)
imageyog = imageyug.getValues()
print(imageyog)
image = imageyog.astype('float32')

grayscale_17_levels(image)
viewImage(image)