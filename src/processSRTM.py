from terraingrid import TerrainGrid
from scipy.ndimage.filters import gaussian_filter
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt  
import numpy.ma as ma



SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"


a = TerrainGrid((SRTM3), (1,1), 0)

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


    if (stats[i, 4]) > 75:
        print(i)
        waterlist.append(i)
        org = ma.masked_not_equal(labels, i) / i
        waterarray = np.add(waterarray, org.filled(0))

plt.imshow(waterarray)
plt.show()

a.arrayValues = gaussian_filter(a.arrayValues, sigma = 3.8) * 0.5
a.arrayValues = a.arrayValues[0:1024, 0:1024]
#a.viewer_3d('viridis', -5, 30)

plt.imshow(a.arrayValues, cmap = 'gist_gray')
plt.show()