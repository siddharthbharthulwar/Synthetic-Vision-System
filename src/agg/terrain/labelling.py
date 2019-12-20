from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"


a = TerrainGrid((rd1), (1,1), 1)

c = cv.threshold(a.arrayValues, 5, 200, cv.THRESH_BINARY)[1].astype('uint8')

b = a.erodilate(3.6, np.ones((2,2), np.uint8) , 1).astype('uint8')

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity=4)

colors = np.random.randint(0, 255, size = n_labels, dtype = np.uint8)
colors[0] = 0
false_colors = colors[labels]

count = 0
min_area = 100
indexList = []
while (count < n_labels):
    if (stats[count, 4] > min_area):
        indexList.append(count)
        count = count + 1
    else:
        count = count + 1

print(len(indexList))
#the issue here is that the numpy labelling process isn't semantic. 
plt.imshow(a.arrayValues, vmin = -10, vmax = 50)
plt.imshow(ma.masked_values(labels, 0), cmap = 'gist_gray', alpha = 0.8)
plt.show()

