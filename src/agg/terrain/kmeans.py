from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import time as time 


rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn2.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"

a = TerrainGrid((rd1), (1,1), 1)
a.arrayValues = a.arrayValues[5650:5850, 2900:3200]


b = a.erodilate(2, 2, 1).astype('uint8')
plt.imshow(a.arrayValues)
plt.imshow(ma.masked_values(b, 0), cmap = 'gist_gray_r')
plt.show()


n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity=4)
print(n_labels)

variance = []

buildings = []
vegetation = [] 

inbuildings = []
invegetation = []
histogram = []
unique = np.delete(np.unique(labels), 0)


incount = 0

newArray = np.zeros(labels.shape, np.uint8)
start = time.time()
for i in unique:
    print(i)
    if (stats[i, 4] > 500):

        org = ma.masked_not_equal(labels, i) / i
        var = np.var(org * a.arrayValues)
        variance.append(var)
        histogram.append(var * stats[i, 4] / 500)
        if (var > 2.8):
            vegetation.append(i)
            invegetation.append(incount)
            incount +=1 
            newArray = np.add(newArray, 20 * org.filled(0))
        else:
            buildings.append(i)
            inbuildings.append(incount)
            incount +=1
            newArray = np.add(newArray, 5 * org.filled(0))

print(len(buildings), len(vegetation), len(variance))
end = time.time()
print(end - start ," seconds elapsed cuh.")


count = 0
while (count < len(buildings)):
    plt.imshow((ma.masked_not_equal(labels, buildings[count])))
    plt.imshow(cv.erode(ma.masked_not_equal(labels, buildings[count]), np.ones((2,2), np.uint8), iterations = 2))
    print("Real index of: ", buildings[count], " and relative index of: ", inbuildings[count], " with variance of: ", variance[inbuildings[count]], " (B)")
    plt.show()
    count +=1
count = 0
while (count < len(vegetation)):

    eps = cv.threshold(ma.masked_not_equal(labels, vegetation[count]).astype('uint8'), 0.1, 1, cv.THRESH_BINARY)[1]
    plt.imshow(eps, alpha = 0.54)
    neww = cv.erode(eps, np.ones((2,2), np.uint8), iterations = 1)
    plt.imshow(neww * a.arrayValues / vegetation[count])
    print("Real index of: ", vegetation[count], " and relative index of: ", invegetation[count], " with variance of: ", variance[invegetation[count]], " (B)")
    plt.show()
    count +=1

plt.imshow(a.arrayValues)
plt.imshow(ma.masked_values(newArray, 0), cmap = 'tab10', vmin = 0, vmax = 19)
plt.show()

histogram2 = []
for i in histogram:
    if (i < 20):
        histogram2.append(i)


n, bins, patches = plt.hist(histogram2, 25, facecolor='blue', alpha=0.5)
plt.show()