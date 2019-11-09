import aggregation as ag 
import rasterio.warp
import numpy as np 
import matplotlib.pyplot as plt
import numpy.ma as ma
import cv2 as cv

DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DTM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHNDTM\\M5_37FZ1\\m5_37fz1.tif"

a = ag.TerrainGrid(DSM8, (1,1), 1)
b = ag.TerrainGrid(DTM8, (1,1) ,0)
c = a.arrayValues
d = b.arrayValues

e = c - d
e = e.astype('uint8')
f = cv.threshold(e, 0.1, 50, cv.THRESH_TOZERO)
f = f[1]
f = f.astype('float32')
g = ma.masked_values(f, 249)
h = ma.masked_values(g, 0)

plt.imshow(h, vmin = -5, vmax = 50)
plt.show()