import cv2 as cv
import matplotlib.pyplot as plt
import numpy as np
import numpy.ma as ma

truths = cv.imread(r"C:\Users\siddh\Desktop\IEEE\resultimg\1post.png", cv.IMREAD_GRAYSCALE)
predictions = cv.imread(r"C:\Users\siddh\Desktop\IEEE\resultimg\1pre.png", cv.IMREAD_GRAYSCALE)

def plot(truths, prediction):

    h, w = truths.shape[0], truths.shape[1]

    tp = np.zeros(truths.shape)
    fp = np.zeros(truths.shape)
    fn = np.zeros(truths.shape)
    tn = np.zeros(truths.shape)

    for i in range(0, h):

        for j in range(0, w):

            if (truths[i, j] == 255 and prediction[i, j] == 255):

                tp[i, j] = 1

            elif (truths[i, j] == 255 and prediction[i, j] == 0):

                fn[i, j] = 1

            elif (truths[i, j] == 0 and prediction[i, j] == 255):

                fp[i, j] = 1

            else:

                tn[i, j] = 1

    tp = ma.masked_values(tp * 100, 0)
    fp = ma.masked_values(fp *50 , 0)
    fn = ma.masked_values(fn, 0)

    plt.imshow(tp, cmap = 'brg', vmin = 0.1) #green
    plt.imshow(fp, cmap = 'brg', vmin = 0.1, vmax = 100) #red
    plt.imshow(fn, cmap = 'brg', vmin = 0.1, vmax = 90) #blue

    plt.show()

plot(truths, predictions)