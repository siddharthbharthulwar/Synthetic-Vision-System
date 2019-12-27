import numpy as np 
import matplotlib.pyplot as plt 
import cv2 as cv 
from aggregation import TerrainGrid

path = r"C:\Users\siddh\Desktop\r37.png"

truth = cv.imread(path, cv.IMREAD_GRAYSCALE).astype('uint8')

plt.imshow(truth)
plt.show()