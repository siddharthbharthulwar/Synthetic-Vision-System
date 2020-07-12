import cv2 as cv
import matplotlib.pyplot as plt 

img = cv.imread(r"C:\Users\siddh\Desktop\ZUhEA.png", cv.IMREAD_GRAYSCALE)

plt.imshow(img, cmap = 'gist_gray')
plt.show()