from aggregation import TerrainGrid
import numpy as np 
import matplotlib.pyplot as plt 
import cv2 as cv
import numpy.ma as ma

h1 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25bz1.tif"
h2 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25bz2.tif"
h3 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25dn1.tif"
h4 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25dn2.tif"
h5 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25ez1.tif"
h6 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25gn1.tif"

#6971 from 10075 to 10262
a = TerrainGrid((h3, h4), (2,1), 1)
a.show(-5, 40)
plt.plot(ma.masked_outside(a.elevationProfile('h', 6971, 10075, 10262), -30, 45))
plt.show()