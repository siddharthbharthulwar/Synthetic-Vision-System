#file containing functions useful in initial aggregation of DSM data


import numpy as np
import rasterio as rio
import matplotlib.pyplot as plt
import numpy.ma as ma
from rasterio.warp import calculate_default_transform, reproject, Resampling
import tensorflow as tf
from mpl_toolkits.mplot3d.axes3d import *
import math

#spatial extent for normal AHN grid tile is 8500 left, 437500 bottom, 90000 right, 443750 top

highDSM = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R_37FZ1\\r_37fz1.tif"
lowDSM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
lowDSM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
lowDSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
lowDSM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"
highDTM = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\M_37FZ1\\m_37fz1.tif"
lowDTM = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\M5_37FZ1\\m5_37fz1.tif"

def load(path):
    global loadBounds
    with rio.open(path) as src:
    # convert / read the data into a numpy array: masked= True turns `nodata` values to nan
        lidar_dem_im = src.read(1, masked=True)
        return(lidar_dem_im)
        
def getBounds(path):
    with rio.open(path) as src:
        lidarBounds = src.bounds
        return(lidarBounds)

def getMetaData(path):
    with rio.open(path) as src:
        return(src.meta)
        
def open(path, minMeters, maxMeters, title):
    global spatial_extent
    with rio.open(path) as src:
    # convert / read the data into a numpy array: masked= True turns `nodata` values to nan
        lidar_dem_im = src.read(1, masked=True)
    # create a spatial extent object using rio.plot.plotting
        spatial_extent = src.bounds
        print(spatial_extent)

    print("object shape:", lidar_dem_im.shape)
    print("object type:", type(lidar_dem_im))
    
    fig, ax = plt.subplots(figsize = (8,3))
    lidar_plot = ax.imshow(lidar_dem_im, 
                           cmap='plasma', 
                           vmin = minMeters,
                           vmax = maxMeters,
                           extent=spatial_extent)
    ax.set_title(str(title), fontsize= 20)


    fig.colorbar(lidar_plot)
    # turn off the x and y axes for prettier plotting
    ax.set_axis_off()
    plt.show()


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


def doubleGridShow(left1, right1, left2, right2):
    global totalCombinedArray
    arrayLeft = load(left2)
    arrayRight = load(right2)
    array2Left = load(left1)
    array2Right = load(right1)
    combinedArray1 = np.hstack((arrayLeft, arrayRight))
    combinedArray2 = np.hstack((array2Left, array2Right))
    totalCombinedArray = np.vstack((combinedArray2, combinedArray1))
    primaryBounds = getBounds(left2)
    show(totalCombinedArray, "combined", primaryBounds)
    
def tripleGridShow(t1, t2, t3, m1, m2, m3, b1, b2, b3):
    global tripleCombinedArray
    at1 = load(t1)
    at2 = load(t2)
    at3 = load(t3)
    am1 = load(m1)
    am2 = load(m2)
    am3 = load(m3)
    ab1 = load(b1)
    ab2 = load(b2)
    ab3 = load(b3)
    top = np.hstack((at1, at2, at3))
    mid = np.hstack((am1, am2, am3))
    bot = np.hstack((ab1, ab2, ab3))
    tripleCombinedArray = np.vstack((top, mid, bot))
    primaryBounds = getBounds(t1)
    fillShow(tripleCombinedArray, "Triple Grid", primaryBounds)
    print(tripleCombinedArray.shape)
    
def gridShow(param):
    sq = len(param)
    num = math.sqrt(sq)
    for i in param:
    
        print("test")
    
#final gridShow function will be modular to adapt to any square grid size using tuples
    
    
    
    
    
