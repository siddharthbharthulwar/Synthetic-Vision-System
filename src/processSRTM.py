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


a = TerrainGrid((SRTM3), (1,1), 0)
a.show(-5, 50)
a.arrayValues = a.arrayValues[100:356, 644:900]
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

R = np.stack((waterarray, dim, dim), axis = 2)[0:1024, 0:1024]

plt.imshow(R)
p1 = os.path.join(os.path.dirname(os.path.abspath(__file__)), "Terrain\\blendmap.py")
print(p1)
print(type(p1))
plt.imsave(str(p1), R, cmap = 'gist_gray')
plt.show()
plt.imshow(a.arrayValues, cmap = 'gist_gray')
plt.show()


a.arrayValues = gaussian_filter(a.arrayValues, sigma = 3.8)

min = np.amin(a.arrayValues)

a.arrayValues = a.arrayValues - min

p2 = os.path.join(os.path.dirname(os.path.abspath(__file__)), "Terrain\\heightmap.py")


plt.imsave(str(p2), a.arrayValues, cmap = 'gist_gray')
