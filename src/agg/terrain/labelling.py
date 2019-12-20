from aggregation import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt
import numpy.ma as ma
import random as rng

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"

DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"


a = TerrainGrid((rd0), (1,1), 1)
a.arrayValues = a.arrayValues[11000:12000, 6000:7000]

c = cv.threshold(a.arrayValues, 5, 200, cv.THRESH_BINARY)[1].astype('uint8')

b = a.erodilate(3.6, np.ones((2,2), np.uint8) , 1).astype('uint8')

n_labels, labels, stats, centroids = cv.connectedComponentsWithStats(b, connectivity=4)


'''
contours, hierarchy = cv.findContours(b, cv.RETR_LIST, cv.CHAIN_APPROX_SIMPLE)[-2:]
idx = 0
for cnt in contours:
    idx +=1 
    x, y, w, h = cv.boundingRect(cnt)
    roi = a.arrayValues[y:y+h, x:x+w]
    color = (rng.randint(0, 256), rng.randint(0, 256), rng.randint(0, 256))
    cv.drawContours(a.arrayValues, [cnt], 0, color, -1)

cv.imshow('image', a.arrayValues)
cv.waitKey(0)
'''


colors = np.random.randint(0, 255, size = n_labels, dtype = np.uint8)
colors[0] = 0
false_colors = colors[labels]
'''
unique = np.unique(labels)
print(unique)
resultantList = []
print(n_labels)
for i, c in enumerate(unique):
    result = np.where(np.all(labels == c), 1, 0)
    resultantList.append(result)


for i in resultantList:
    plt.imshow(i)
    plt.show()
#the issue here is that the numpy labelling process isn't semantic. 
#plt.imshow(a.arrayValues, vmin = -10, vmax = 50)

'''
retl = []
for i in np.unique(labels):
    retl.append(cv.threshold(labels.astype('uint8'), i, 1, cv.THRESH_BINARY)[1])

plt.imshow(retl[1])
plt.show()
'''
plt.imshow(np.zeros(labels.shape), cmap = 'gist_gray')
plt.imshow(ma.masked_values(labels, 0))
plt.show()
'''
