from terraingrid import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng
import time as time


rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"

buildings = r"D:\Documents\School\2019-20\ISEF 2020\TRUTHS\buildings.png"
vegetation = r"D:\Documents\School\2019-20\ISEF 2020\TRUTHS\trees.png"

a = TerrainGrid((rd0), (1,1), 1)
a.arrayValues = a.arrayValues[5000:10000, 0:5000]

buildings_true = cv.imread(buildings, cv.IMREAD_GRAYSCALE).astype('uint8')[5000:10000, 0:5000]
vegetation_true = cv.imread(vegetation, cv.IMREAD_GRAYSCALE).astype('uint8')[5000:10000, 0:5000]

plt.imshow(buildings_true)
plt.show()

plt.imshow(vegetation_true)
plt.show

buildings_true = ma.masked_not_equal(buildings_true, np.amax(buildings_true))
n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(buildings_true, connectivity=4)

unique = np.delete(np.unique(labels), 0)

variancebld = []

derivative = np.gradient(a.arrayValues)[1]


print(n_labels)
for i in unique:
    print(i)

    org = ma.masked_not_equal(labels, i) / i
    var = np.std(org * derivative)
    variancebld.append(var)

    
vegetation_true = ma.masked_not_equal(vegetation_true, np.amax(vegetation_true))
n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(vegetation_true, connectivity=4)

unique = np.delete(np.unique(labels), 0)

varianceveg = []

derivative = np.gradient(a.arrayValues)[1]


print(n_labels)
for i in unique:
    print(i)

    org = ma.masked_not_equal(labels, i) / i
    var = np.std(org * derivative)
    varianceveg.append(var)


totalvariance = variancebld + varianceveg


veghistogram2 = []
for i in varianceveg:
    if (i < 50):
        veghistogram2.append(i)

bldhistogram2 = []
for i in variancebld:
    if (i < 50):
        bldhistogram2.append(i)



n, bins, patches = plt.hist(veghistogram2, 250, facecolor='blue', alpha=0.5)
plt.show()

n, bins, patches = plt.hist(bldhistogram2, 250, facecolor='blue', alpha=0.5)
plt.show()

n, bins, patches = plt.hist(totalvariance, 250, facecolor='blue', alpha=0.5)
plt.show()

print(np.mean(variancebld), " building variance mean")
print(np.mean(varianceveg), " vegetation variance mean")


