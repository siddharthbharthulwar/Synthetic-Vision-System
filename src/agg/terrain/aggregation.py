#file containing functions useful in initial aggregation of DSM data


import numpy as np
import rasterio as rio
import matplotlib.pyplot as plt
import numpy.ma as ma
import math
import cv2 as cv 
import rasterio.warp
import rasterio.features
from scipy import interpolate
from mayavi import mlab
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
def extract(terrainGrid, orientation, index, max_allowable):
    if orientation == 'h':
        profile = terrainGrid.elevationProfile('h', index, 0, terrainGrid.arrayValues.shape[0])
        max = profile.shape[0]
    if orientation == 'v':
        profile = terrainGrid.elevationProfile('v', index, 0, terrainGrid.arrayValues.shape[1])
        max = profile.shape[0]
    susArray = np.zeros(profile.shape)
    count = 0
    while count < max - 2:
        #decision algorithm below: needs modification!!!
        if abs(profile[count + 1] - profile[count]) >= max_allowable:
            susArray[count] = profile[count]
            susArray[count + 1] = profile[count + 1]
            count = count + 1
        else:
            count = count + 1
    susArray = ma.masked_values(susArray, 0)
    return susArray
def slopeExtract(terrainGrid, orientation, index):
    if orientation == 'h':
        profile = terrainGrid.elevationProfile('h', index, 0, terrainGrid.arrayValues.shape[0])
        max = profile.shape[0]
    if orientation == 'v':
        profile = terrainGrid.elevationProfile('v', index, 0, terrainGrid.arrayValues.shape[1])
        max = profile.shape[0]
    susArray = np.zeros(profile.shape)
    count = 0
    while count < max - 1:
        susArray[count] = profile[count + 1] - profile[count]
        count += 1
    return susArray
