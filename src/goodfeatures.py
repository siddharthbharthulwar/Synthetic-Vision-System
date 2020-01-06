from terraingrid import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng
import shapely

path = r"C:\Users\siddh\Projects\Synthetic Vision System\raster.png"
path2 = r"C:\Users\siddh\Desktop\raster3.png"
img = cv.imread(path2, cv.IMREAD_GRAYSCALE)

plt.imshow(img)
plt.show()


corners = cv.goodFeaturesToTrack(img, 16, 0.01, 100)
im2, contours, hierarchy = cv.findContours(img, cv.RETR_TREE, cv.CHAIN_APPROX_SIMPLE)
final = cv.drawContours(img,contours, contourIdx= -1, color = 255, thickness= 1)

for i in corners:
    x,y = i.ravel()
    cv.circle(final,(x,y),7,140,-1)
plt.imshow(final)
plt.show()

print(corners)
print(corners.shape)