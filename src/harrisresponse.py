from skimage.io import imread
import matplotlib.pyplot as plt
from skimage.color import rgb2gray
import scipy.ndimage as ndi
from terraingrid import TerrainGrid
import cv2 as cv

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"

a = TerrainGrid(rd1, (1,1), 1)
imggray = a.arrayValues[11200:12400, 3000:5000]

ret, thresh = cv.threshold(imggray, 10, 1, cv.THRESH_BINARY)

plt.imshow(thresh)
plt.show()


from scipy import signal as sig
import numpy as np

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
    plt.imshow(harris_response, cmap = 'jet')
    plt.show()

harrisresponse(thresh)