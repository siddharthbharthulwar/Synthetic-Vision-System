from terraingrid import TerrainGrid
import numpy as np 
import cv2 as cv 
import matplotlib.pyplot as plt 


b = r"D:\Documents\School\2019-20\ISEF 2020\ML\PNG\r5_30hz2.png"


img = cv.imread(b)
imgray = cv.cvtColor(img, cv.COLOR_RGB2GRAY)
ret, thresh = cv.threshold(imgray, 50, 255, cv.THRESH_BINARY_INV)

im2, contours, hierarchy = cv.findContours(thresh, cv.RETR_TREE, cv.CHAIN_APPROX_SIMPLE)

cv.drawContours(imgray, contours, -1, (0, 0, 255), 3)

epsilon = 0.1 * cv.arcLength(contours[5], True)
approx = cv.approxPolyDP(contours[5], epsilon, True)
print(len(contours))
print(contours)
print("break")
print(approx)
cv.imshow("Image", imgray)
cv.waitKey(0)
cv.destroyAllWindows()
