from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng
from time import time

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"

a = TerrainGrid((rd0), (1,1), 1).arrayValues[6300:6500, 1700:1850]


print(np.var(a))

b = TerrainGrid((rd0), (1,1), 1).arrayValues[7250:7400, 4560:4600]
print(np.var(b))