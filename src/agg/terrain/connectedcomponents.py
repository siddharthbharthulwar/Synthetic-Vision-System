from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt 
from erodedilate import processTotal


rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"

connectivity = 8


a = TerrainGrid((rd0, rd1), (2,1), 1).arrayValues[10000:11000, 6200:7200]
thresh = processTotal(a, 3.5, np.ones((2,2), np.uint8), 1)
plt.imshow(thresh)
plt.show()
output = cv.connectedComponentsWithStats(thresh.astype('uint8'), connectivity, cv.CV_32S)

num_labels = output[0]
labels = output[1]
stats = output[2]
centroids = output[3]

sizes = stats[1:, -1]
num_labels = num_labels - 1


img2 = np.zeros(labels.shape)
print(sizes)
print(num_labels)
for i in range(0, num_labels):
    if sizes[i] >= 300:
        img2[output == i + 1] = 255

plt.imshow(img2)
plt.show()
