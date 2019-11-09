
import cv2 as cv 
import numpy as np 
import matplotlib.pyplot as plt
import aggregation as ag 


path = "D:\\Documents\\School\\2019-20\\ISEF 2020\\GMAP\\satimg1.png"

a = ag.load(path, 0)
b = a.astype('uint8')
c = cv.Canny(b, 215, 411)
d = c.astype('float32')


plt.imshow(d)
plt.show()





