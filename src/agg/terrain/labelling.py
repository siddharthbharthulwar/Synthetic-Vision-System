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

a = TerrainGrid((rd0), (1,1), 1)
a.arrayValues = a.arrayValues[8000:10000, 3000:5000]

c = cv.threshold(a.arrayValues, 4, 200, cv.THRESH_BINARY)[1].astype('uint8')

b = a.erodilate(4, np.ones((1,1), np.uint8) , 1).astype('uint8')
b = cv.dilate(c, np.ones((1,1), np.uint8), iterations = 4)


n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity=4)
print(n_labels)



plt.imshow(a.arrayValues)


plt.imshow(ma.masked_values(labels, 0), cmap = 'gist_gray', vmin = 0, vmax = 1)
plt.show()


variance = []

buildings = []
vegetation = [] 

inbuildings = []
invegetation = []

unique = np.delete(np.unique(labels), 0)


incount = 0

for i in unique:
    if (stats[i, 4] > 500):
        print(i)
        var = np.var((ma.masked_not_equal(labels, i) / i) * a.arrayValues)
        variance.append(var)
        if (var > 2.5):
            vegetation.append(i)
            invegetation.append(incount)
            incount +=1 
        else:
            buildings.append(i)
            inbuildings.append(incount)
            incount +=1

print(len(buildings), len(vegetation), len(variance))


count = 0
while (count < len(buildings)):
    plt.imshow((ma.masked_not_equal(labels, buildings[count]) / buildings[count]) * a.arrayValues)
    print("Real index of: ", buildings[count], " and relative index of: ", inbuildings[count], " with variance of: ", variance[inbuildings[count]], " (B)")
    plt.show()
    count +=1

count = 0
while (count < len(vegetation)):
    plt.imshow((ma.masked_not_equal(labels, vegetation[count]) / vegetation[count]) * a.arrayValues)
    print("Real index of: ", vegetation[count], " and relative index of: ", invegetation[count], " with variance of: ", variance[invegetation[count]], " (V)")
    plt.show()
    count +=1
