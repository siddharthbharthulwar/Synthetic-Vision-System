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
ret, thresh = cv.threshold(img, 200, 1, cv.THRESH_BINARY)


dst = cv.cornerHarris(thresh, 4, 3, 0.04)

plt.imshow(dst)
plt.show()