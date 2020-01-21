from terraingrid import TerrainGrid
import matplotlib.pyplot as plt
import cv2 as cv

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn1.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"
path = r"C:\Users\siddh\Documents\DSMS\R_25GN1\r_25gn1.tif"

a = TerrainGrid(rd1, (1,1), 1)
a.show(-5, 50)
a.arrayValues = a.arrayValues[9500: 10000, 2500:3000]

a.full_classification(3, 600, 1, True, 15, 10, False)