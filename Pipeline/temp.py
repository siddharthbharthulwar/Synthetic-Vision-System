from terraingrid import TerrainGrid
import numpy as np 
import matplotlib.pyplot as plt 
import cv2 as cv 

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"

a = TerrainGrid((rd1), (1,1), 1)
a.show(-5, 50)
ret, thresh = cv.threshold(a.arrayValues, 5, 1, cv.THRESH_BINARY)
plt.imshow(thresh)
plt.show()
plt.imshow(a.erodilate(5, 2, 5))
plt.show()
