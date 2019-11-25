


from aggregation import TerrainGrid
import numpy as np
import cv2 as cv 
import numpy.ma as ma
import matplotlib.pyplot as plt
import rasterio as rio
import affine as affine
import time
from scipy.signal import butter, lfilter, freqz
from scipy.interpolate import RectBivariateSpline

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
eham6 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25DZ2\\r5_25dz2.tif"

a = TerrainGrid((eham1, eham2, eham4, eham5), (2,2), 1).arrayValues
