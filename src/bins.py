import matplotlib.pyplot as plt 
import numpy as np 
import numpy.ma as ma
from aggregation import TerrainGrid



rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn2.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"

a = TerrainGrid((rd0), (1,1), 1)
a.arrayValues = a.arrayValues[0:10000, 0:10000]

'''
b = a.totalSlope('h')
#c = a.totalSlope('v')
d = np.gradient(a.arrayValues)

plt.imshow(b)
plt.show()
'''

d = np.gradient(a.arrayValues)

plt.imshow(d[0] + d[1], vmin = -4, vmax = 4)
plt.show()

