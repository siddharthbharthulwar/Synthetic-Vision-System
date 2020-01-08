from terraingrid import TerrainGrid
import matplotlib.pyplot as plt
import numpy as np
import rasterio as rio

path = r"C:\Users\siddh\Documents\DSMS\R_25GN1\r_25gn1.tif"

a = TerrainGrid((path), (1,1), 1)
a.arrayValues = a.arrayValues[0:1000, 0:1000]
a.classification(3, 2, 1, 4, 1000, 1.7, 1, 1, True)