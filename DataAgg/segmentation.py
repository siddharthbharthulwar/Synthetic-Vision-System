
"""
Created on Sat Sep 21 15:53:42 2019

@author: siddharth bharthulwar
"""



import aggregation as ag
import reprojection as rp
import tensorflow as tf
import numpy as np


"""
1, 2, 3
4, 5, 6
7, 8, 9

"""

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30HZ2\\r5_30hz2.tif"
DSM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30HZ1\\r5_30hz1.tif"
DSM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30GZ2\\r5_30gz2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"




#ag.tripleGridShow(DSM1, DSM2, DSM3, DSM4, DSM5, DSM6, DSM7, DSM8, DSM9)
#cuh = ag.GridArray(DSM1, 4, 5)
#print(cuh.getMetadata())

#ag.show(ag.load(DSM1, 1), "test", ag.getBounds(DSM1))
aTest = np.array((1, 2, 3))
bTest = np.array((4, 5, 6))
cTest = np.array((7, 8, 9))
dTest = np.array((10, 11, 12))
eTest = np.array((13, 14, 15))
fTest = np.array((16, 17, 18))
gTest = np.array((19, 20, 21))
hTest = np.array((22, 23, 24))
iTest = np.array((25, 26, 27))



testo = ag.stack((aTest, bTest, cTest, dTest, eTest, fTest, gTest, hTest, iTest))
print(testo)