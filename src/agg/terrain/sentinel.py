import aggregation as ag 
import numpy as np  
import matplotlib.pyplot as plt 
import cv2 as cv
import numpy.ma as ma


path = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SatelliteImagery\\NedTIFF\\L1C_T31UET_A022785_20191102T105441.tif"


a = ag.load(path, 0)
b = a.astype('uint8')
c = cv.Canny(b, 50, 60)
d = c.astype('float32')


plt.imshow(a)
plt.show()
