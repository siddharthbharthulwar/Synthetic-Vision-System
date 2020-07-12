from terraingrid import TerrainGrid
import matplotlib.pyplot as plt
import cv2 as cv
import numpy as np
from skimage.measure import _structural_similarity as ssim
import os

def mse(imageA, imageB):
	# the 'Mean Squared Error' between the two images is the
	# sum of the squared difference between the two images;
	# NOTE: the two images must have the same dimension
	err = np.sum((imageA.astype("float") - imageB.astype("float")) ** 2)
	err /= float(imageA.shape[0] * imageA.shape[1])
	
	# return the MSE, the lower the error, the more "similar"
	# the two images are
	return err
def compare_images(num, imageA, imageB, title, template):

	pt = r"C:\Users\siddh\Desktop\IEEE\resultimg"

	plt.imshow(imageA, cmap = 'gist_gray')
	plt.imsave(os.path.join(pt, (str(num) + "org.png")), imageA, cmap = 'gist_gray')
	
	plt.imshow(imageB, cmap = 'gist_gray')
	plt.imsave(os.path.join(pt, (str(num) + "pre.png")), imageB, cmap = 'gist_gray')

	plt.imshow(template, cmap = 'gist_gray')
	plt.imsave(os.path.join(pt, (str(num) + "tem.png")), template, cmap = 'gist_gray')
	# compute the mean squared error and structural similarity
	# index for the images
	m = mse(imageA, imageB)
	s = ssim.compare_ssim(imageA, imageB)
	# setup the figure

	fig = plt.figure(title)
	plt.suptitle("MSE: %.2f, SSIM: %.2f" % (m, s))

	#show template
	ax = fig.add_subplot(1, 3, 1)
	plt.imshow(template, cmap = plt.cm.gray)
	plt.axis("off")
	# show first image
	ax = fig.add_subplot(1, 3, 2)
	plt.imshow(imageA, cmap = plt.cm.gray)
	plt.axis("off")
	# show the second image
	ax = fig.add_subplot(1, 3, 3)
	plt.imshow(imageB, cmap = plt.cm.gray)
	plt.axis("off")
	# show the images
	plt.show()

rd0 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37ez2.tif"
rd1 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz1.tif"
rasdf = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37hn2.tif"
ehamr = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_25dn1.tif"
r2 = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_37fz2.tif"
path = r"C:\Users\siddh\Documents\DSMS\R_25GN1\r_25gn1.tif"
rotterdam = r"D:\Documents\School\2019-20\ISEF 2020\HighProcessed\r_51bz2.tif"

truthpath = r"D:\Documents\School\2019-20\ISEF 2020\TRUTHS\buildings.png"

#~~~~~~~~~~~~~~~~~~~~Testing Environments~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

# #1: [7200:8200, 4600:5600]
# #2: [7200:8200, 5600:6600]
# #3: [6700:7700, 1500:2500]
# #4: [5250:6250, 5250, 6250]
# #5: 


def compare_dem(number, bound1, bound2, bound3, bound4):

	a = TerrainGrid((rd0), (1,1), 1)
	a.show(-5, 50)
	a.arrayValues = a.arrayValues[bound1:bound2, bound3: bound4]
	a.kernelclassv2(3, 100, 200, 50000, 0.5, True, 11, 2, 20, False)

	fb = a.final_building_labels

	plt.imshow(fb)
	plt.title('fb')
	plt.show()

	num = (np.amax(fb) + np.amin(fb)) / 2

	fb = cv.threshold(a.final_building_labels, num, 1, cv.THRESH_BINARY_INV)[1].astype('uint8')

	truths = cv.imread(truthpath, cv.IMREAD_GRAYSCALE)[bound1:bound2, bound3: bound4]

	truths = cv.threshold(truths, 10, 1, cv.THRESH_BINARY)[1].astype('uint8')

	compare_images(number, truths, fb, "Ground Truths vs Prediction", a.arrayValues)

#compare_dem(1, 7200,8200, 4600,5600)
#compare_dem(2, 7200,8200, 5600,6600)
#compare_dem(3, 6700,7700, 1500,2500)
#compare_dem(4, 5250,6250, 5250, 6250)
compare_dem(5, 8200, 9000, 7400, 8200)

