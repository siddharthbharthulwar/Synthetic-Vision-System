#file to reproject geotiffs to mercator
import numpy as np 
import rasterio
import aggregate as ag
from rasterio.warp import calculate_default_transform, reproject, Resampling



DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30HZ2\\r5_30hz2.tif"
DSM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30HZ1\\r5_30hz1.tif"
DSM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_30GZ2\\r5_30gz2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"




dst_crs = 'ESPG:28992'

def arrayReproject(path):
    global dst_crs
    with rasterio.open(path) as src:
        transform, width, height = calculate_default_transform(
            src.crs, dst_crs, src.width, src.height, *src.bounds)
        kwargs = src.meta.copy()
        kwargs.update({
            'crs': dst_crs,
            'transform': transform,
            'width': width,
            'height': height
        })

        with rasterio.open(path, 'w', **kwargs) as dst:
            for i in range(1, src.count + 1):
                reproject(
                    source=rasterio.band(src, i),
                    destination=rasterio.band(dst, i),
                    src_transform=src.transform,
                    src_crs=src.crs,
                    dst_transform=transform,
                    dst_crs=dst_crs,
                    resampling=Resampling.nearest)


