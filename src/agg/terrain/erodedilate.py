from aggregation import TerrainGrid
import numpy as np 
import matplotlib.pyplot as plt 
import cv2 as cv
import numpy.ma as ma

h1 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25bz1.tif"
h2 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25bz2.tif"
h3 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25dn1.tif"
h4 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25dn2.tif"
h5 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25ez1.tif"
h6 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25gn1.tif"

an2 = r"D:\Documents\School\2019-20\ISEF 2020\High\r25gn1.tif\r25gn1.tif"

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"


DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"

'''
kernel = np.ones((2,2), np.uint8)

a = TerrainGrid((rd0, rd1), (2,1), 1)
#a.arrayValues = a.arrayValues[7250:8750, 4500:6000]
b = cv.threshold(a.arrayValues, 2, 255, cv.THRESH_BINARY)[1]
img_erosion = cv.erode(b, kernel, iterations = 1)
img_erdil = cv.dilate(np.array(img_erosion), kernel, iterations = 1)


plt.imshow(b)
plt.show()
plt.imshow(np.array(img_erosion))
plt.show()

plt.imshow(np.array(img_erdil))
plt.show()

'''

def arrayErode(array, threshval, kernel, iter):
    b = cv.threshold(array, threshval, 255, cv.THRESH_BINARY)[1]
    img_erosion = cv.erode(b, kernel, iterations = iter)
    return np.array(img_erosion)

def arraydilate(array, threshval, kernel, iter):
    b = cv.threshold(array, threshval, 255, cv.THRESH_BINARY)[1]
    img_dilation = cv.erode(b, kernel, iterations = iter)
    return np.array(img_dilation)

def processTotal(array, threshval, kernel, iter):
    b = cv.threshold(array, threshval, 255, cv.THRESH_BINARY)[1]
    img_erosion = cv.erode(b, kernel, iterations = iter)
    img_errdil = cv.dilate(np.array(img_erosion), kernel, iterations = iter)
    return img_errdil
