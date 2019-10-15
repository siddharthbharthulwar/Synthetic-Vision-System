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


def stack(inputPaths, dimensions, fillBool):
    print(type(inputPaths))
    if isinstance(inputPaths, str) == True:
        a = load(inputPaths, fillBool)
        return a
    else:

        if len(inputPaths) == dimensions[0] * dimensions[1]:
            counter = 0
            paths = []
            while counter < len(inputPaths):
                paths.append(load(inputPaths[counter], fillBool))
                counter = counter + 1
            xdim = dimensions[0]
            ydim = dimensions[1]
            vert = 0
            hor = 1
            fin = []
            while vert < len(paths):
                fin.append(paths[vert])
                vert = vert + xdim
            #creates a list with all values in the first column of path array
            vert = 0
            posMark = 0
            while vert < ydim:
                while hor < xdim: #error is that hor is less than xdim for 2nd iteration
                    fin[vert] = np.hstack((fin[vert], paths[hor + posMark]))
                    hor = hor + 1
                posMark = posMark + xdim
                hor = 1
                vert = vert + 1

            vertical = 1
            finalArray = fin[0]
            while vertical < ydim:
                finalArray = np.vstack((finalArray, fin[vertical]))
                vertical = vertical + 1
            return(finalArray)
        else:
            print("Dimensions and length of arrayList do not match")

class TerrainGrid:
    def __init__(self, path, dimensions, fill):

        self.arrayValues = stack(path, dimensions, fill)
        self.xDimension = dimensions[0]
        self.yDimension = dimensions[1]
        self.fill = fill

    def show(self):
        plt.imshow(self.arrayValues)
        plt.show()
    def getValues(self):
        return self.arrayValues
