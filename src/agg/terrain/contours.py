from aggregation import TerrainGrid
import numpy as np 
import matplotlib.pyplot as plt 
import cv2 as cv
import numpy.ma as ma
from entropy import calc_entropy_1d

h1 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25bz1.tif"
h2 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25bz2.tif"
h3 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25dn1.tif"
h4 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25dn2.tif"
h5 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25ez1.tif"
h6 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25gn1.tif"

an2 = r"D:\Documents\School\2019-20\ISEF 2020\High\r25gn1.tif\r25gn1.tif"

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"


DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"

#11522 from 13861 to 14028
a = TerrainGrid((rd0, rd1), (2,1), 1)
'''
b = a.arrayThreshold(2, 5.5, cv.THRESH_TOZERO)

plt.imshow(a.arrayValues, vmin = -10, vmax = 50)
plt.show()
'''

b = a.arrayThreshold(2, 5.5, cv.THRESH_TOZERO)
#.arrayValues = b
plt.plot(a.elevationProfile('h', 11522, 13861, 14028))
#building from 80 to 160, trees from 0 to 70
c = a.elevationProfile('h', 11522, 13861, 14028)
print(calc_entropy_1d(c[100:140]))
print(calc_entropy_1d(c[0:70]))
plt.show()