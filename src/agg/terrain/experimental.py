from aggregation import TerrainGrid
import numpy as np
import cv2 as cv 
import numpy.ma as ma
import matplotlib.pyplot as plt
import rasterio
from rasterio.warp import calculate_default_transform, reproject, Resampling
import affine as affine
import time
from skimage import data
from skimage.exposure import histogram
from skimage.feature import canny
from scipy import ndimage as ndimage
from skimage.viewer import ImageViewer
from skimage.filters import sobel
from skimage.filters import gaussian
from skimage.segmentation import active_contour

HIGH = "D:\\Documents\\School\\2019-20\\ISEF 2020\\HIGHAHN\\R_37HN1\\r_37hn1.tif"

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"

LAPTOPDSM = "C:\\Users\\siddh\\Documents\\DSMS\\R5_37FZ1\\r5_37fz1.tif"


SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"


eham1 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25CN2\\r5_25cn2.tif"
eham2 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25DN1\\r5_25dn1.tif"
eham3 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25DN2\\r5_25dn2.tif"
eham4 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25CZ2\\r5_25cz2.tif"
eham5 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25DZ1\\r5_25dz1.tif"
eham6 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_2s5DZ2\\r5_25dz2.tif"

max_allowable = 2 #maximum allowable terrain slope(in meters)

a = TerrainGrid((eham3), (1, 1), 1)

profile = a.elevationProfile('h', 400, 0, 1000)
max = profile.shape[0]



susArray = np.zeros(profile.shape)
count = 0
while count < max - 1:
    if abs(profile[count + 1] - profile[count]) >= max_allowable:
        susArray[count] = profile[count]
        susArray[count + 1] = profile[count + 1]
        count = count + 1
    else:
        count = count + 1


susArray = ma.masked_values(susArray, 0)
plt.plot(profile)
plt.plot(susArray, alpha = 0.8)
plt.show()