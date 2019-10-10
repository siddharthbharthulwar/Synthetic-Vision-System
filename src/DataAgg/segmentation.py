
"""
Created on Sat Sep 21 15:53:42 2019

@author: siddharth bharthulwar
"""



import aggregation as ag
import numpy as np
import cv2 as cv 
import matplotlib.pyplot as plt


"""
1, 2, 3
4, 5, 6
7, 8, 9

"""

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30HZ2\\r5_30hz2.tif"
DSM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30HZ1\\r5_30hz1.tif"
DSM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30GZ2\\r5_30gz2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"



#ag.tripleGridShow(DSM1, DSM2, DSM3, DSM4, DSM5, DSM6, DSM7, DSM8, DSM9)


img = np.hstack((ag.load(DSM7, 1), ag.load(DSM8, 1)))
th1 = cv.threshold(img, 127, 255, cv.THRESH_BINARY)
titles = ['yikes', 'yikes2']
images = [img, th1]


plt.imshow(img)
plt.show()