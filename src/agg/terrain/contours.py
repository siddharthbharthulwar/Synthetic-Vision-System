from aggregation import TerrainGrid
import numpy as np 
import matplotlib.pyplot as plt 
import cv2 as cv
import numpy.ma as ma

h1 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25bz1.tif"
h2 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25bz2.tif"
h3 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25dn1.tif"
h4 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25dn2.tif"
h5 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25ez1.tif"
h6 = r"D:\Documents\School\2019-20\ISEF 2020\High\r_25gn1.tif"

an2 = r"D:\Documents\School\2019-20\ISEF 2020\High\r25gn1.tif\r25gn1.tif"

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"


DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"

#11522 from 13861 to 14028
a = TerrainGrid((rd0, rd1), (2,1), 1)
a.arrayValues = a.arrayValues[7250:8750, 4500:6000]
plt.imshow(a.arrayValues)
plt.show()
'''
b = a.arrayThreshold(2, 5.5, cv.THRESH_TOZERO)

plt.imshow(a.arrayValues, vmin = -10, vmax = 50)
plt.show()
'''

ret, bw = a.arrayThreshold(3, 255, cv.THRESH_BINARY).astype('uint8')

plt.imshow(bw)
plt.show()

#find all your connected components (white blobs in your image)
nb_components, output, stats, centroids = cv.connectedComponentsWithStats(a.arrayValues.astype('uint8')[7250:8750, 4500:6000], connectivity=8)
#connectedComponentswithStats yields every seperated component with information on each of them, such as size
#the following part is just taking out the background which is also considered a component, but most of the time we don't want that.
sizes = stats[1:, -1]; nb_components = nb_components - 1

# minimum size of particles we want to keep (number of pixels)
#here, it's a fixed value, but you can set it as you want, eg the mean of the sizes or whatever
min_size = 300  

#your answer image
img2 = np.zeros((output.shape))
#for every component in the image, you keep it only if it's above min_size

for i in range(0, nb_components):
    print(i)
    if sizes[i] >= min_size:
        img2[output == i + 1] = 255
plt.imshow(img2.astype('float32'))

plt.show()