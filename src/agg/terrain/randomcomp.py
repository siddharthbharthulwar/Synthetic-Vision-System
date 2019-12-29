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

a = TerrainGrid((rd1), (1,1), 1)
a.arrayValues = a.arrayValues[9000:10000, 3000:4000]

a.show(-5, 50)

c = cv.threshold(a.arrayValues, 2.4, 200, cv.THRESH_BINARY)[1].astype('uint8')
c = cv.erode(c, np.ones((2,2), np.uint8), iterations = 1)

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(c, connectivity=4)
print(n_labels)

colors = np.random.randint(0, 255, size=(n_labels , 3), dtype=np.uint8)
colors[0] = [0, 0, 0]  # for cosmetic reason we want the background black
false_colors = colors[labels]

plt.imshow(a.arrayValues)
plt.imshow(ma.masked_values(false_colors, 0))
plt.show()

unique = np.unique(labels)
histogram = []
for i in unique:
    histogram.append(stats[i, 4])


histogram2 = []
for i in histogram:
    if i < 100:
        histogram2.append(i)
n, bins, patches = plt.hist(histogram2, 100, facecolor='blue', alpha=0.5)
plt.show()
