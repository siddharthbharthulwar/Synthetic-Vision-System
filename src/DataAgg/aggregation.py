#file containing functions useful in initial aggregation of DSM data


import numpy as np
import rasterio as rio
import matplotlib.pyplot as plt
import numpy.ma as ma
from rasterio.warp import calculate_default_transform, reproject, Resampling
from mpl_toolkits.mplot3d.axes3d import *
import math

#spatial extent for normal AHN grid tile is 8500 left, 437500 bottom, 90000 right, 443750 top

def load(path, fillBoolean):
    with rio.open(path) as src:
    # convert / read the data into a numpy array: masked= True turns `nodata` values to nan
        lidar_dem_im = src.read(1, masked=True)
        arraya = ma.masked_values(lidar_dem_im, 3.40282e+38)
        if fillBoolean == 1:
            array = arraya.filled(-7.5)
            return(array)
        if fillBoolean == 0:
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
    
class GridArray:
    def __init__(self, arrayName, xPos, yPos):
        self.arrayValues = load(arrayName, 1)
        self.xPos = xPos
        self.yPos = yPos

    def getMetadata(self):
        return(self.xPos, self.yPos)
     
def veristack(arrayParam):
    n = 1
    stackedArray = arrayParam[0]
    while n < len(arrayParam):
        stackedArray = np.vstack((stackedArray, arrayParam[n]))
        n = n + 1
    return(stackedArray)


#stacks arrays in square grid. input must be a square number of arrays. 
def stack(array):
    sq = int(math.sqrt(len(array)))
    n = 0
    initStackList = []
    posList = []
    stackedList = []
    while n < len(array):
        initStackList.append(array[n])
        posList.append(n)
        n = n + sq
    print(initStackList)
    n = 0 #vertical 
    q = 0
    cout = 0
    while n < len(array):
        stackedList[n] = initStackList[n]
        cout = posList[q]
        while cout < sq:
            stackedList[n] = np.hstack(stackedList[n], array[cout + 1])
            cout = cout + 1
            print(stackedList)
        n = n + sq
        q = q + 1
    veristack(stackedList)
    return(veristack)

    
#final gridShow function will be modular to adapt to any square grid size using tuples
    
    
    
    
    
