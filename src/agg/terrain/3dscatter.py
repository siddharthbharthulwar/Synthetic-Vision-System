from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm 
import matplotlib.pyplot as plt
from matplotlib import colors 
from aggregation import TerrainGrid

rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"

a = TerrainGrid((rd1), (1,1), 1)


a.arrayValues =  15 * a.arrayValues[10500:12500, 3000:5000]
a.viewer_3d('viridis', -5, 50)