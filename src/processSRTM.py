from terraingrid import TerrainGrid
from scipy.ndimage.filters import gaussian_filter
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt  
import numpy.ma as ma
import os



SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"


a = TerrainGrid((SRTM1, SRTM2, SRTM3, SRTM4), (2,2), 0)
a.arrayValues = a.arrayValues[3000:5048, 0:2048]
a.show(-5, 50)

water = ma.masked_not_equal(a.arrayValues, 1).astype('uint8') + np.ones(a.arrayValues.shape)
ret, thresh = cv.threshold(water, 0, 1, cv.THRESH_BINARY_INV)
plt.imshow(thresh)
plt.show()

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(thresh.astype('uint8'), connectivity=4)
print(n_labels)
waterarray = np.zeros(a.arrayValues.shape)
waterlist = []
unique = np.delete(np.unique(labels), 0)

for i in unique:


    if (stats[i, 4]) > 190:
        print(i)
        waterlist.append(i)
        org = ma.masked_not_equal(labels, i) / i
        waterarray = np.add(waterarray, org.filled(0))

dim = np.zeros(waterarray.shape)

R = np.stack((waterarray, dim, dim), axis = 2)

plt.imshow(R)
plt.imsave('blendmap.png', R, cmap = 'gist_gray')
plt.show()
plt.imshow(a.arrayValues, cmap = 'gist_gray')
plt.show()



a.arrayValues = gaussian_filter(a.arrayValues, sigma = 0.5)


min = np.amin(a.arrayValues)

a.arrayValues = a.arrayValues - min


plt.imshow(a.arrayValues)
plt.show()

print(np.amin(a.arrayValues))


plt.imsave('heightmap.png', a.arrayValues, cmap = 'gist_gray')

