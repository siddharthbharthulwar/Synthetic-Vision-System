from terraingrid import TerrainGrid
import matplotlib.pyplot as plt 
import numpy as np 
import cv2 as cv

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"



r0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
r1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"

a = TerrainGrid((r1), (1,1), 1)
a.arrayValues = a.arrayValues[9000:10000, 7000:8000]

cutoff = 1
a.classification(3, 2, 1, 4, 600, cutoff)

