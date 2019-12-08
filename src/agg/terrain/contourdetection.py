from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"

'''
img = TerrainGrid((rd0), (1,1), 1).arrayValues[10000:11000, 6200:7200]
ret, thresh = cv.threshold(img, 3, 255, cv.THRESH_BINARY)

iterate = 1
kernel = np.ones(thresh.shape)

img_erosion = cv.erode(thresh, kernel, iterations = iterate)
img_errdil = cv.dilate(np.array(img_erosion), kernel, iterations = iterate)

plt.imshow(img_errdil)
plt.show()
'''

a = TerrainGrid((rd0), (1,1), 1)
b = a.erodilate(2.7, np.ones((3,3), np.uint8) , 1)[10000:11000, 6200:7200].astype('uint8')

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity=4)

colors = np.random.randint(0, 255, size = (n_labels, 3), dtype = np.uint8)
colors[0] = [0,0,0]
false_colors = colors[labels]

print(type(false_colors))

plt.imshow(false_colors)
plt.show()