from terraingrid import TerrainGrid
import matplotlib.pyplot as plt
import numpy as np

path = r"C:\Users\siddh\Documents\DSMS\R5_37FZ1\r5_37fz1.tif"

a = TerrainGrid(path, (1,1), 1)
a.arrayValues = a.arrayValues[5000:6000, 7000:8000]
a.show(-5, 50)
a.classification(2.7, 2, 1, 4, 1000, 1.5, 1, 1, False)

plt.imshow(a.labeled_buildings, cmap = 'gist_gray', vmin = 0, vmax = 1)
plt.imshow(a.labeled_vegetation, cmap = 'winter', vmin = 0, vmax = 1)
plt.show()