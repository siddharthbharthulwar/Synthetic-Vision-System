
"""
Created on Sat Nov 9 at 5:06 PM
@author: siddharth bharthulwar
"""


#this file displays the histogram of a certain line of array values.
import aggregation as ag
import numpy as np
import cv2 as cv 
import numpy.ma as ma
import matplotlib.pyplot as plt
import rasterio as rio
import time
import scipy as sp 
import skimage

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

thresh1 = 127
thresh2 = 254

#Load image
bcc = ag.TerrainGrid((DSM8),(1,1),1)
im = bcc.arrayValues

#Get threashold mask for different regions
gryim = np.mean(im[:,:,0:2],2)
region1 =  (thresh1<gryim)
region2 =  (thresh2<gryim)
nregion1 = ~ region1
nregion2 = ~ region2

#Plot figure and two regions
fig, axs = plt.subplots(2,2)
axs[0,0].imshow(im)
axs[0,1].imshow(region1)
axs[1,0].imshow(region2)

#Clean up any holes, etc (not needed for simple figures here)
#region1 = sp.ndimage.morphology.binary_closing(region1)
#region1 = sp.ndimage.morphology.binary_fill_holes(region1)
#region1.astype('bool')
#region2 = sp.ndimage.morphology.binary_closing(region2)
#region2 = sp.ndimage.morphology.binary_fill_holes(region2)
#region2.astype('bool')

#Get location of edge by comparing array to it's 
#inverse shifted by a few pixels
shift = -2
edgex1 = (region1 ^ np.roll(nregion1,shift=shift,axis=0))
edgey1 = (region1 ^ np.roll(nregion1,shift=shift,axis=1))
edgex2 = (region2 ^ np.roll(nregion2,shift=shift,axis=0)) 
edgey2 = (region2 ^ np.roll(nregion2,shift=shift,axis=1))

#Plot location of edge over image
axs[1,1].imshow(im)
axs[1,1].contour(edgex1,2,colors='r',lw=2.)
axs[1,1].contour(edgey1,2,colors='r',lw=2.)
axs[1,1].contour(edgex2,2,colors='g',lw=2.)
axs[1,1].contour(edgey2,2,colors='g',lw=2.)
plt.show()