class TerrainGrid:
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

    def scipolate(self, scalefactor):
        min = 0
        xMax = self.shape[1] - 1
        yMax = self.shape[0] - 1
        X = np.linspace(min, xMax, self.shape[1])
        Y = np.linspace(min, yMax, self.shape[0])

        x, y = np.meshgrid(X, Y)

        f = interpolate.interp2d(x, y, self.arrayValues)
        Xnew = np.linspace(min, xMax, self.shape[1] * scalefactor)
        Ynew = np.linspace(min, yMax, self.shape[0] * scalefactor)

        return f(Xnew, Ynew)

    def viewer_3d(self, color, min, max):
        mlab.figure(size=(1920, 1080), bgcolor=(0.16, 0.28, 0.46))
        mlab.surf(self.arrayValues, colormap= color, warp_scale=0.2,
            vmin= min, vmax=max)

        mlab.view(-5.9, 900, 570, [5.3, 20, 238])
        mlab.show()     


    def showElevationProfile(self, orientation, index, starting, stopping):
        if orientation == 'h':
            plt.plot(self.horslice(index, starting, stopping))
            plt.show()
        if orientation == 'v':
            plt.plot(self.verslice(index, starting, stopping))
            plt.show()
        else:
            print("Error: Orientation must be either horizontal(h) or vertical(v)")
    def elevationProfile(self, orientation, index, starting, stopping):
        if orientation == 'h':
            return self.horslice(index, starting, stopping)
        if orientation == 'v':
            return self.verslice(index, starting, stopping)
        else:
            print("Error: Orientation must be either horizontal(h) or vertical(v)")
    def totalExtract(self, orientation, max_allowable):
        if orientation == 'h':
            orientationHeader = self.arrayValues.shape[0]
        if orientation == 'v':
            orientationHeader = self.arrayValues.shape[1]
        count = 0
        finalList = []
        while count < orientationHeader:
            finalList.append(extract(self, orientation, count, max_allowable))
            count = count + 1
            print(count)
        if orientation == 'h':
            return ma.masked_values((np.array(finalList)), 0)
        if orientation == 'v':
            final = np.array(finalList)
            return ma.masked_values(np.rot90(np.fliplr(final), 1), 0)
    def totalSlope(self, orientation):
        if orientation == 'h':
            orientationHeader = self.arrayValues.shape[0]
        if orientation == 'v':
            orientationHeader = self.arrayValues.shape[1]
        count = 0
        finalList = []
        while count < orientationHeader:
            finalList.append(slopeExtract(self, orientation, count))
            count +=1 
            print(count)
        if orientation == 'h':
            return np.array(finalList)
        if orientation == 'v':
            return np.rot90(np.fliplr(np.array(finalList)))
        
    def arrayDerivative(self, orientation, iterations):
        start = time.time()
        if orientation == "h":
            orientationHeader = self.arrayValues.shape[0]
        if orientation == "v":
            orientationHeader = self.arrayValues.shape[1]
        count = 0
        base = self
        while count < iterations:
            base.arrayValues = base.totalSlope(orientation)
            count +=1
        end = time.time()
        print("Time elapsed: ", end - start," seconds. ")
        return base
    def erodilate(self, thresh, kernel, iterate):
        b = cv.threshold(self.arrayValues, thresh, 255, cv.THRESH_BINARY)[1]
        img_erosion = cv.erode(b, kernel, iterations = iterate)
        img_errdil = cv.dilate(np.array(img_erosion), kernel, iterations = iterate)
        return img_errdil
    def label(self, threshold, kernel, iterations, connectivity):
        b = cv.threshold(self.arrayValues, threshold, 255, cv.THRESH_BINARY)[1].astype('uint8')
        img_erosion = cv.erode(b, kernel, iterations= iterations)
        img_errdil = cv.dilate(np.array(img_erosion), kernel, iterations = iterations)
        n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity = connectivity)
        self.dupValues = labels
        return labels
    def classification(self, threshold, iterations, connectivity, min_area, cutoff):
        b = self.erodilate(threshold, np.ones((1,1), np.uint8), 1).astype('uint8')
        n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity = connectivity)
        plt.imshow(a.arrayValues)
        plt.imshow(ma.masked_values(labels, 0), cmap = 'gist_gray', vmin = 0, vmax = 1)
        plt.show()

        variance = []
        buildings = []
        vegetation = []

        inbuildings = []
        invegetation = []

        unique = np.delete(np.unique(labels), 0)

        incount = 0

        start = time.time()

        for i in unique:

            if (stats[i, 4] > min_area):
                var = np.var((ma.masked_not_equal(labels, i) / i) * self.arrayValues)
                variance.append(var)

                if (var > cutoff):

                    vegetation.append(i)
                    invegetation.append(incount)
                    incount +=1

                else:

                    buildings.append(i)
                    inbuildings.append(incount)
                    incount +=1

        end = time.time()

        print(len(buildings), " buildings and ", len(vegetation), " trees processed in ", end - start, " seconds. ")
        
        count = 0
        while (count < len(buildings)):
            plt.imshow((ma.masked_not_equal(labels, buildings[count]) / buildings[count]) * a.arrayValues)
            print("Real index of: ", buildings[count], " and relative index of: ", inbuildings[count], " with variance of: ", variance[inbuildings[count]], " (B)")
            plt.show()
            count +=1

        count = 0
        while (count < len(vegetation)):
            plt.imshow((ma.masked_not_equal(labels, vegetation[count]) / vegetation[count]) * a.arrayValues)
            print("Real index of: ", vegetation[count], " and relative index of: ", invegetation[count], " with variance of: ", variance[invegetation[count]], " (V)")
            plt.show()
            count +=1

        self.n_labels = len(buildings) + len(vegetation)
        self.varlist = variance
        self.bldlist = buildings
        self.veglist = vegetation

        
    


#TODO: reproject the AHN data to align with the SRTM data properly
#TODO: interpolate SRTM arrays with rectangular bivariate spline from scipy package
#TODO: use watershed filtering to extract building footprints
#TODO: refine if statement for better filter


