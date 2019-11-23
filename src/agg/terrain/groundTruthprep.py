

import rasterio as rasterio
import rasterio.warp
import numpy as np 
import matplotlib.pyplot as plt 
import aggregation as ag
import cv2 as cv 




path = r"D:\Documents\School\2019-20\ISEF 2020\ML\Real\R5_66FZ2\r5_66fz2.tif"

a = ag.TerrainGrid((path), (1,1), 1)
a.show(
    'viridis', -5, 40
)