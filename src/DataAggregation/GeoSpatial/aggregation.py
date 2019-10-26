#file containing functions useful in initial aggregation of DSM data


import numpy as np
import rasterio as rio
import matplotlib.pyplot as plt
import numpy.ma as ma
import math
import cv2 as cv 
import rasterio.warp
import rasterio.features



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

def rioTransform(path, fillboolean):
    with rio.open(path) as src:
        lidar_dem_im = src.read(1, masked=True)
        metadata = src.meta
        transformAffine = metadata['transform']

        
def getBounds(path):
    with rio.open(path) as src:
        lidarBounds = src.bounds
        return(lidarBounds)

def getMetaData(path):
    with rio.open(path) as src:
        return(src.meta)


def getAffine(path):
    with rio.open(path) as src:
        retrieve = src.meta
        return retrieve['transform']

def stack(inputPaths, dimensions, fillBool):
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

def listBounds(path):
    count = 0
    final = []
    while count < len(path):
        with rio.open(path[count]) as src:
            final.append(src.bounds)
        count = count + 1
    return final

#converts the instance's AHN bounds into lat and lon coordinates
def transformBounds(normalBounds):
    returnList = []
    count = 0
    while count < len(normalBounds):
        temp = normalBounds[count]
        returnList.append(rasterio.warp.transform_bounds('EPSG:28992', {'init': 'epsg:4326'}, temp[0], temp[1], temp[2], temp[3]))
        count = count + 1
    return returnList


class TerrainGrid:
    #class for a grid of rastered array tiff files
    def __init__(self, path, dimensions, fill):

        self.arrayValues = stack(path, dimensions, fill)
        self.xDimension = dimensions[0]
        self.yDimension = dimensions[1]
        self.fill = fill
        self.bounds = listBounds(path)
        self.transformBounds = transformBounds(listBounds(path))

    def show(self, colormap, min, max):
        plt.imshow(self.arrayValues, cmap=colormap, vmin = min, vmax = max)
        plt.show()
    #simple matplotlib plotting of the terraingrid
    #accessor method to values of the terraingrid
    def arrayThreshold(self, value, outval, type):
        a = cv.threshold(self.arrayValues, value, outval, type)
        return ma.masked_values(a[1], 0)
    #opencv tozero threshold of all array values: values above "value" are preserved, values below are removed from array.
    #no idea why a is a tuple when the threshold function of opencv is supposed to return a single array, so that's why a[1] is what is being masked
    #with SRTM geospatial dataset, the value of thresholding can be set given the base ground altitude.