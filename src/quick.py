from terraingrid import TerrainGrid
import matplotlib.pyplot as plt 




SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"

a = TerrainGrid((SRTM1, SRTM2, SRTM3, SRTM4), (2,2), 1)
plt.imshow(a.arrayValues, cmap = 'jet')
plt.show()