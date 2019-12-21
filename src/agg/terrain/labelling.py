from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng
from time import time

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"

r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"


DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"


a = TerrainGrid((rd0), (1,1), 1)
a.arrayValues = a.arrayValues[10000:12000, 0:4000]
c = cv.threshold(a.arrayValues, 2, 200, cv.THRESH_BINARY)[1].astype('uint8')

b = a.erodilate(2.9, np.ones((2,2), np.uint8) , 1).astype('uint8')

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity=8)
print(n_labels)



plt.imshow(a.arrayValues)


plt.imshow(ma.masked_values(labels, 0), cmap = 'gist_gray', vmin = 0, vmax = 1)
plt.show()



retl = []
start = time()
minsize = 500
for i in np.unique(labels):
    if (stats[i, 4] > minsize):
        perm = np.var((ma.masked_not_equal(labels, i) / i)* a.arrayValues)
        if (perm > 2):
            retl.append([perm, 'veg'])
        else:
            retl.append([perm, 'bld'])
    print(i)
end = time()
print(len(retl))
print(n_labels)
print(end - start ," seconds to complete task. ")

print(retl)
print(retl[1])



n, bins, patches = plt.hist(retl, 250, facecolor='blue', alpha=0.5)
plt.show()


'''
plt.imshow(np.zeros(labels.shape), cmap = 'gist_gray')
plt.imshow(ma.masked_values(labels, 0))
plt.show()
'''
