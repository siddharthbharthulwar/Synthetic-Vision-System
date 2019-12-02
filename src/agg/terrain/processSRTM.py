from aggregation import TerrainGrid
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
print(np.mean(ma.masked_values(a.arrayValues, 0)))
'''
a.arrayValues = gaussian_filter(a.arrayValues[0:400, 200:600], sigma = 2.5)

'''
a.arrayValues = gaussian_filter(a.arrayValues, sigma = 3.8) * 1.5
a.viewer_3d('viridis', -5, 30)
