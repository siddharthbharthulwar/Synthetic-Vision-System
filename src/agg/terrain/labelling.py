from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"



a = TerrainGrid((rd0, rd1), (2,1), 1)
c = cv.threshold(a.arrayValues, 5, 200, cv.THRESH_BINARY)[1].astype('uint8')

b = a.erodilate(5, np.ones((2,2), np.uint8) , 2).astype('uint8')

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity=4)

colors = np.random.randint(0, 255, size = n_labels, dtype = np.uint8)
colors[0] = 0
false_colors = colors[labels]

print(np.amax(labels), np.amin(labels))
print(labels)

plt.imshow(labels)
plt.show()

