#file containing functions useful in initial aggregation of DSM data


import numpy as np
import rasterio as rio
import matplotlib.pyplot as plt
import numpy.ma as ma
import math
import cv2 as cv 
import rasterio.warp
import rasterio.features
from mayavi import mlab
from scipy.ndimage.filters import gaussian_filter
import time as time



#spatial extent for normal AHN grid tile is 8500 left, 437500 bottom, 90000 right, 443750 top

def load(path, fillBoolean):
    with rio.open(path) as src:
    # convert / read the data into a numpy array: masked= True turns `nodata` values to nan
        lidar_dem_im = src.read(1, masked=True)
        if fillBoolean == 1:
            arraya = ma.masked_values(lidar_dem_im, np.amax(lidar_dem_im))
            array = arraya.filled(0.436)
            return(array)
        if fillBoolean == 0:
            arraya = ma.masked_values(lidar_dem_im, np.amax(lidar_dem_im))
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
                while hor < xdim: 
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

def listBounds(parameterVal):
    if isinstance(parameterVal, str):
        path = [parameterVal]
    else:
        path = parameterVal
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

def tileDimensions(param):
    returnList = []
    count = 0
    while count < len(param):
        current = param[count]
        dlat = current[3] - current[1]
        dlon = current[2] - current[0]
        returnList.append((dlat, dlon))
        count = count + 1
    return returnList

class BaseGrid:
    #class for a grid of rastered array tiff files
    def __init__(self, path, dimensions, fill):

        self.arrayValues = stack(path, dimensions, fill)
        self.dimensions = dimensions
        self.fill = fill
        self.bounds = listBounds(path)
        self.transformBounds = transformBounds(listBounds(path))
        self.raw_dimensions = tileDimensions(transformBounds(listBounds(path)))
        self.shape = self.arrayValues.shape
        self.dupValues = stack(path, dimensions, fill)


    def dynamicShow(self):
        plt.imshow(self.arrayValues, cmap='viridis', vmin = np.amin(self.arrayValues), vmax = np.amax(self.arrayValues))
        plt.show()
    def show(self, min, max):
        plt.imshow(self.arrayValues, cmap='viridis', vmin = min, vmax = max)
        plt.show()
    #simple matplotlib plotting of the terraingrid
    #accessor method to values of the terraingrid
    def arrayThreshold(self, value, outval, type):
        a = cv.threshold(self.arrayValues, value, outval, type)
        return ma.masked_values(a[1], 0)

    def horslice(self, row, xinit, xfinal):
        return(np.squeeze(self.arrayValues[row:row+1, xinit:xfinal]))

    def verslice(self, column, yinit, yfinal):
        return(np.squeeze(self.arrayValues[yinit:yfinal, column:column + 1]))

    def gridslice_2d(self, xinit, xfinal, yinit, yfinal):
        return(self.arrayValues[yinit:yfinal, xinit:xfinal])

    def viewer_3d(self, color, min, max):
        mlab.figure(size=(1920, 1080), bgcolor=(0.16, 0.28, 0.46))
        mlab.surf(self.arrayValues, colormap= color, warp_scale=0.2,
            vmin= min, vmax=max)

        mlab.view(-5.9, 900, 570, [5.3, 20, 238])
        mlab.show()     

    def interpolate(self, sigma):
        return gaussian_filter(self.arrayValues, sigma = sigma)

    def process(self):
        water = ma.masked_not_equal(self.arrayValues, 1).astype('uint8') + np.ones(self.arrayValues.shape)
        ret, thresh = cv.threshold(water, 0, 1, cv.THRESH_BINARY_INV)
        plt.imshow(thresh)
        plt.show()

        n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(thresh.astype('uint8'), connectivity = 4, )
        waterarray = np.zeros(self.arrayValues.shape)
        waterlist = []

        unique = np.delete(np.unique(labels), 0)

        for i in unique:

            if (stats[i, 4]) > 170:
                print(i, " / ", n_labels)
                org = ma.masked_not_equal(labels, i) / i
                waterarray = np.add(waterarray, org.filled(0))
        dim = np.zeros(waterarray.shape)

        R = np.stack((waterarray, dim, dim), axis = 2)

        plt.imshow(R)
        plt.imsave('blendmap.png', cmap = 'gist_gray')
        plt.show()

        self.arrayValues = gaussian_filter(self.arrayValues, sigma = 0.5)
        min = np.amin(self.arrayValues)

        self.arrayValues = self.arrayValues - min

        plt.imshow(self.arrayValues)
        plt.show()

        print(np.amin(self.arrayValues))