import aggregation as ag
import matplotlib.pyplot as plt
import cv2 as cv 
import numpy.ma as ma 


a = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SatelliteImagery\\3601228_n1e226\\CO\\2014\\201403_denver_area02_co_0x1500m_utm_cnir\\vol001\\n1e226.tif"

l = ag.load(a, 0)
l = l.astype('uint8')
t = cv.Canny(l, 65, 555)
z = t.astype('float32')
'''
tau = ma.masked_values(z, 0)
'''
tau = z
#plt.imshow(l)
plt.imshow(tau)
plt.show()






