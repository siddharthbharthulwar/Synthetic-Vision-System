from terraingrid import TerrainGrid
from terraingrid import semistack
import numpy as np 
import matplotlib.pyplot as plt
import cv2 as cv 


rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn1.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"
path = r"C:\Users\siddh\Documents\DSMS\R_25GN1\r_25gn1.tif"
rotterdam = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_51bz2.tif"

a = TerrainGrid((rd1), (1,1), 1)
test = a.arrayValues

block = []
for x in range(0, test.shape[0], 500):
    for y in range(0, test.shape[1],500):
        block.append(test[x:x+500, y:y+500])

a.show(-5, 50)
'''
for i in range(len(block)):

    plt.imshow(block[i])
    plt.show()
'''
fin = semistack(tuple(block), (test.shape[1] / 500, test.shape[0] / 500))
plt.imshow(fin)
plt.show()