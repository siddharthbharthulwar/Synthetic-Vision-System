from terraingrid import TerrainGrid
import numpy as np
import cv2 as cv 
import numpy.ma as ma
import matplotlib.pyplot as plt
import rasterio as rio
import affine as affine
import time
import time
import PIL
import os
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
'''
start = time.time()
plt.imsave('test1.png', TerrainGrid(eham1, (1,1), 1).arrayValues, vmin = -5, vmax = 50)
print(time.time() - start, " seconds elapsed to save test1.tif")

'''

def tiff_to_png(inputDir, outputDir):
    start = time.time()
    count = 0
    max = len(os.listdir(inputDir))
    while count < max:

        path = os.path.join(inputDir, os.listdir(inputDir)[count])
        name = os.listdir(inputDir)[count]
        a = TerrainGrid(path, (1,1), 1).arrayValues
        minimumval, maximumval = 3 * np.amin(a) / 4, 3 * np.amax(a) /4
        rawname = os.path.splitext(os.listdir(inputDir)[count])[0] + ".png"
        plt.imsave(os.path.join(outputDir, rawname.strip(" ")), a, vmin = minimumval, vmax = maximumval)
        print((count / max) * 100, " percent done.")
        count += 1
    end = time.time()
    print(end - start, " seconds elapsed processing ", max, " images. ")


tiff_to_png(r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed", r"D:\Documents\School\2019-20\ISEF 2020\FinalProcessed")

