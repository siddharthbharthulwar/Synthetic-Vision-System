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
eham6 = r"D:\Documents\School\2019-20\ISEF 2020\AHN\R5_25DZ2 (1)\r5_25dz2.tif"

max_allowable = 2 #maximum allowable terrain slope(in meters)

a = TerrainGrid((eham1, eham2, eham3, eham4, eham5, eham6), (3, 2), 1)

def extract(terrainGrid, orientation, index, max_allowable):
    if orientation == 'h':
        profile = terrainGrid.elevationProfile('h', index, 0, terrainGrid.arrayValues.shape[0])
        max = profile.shape[0]
    if orientation == 'v':
        profile = terrainGrid.elevationProfile('v', index, 0, terrainGrid.arrayValues.shape[1])
        max = profile.shape[0]
    susArray = np.zeros(profile.shape)
    count = 0
    while count < max - 2:
        if (abs((profile[count + 2] - profile[count]) / 2) >= max_allowable or abs(profile[count + 1] - profile[count]) >= max_allowable) and ((profile[count + 1] > 0) or (profile[count + 2] > 0)):
            susArray[count] = profile[count]
            susArray[count + 2] = profile[count + 2]
            count = count + 1
        else:
            count = count + 1
    susArray = ma.masked_values(susArray, 0)
    return susArray

'''
plt.plot(a.elevationProfile('h', 420, 0, 1250))
plt.plot(extract(a, 'h', 420, 2.5))
plt.show()

'''

def totalExtract(terrainGrid, orientation, max_allowable):
    if orientation == 'h':
        orientationHeader = terrainGrid.arrayValues.shape[0]
        print(orientationHeader)
    if orientation == 'v':
        orientationHeader = terrainGrid.arrayValues.shape[1]
        print(orientationHeader)
    count = 0
    finalList = []
    while count < orientationHeader:
        finalList.append(extract(terrainGrid, orientation, count, max_allowable))
        count = count + 1
    finalList = np.array(finalList)
    return finalList

b = totalExtract(a, 'h', 2.5)
b = ma.masked_values(b, 0)
plt.imshow(a.arrayValues)
plt.imshow(b, cmap = 'gist_gray_r', alpha = 0.5)
plt.show()
'''
    imcount = 0
    final = []
    while imcount < orientationHeader:
        count = 0
        susArray = np.zeros(profile.shape)
        while count < max - 2:
            if (abs((profile[count + 2] - profile[count]) / 2) >= max_allowable or abs(profile[count + 1] - profile[count]) >= max_allowable) and ((profile[count + 1] > 0) or (profile[count + 2] > 0)):
                susArray[count] = profile[count]
                susArray[count + 2] = profile[count + 2]
                count = count + 1
            else:
                count = count + 1
        susArray = ma.masked_values(susArray, 0)
        final.append(susArray)
        imcount = imcount + 1
    return 
'''
