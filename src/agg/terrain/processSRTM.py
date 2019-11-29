from aggregation import TerrainGrid
from scipy.ndimage.filters import gaussian_filter
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt  




SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"


a = TerrainGrid((SRTM3), (1,1), 0).arrayValues

b = gaussian_filter(a, sigma = 2)

plt.imshow(b)
plt.show()