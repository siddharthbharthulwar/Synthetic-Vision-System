#file containing functions useful in initial aggregation of DSM data


import numpy as np
import rasterio as rio
import matplotlib.pyplot as plt
import numpy.ma as ma
import math

#spatial extent for normal AHN grid tile is 8500 left, 437500 bottom, 90000 right, 443750 top

def load(path, fillBoolean):
    with rio.open(path) as src:
    # convert / read the data into a numpy array: masked= True turns `nodata` values to nan
        lidar_dem_im = src.read(1, masked=True)
        if fillBoolean == 1:
            arraya = ma.masked_values(lidar_dem_im, 3.40282e+38)
            array = arraya.filled(-7.5)
            return(array)
        if fillBoolean == 0:
            arraya = lidar_dem_im
            return(arraya)
        else:
            print("No valid parameter for filling in water values. 1 for YES, 0 for NO.")


        
def getBounds(path):
    with rio.open(path) as src:
        lidarBounds = src.bounds
        return(lidarBounds)

def getMetaData(path):
    with rio.open(path) as src:
        return(src.meta)

def show(inputArray, title, spatial_extent):
    array = ma.masked_values(inputArray, 3.40282e+38)
    fig, ax = plt.subplots()
    lidar_plot = ax.imshow(array, 
                           cmap='terrain', 
                           vmin = -10,
                           vmax = 50,
                           extent= spatial_extent)
    ax.set_title(str(title), fontsize= 20)
    ax.set_aspect('equal', 'box')
    print(spatial_extent)

    fig.colorbar(lidar_plot)
    # turn off the x and y axes for prettier plotting

    plt.show()
    
def fillShow(inputArray, title, spatial_extent):
    arraya = ma.masked_values(inputArray, 3.40282e+38)
    array = arraya.filled(-7.5)
    fig, ax = plt.subplots()
    lidar_plot = ax.imshow(array, 
                           cmap='gray', 
                           vmin = -10,
                           vmax = 30,
                           extent= spatial_extent)
    ax.set_title(str(title), fontsize= 20)
    ax.set_aspect('equal', 'box')
    print(spatial_extent)

    fig.colorbar(lidar_plot)
    plt.show()
    # turn off the x and y axes for prettier plotting

    
def tripleGridShow(t1, t2, t3, m1, m2, m3, b1, b2, b3):
    global tripleCombinedArray
    at1 = load(t1, 1)
    at2 = load(t2, 1)
    at3 = load(t3, 1)
    am1 = load(m1, 1)
    am2 = load(m2, 1)
    am3 = load(m3, 1)
    ab1 = load(b1, 1)
    ab2 = load(b2, 1)
    ab3 = load(b3, 1)
    top = np.hstack((at1, at2, at3))
    mid = np.hstack((am1, am2, am3))
    bot = np.hstack((ab1, ab2, ab3))
    tripleCombinedArray = np.vstack((top, mid, bot))
    primaryBounds = getBounds(t1)
    fillShow(tripleCombinedArray, "Triple Grid", primaryBounds)
    print(tripleCombinedArray.shape)
    

def stack(paths, dimensions):
    xdim = dimensions[0]
    ydim = dimensions[1]


class TerrainGrid:
    def __init__(self, path, xPos, yPos):
        self.arrayValues = load(path, 1)
        self.xPos = xPos
        self.yPos = yPos

    def getMetadata(self):
        return(self.xPos, self.yPos)

    def stack(self):
        print("not functional at the moment")




#note: final stack function will be used in class and will only accept rectangular array grids. 