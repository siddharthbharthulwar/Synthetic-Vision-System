from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm 
import matplotlib.pyplot as plt
from matplotlib import colors 
from aggregation import TerrainGrid
import numpy as np 
from scipy.ndimage.filters import gaussian_filter


rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"


SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"

a = TerrainGrid((SRTM3), (1,1), 0)
a.show(-5, 50)

a.arrayValues = a.arrayValues[2000:2400, 450:850]

plt.imshow(a.arrayValues)
plt.show()

nx, ny = 400, 400
x = range(nx)
y = range(ny)

data = a.arrayValues

hf = plt.figure()
ha = hf.add_subplot(111, projection='3d')

X, Y = np.meshgrid(x, y)  # `plot_surface` expects `x` and `y` data to be 2D
ha.plot_surface(X, Y, data, cmap = 'viridis', vmin = -5, vmax = 50)


plt.show()

data = gaussian_filter(a.arrayValues, sigma = 4.8)

plt.imshow(a.arrayValues)
plt.show()


plt.imshow(data)
plt.show()


hf = plt.figure()
ha = hf.add_subplot(111, projection='3d')

X, Y = np.meshgrid(x, y)  # `plot_surface` expects `x` and `y` data to be 2D
ha.plot_surface(X, Y, data, cmap = 'viridis', vmin = -5, vmax = 50)


plt.show()
