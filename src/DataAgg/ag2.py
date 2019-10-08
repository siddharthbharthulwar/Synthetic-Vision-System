from osgeo import gdal, osr
import os, sys
import matplotlib.pyplot as plt
import aggregation as aggregation

gdal.UseExceptions()


def open(path):
    ds = gdal.Open(path)
    width = ds.RasterXSize
    height = ds.RasterYSize
    gt = ds.GetGeoTransform


def show(array, title, spatial_extent):
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

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"

#show(open(DSM9), "yikes, ag2", aggregation.getBounds(DSM9))