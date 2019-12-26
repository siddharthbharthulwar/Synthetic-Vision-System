from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm 
import matplotlib.pyplot as plt
from matplotlib import colors 
from aggregation import TerrainGrid
import numpy as np 

rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"

a = TerrainGrid((rd1), (1,1), 1)


a.arrayValues =  15 * a.arrayValues[10500:12500, 3000:5000]


nx, ny = 256, 1024

x = range(nx)
y = range(ny)

data = np.random.random((nx,ny))
hf = plt.figure()
ha = hf.add_subplot(111, projection = '3d')

X, Y = np.meshgrid(x, y)
ha.plot_surface(X, Y, data)

plt.show()