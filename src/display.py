from terraingrid import TerrainGrid
import matplotlib.pyplot as plt 
import numpy as np 
import cv2 as cv

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"



r0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
r1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"

a = TerrainGrid((r1), (1,1), 1)
a.arrayValues = a.arrayValues[9000:10000, 1000:2000]

#plt.imshow(a.totalExtract('h', 3), vmin = 3, vmax = 5, cmap = 'gist_gray_r')
#plt.imshow(a.arrayThreshold(3, 255, cv.THRESH_BINARY), cmap = 'gist_gray')
#plt.imshow(a.totalSlope('h'), vmin = -2, vmax = 5)

a.heightmap(2.78, 2, 1, 4, 600, 1.6, 100, 20)
print(a.avgbldheight)

