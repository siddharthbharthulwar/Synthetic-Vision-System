from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng
import time as time

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn2.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"

a = TerrainGrid((rd0), (1,1), 1)
a.arrayValues = a.arrayValues[8000:10000, 5000:7000]

a.show(-5, 50)

c = cv.threshold(a.arrayValues, 2.4, 200, cv.THRESH_BINARY)[1].astype('uint8')
'''
c = cv.erode(c, np.ones((5,5), np.uint8), iterations = 1)
c = cv.dilate(c, np.ones((5,5), np.uint8), iterations = 1)
'''
n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(c, connectivity=4)
print(n_labels)

variance = []



buildings = []
vegetation = [] 

inbuildings = []
invegetation = []
histogram = []
above = []
unique = np.delete(np.unique(labels), 0)


incount = 0
bray = np.gradient(a.arrayValues)[0]
plt.imshow(bray, vmin = -2, vmax = 2)
plt.show()

blda = np.zeros(labels.shape, np.uint8)
vega = np.zeros(labels.shape, np.uint8)
start = time.time()
for i in unique:
    print(i)
    if (stats[i, 4] > 600):

        org = ma.masked_not_equal(labels, i) / i
        var = np.std(org * bray)
        variance.append(var)
        histogram.append(var)
        if (var > 1.5):
            vegetation.append(i)
            invegetation.append(incount)
            incount +=1 
            vega = np.add(vega, org.filled(0))
        else:
            if (var > 1):
                above.append((var, i, incount))
            buildings.append(i)
            inbuildings.append(incount)
            incount +=1
            blda = np.add(blda,org.filled(0))

print(len(buildings), len(vegetation), len(variance))
end = time.time()
print(end - start ," seconds elapsed cuh.")

plt.imshow(a.arrayValues)
plt.imshow(ma.masked_values(blda, 0), cmap = 'gist_gray', vmin = 0, vmax = 1)
plt.imshow(ma.masked_values(vega, 0), cmap = 'winter', vmin = 0, vmax = 1)
plt.show()

histogram2 = []
for i in histogram:
    if (i < 50):
        histogram2.append(i)


n, bins, patches = plt.hist(histogram2, 250, facecolor='blue', alpha=0.5)
plt.show()





count = 0

while (count < len(buildings)):
    plt.imshow((ma.masked_not_equal(labels, buildings[count]) / buildings[count]) * bray)
    print("Real index of: ", buildings[count], " and relative index of: ", inbuildings[count], " with variance of: ", variance[inbuildings[count]], " (B)")
    plt.show()
    count +=1
count = 0
while (count < len(vegetation)):
    plt.imshow((ma.masked_not_equal(labels, vegetation[count]) / vegetation[count]) * bray)
    print("Real index of: ", vegetation[count], " and relative index of: ", invegetation[count], " with variance of: ", variance[invegetation[count]], " (V)")
    plt.show()
    count +=1

