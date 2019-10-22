import aggregation as ag
import matplotlib.pyplot as plt
import numpy as numpy
from mpl_toolkits.mplot3d import axes3d, Axes3D


DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"


p1 = ag.TerrainGrid(DSM8, (1,1), 1)
val = p1.getValues()

fig = plt.figure()

X, Y, Z = axes3d.get_test_data(0.05)
cset = axes3d.contour(X, Y, Z, 16, extend3d=True)
axes3d.clabel(cset, fontsize=9, inline=1)
plt.show()