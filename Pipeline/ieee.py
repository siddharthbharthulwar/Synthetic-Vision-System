from terraingrid import TerrainGrid
import matplotlib.pyplot as plt
import scipy.signal as sig
import scipy.ndimage as ndi
import cv2 as cv
import numpy as np
import numpy.ma as ma

def gradient_x(imggray):
    ##Sobel operator kernels.
    kernel_x = np.array([[-1, 0, 1],[-2, 0, 2],[-1, 0, 1]])
    return sig.convolve2d(imggray, kernel_x, mode='same')
def gradient_y(imggray):
    kernel_y = np.array([[1, 2, 1], [0, 0, 0], [-1, -2, -1]])
    return sig.convolve2d(imggray, kernel_y, mode='same')

def harrisresponse(imggray):
    I_x = gradient_x(imggray)
    I_y = gradient_y(imggray)
    Ixx = ndi.gaussian_filter(I_x**2, sigma=1)
    Ixy = ndi.gaussian_filter(I_y*I_x, sigma=1)
    Iyy = ndi.gaussian_filter(I_y**2, sigma=1)
    k = 0.05
    # determinant
    detA = Ixx * Iyy - Ixy ** 2
    # trace
    traceA = Ixx + Iyy
        
    harris_response = (detA - k * traceA ** 2) 
    return harris_response


pt = r"D:\Downloads\R_37EN1\R_37EN1.TIF"
rotterdam = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_51bz2.tif"
rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn1.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"

a = TerrainGrid(rd0, (1,1), 1)

#[11400:12400, 3600: 4600]
#[8600:9600, 3800:4800]
#[1400:2400, 7600: 8600]
plt.imshow(a.arrayValues, cmap = 'gist_gray')
plt.show()

plt.imshow(a.arrayValues[11400:12400, 3600: 4600], cmap = 'gist_gray')
plt.show()

plt.imshow(a.arrayValues[8600:9600, 3800:4800], cmap = 'gist_gray')
plt.show()

plt.imshow(a.arrayValues[1400:2400, 7600: 8600], cmap = 'gist_gray')
plt.show()

#a.kernelclassv2(3, 100, 200, 50000, 0.6, True, 11, 2, 20, False)
