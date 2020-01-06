from terraingrid import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng
import shapely

path = r"C:\Users\siddh\Projects\Synthetic Vision System\raster.png"

img = cv.imread(path, cv.IMREAD_GRAYSCALE)

plt.imshow(img)
plt.show()


corners = cv.goodFeaturesToTrack(img, 15, 0.01, 10)

for i in corners:
    x,y = i.ravel()
    cv.circle(img,(x,y),3,0,-1)
plt.imshow(img)
plt.show()