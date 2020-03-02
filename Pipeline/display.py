from terraingrid import TerrainGrid
import matplotlib.pyplot as plt
import cv2 as cv

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn1.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"
path = r"C:\Users\siddh\Documents\DSMS\R_25GN1\r_25gn1.tif"
rotterdam = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_51bz2.tif"

<<<<<<< HEAD:src/display.py
a = TerrainGrid((path), (1,1), 1)
a.arrayValues = a.arrayValues[0:2000, 1000:2000]
a.kernelclassv2(3, 500, 200, 50000, 1, True, 15, 15, False)

=======
a = TerrainGrid((rd1), (1,1), 1)
a.show(-5, 50)

a.full_classification(3, 700, 1, True, 15, 15, True)
>>>>>>> 4ec53d7e194e2dba619508598581671241e7c605:Pipeline/display.py
