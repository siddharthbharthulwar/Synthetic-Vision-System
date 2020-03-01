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
den = r"D:\Documents\School\2019-20\ISEF 2020\SRTM\n39_w106_1arc_v3.tif"

'''
a = TerrainGrid((SRTM1, SRTM2, SRTM3, SRTM4), (2,2), 0)
a.arrayValues = a.arrayValues[3000:5048, 0:2048]
a.show(-5, 50)
'''

a = TerrainGrid(den, (1,1), 0)
a.arrayValues = a.arrayValues[0:2048, 0:2048]
a.show(-5, 5000)
water = ma.masked_not_equal(a.arrayValues, 1).astype('uint8') + np.ones(a.arrayValues.shape)
ret, thresh = cv.threshold(water, 0, 1, cv.THRESH_BINARY_INV)
plt.imshow(thresh)
plt.show()

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(thresh.astype('uint8'), connectivity=4)
print(n_labels)
waterarray = np.zeros(a.arrayValues.shape, dtype = np.uint8)
waterlist = []
unique = np.delete(np.unique(labels), 0)

for i in unique:


    if (stats[i, 4]) > 190:
        print(i)
        waterlist.append(i)
        org = ma.masked_not_equal(labels, i) / i
        waterarray = np.add(waterarray, org.filled(0))

plt.imshow(waterarray)
plt.show()

inversewater = np.subtract(np.ones(waterarray.shape), waterarray)
dim = np.zeros(waterarray.shape)

waterarray = waterarray.astype('uint8')

a.arrayValues = gaussian_filter(a.arrayValues, sigma = 2.5)
a.show(-5, 50)


min = np.amin(a.arrayValues)

a.arrayValues = a.arrayValues - min
print(np.amin(a.arrayValues))
max = np.amax(a.arrayValues)

rgbvals = ((inversewater * a.arrayValues) * (255/max)).astype('uint8')
plt.imshow(rgbvals)
print(np.amax(rgbvals))
plt.show()


R = np.stack((waterarray * 255, rgbvals, dim.astype('uint8')), axis = 2)

plt.imshow(R)
plt.imsave('blendmap.png', R)
plt.show()
plt.imsave('heightmap.png', a.arrayValues, cmap = 'gist_gray')

