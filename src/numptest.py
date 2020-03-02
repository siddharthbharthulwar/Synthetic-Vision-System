import numpy as np
import matplotlib.pyplot as plt


a = np.zeros((12500, 10000))
b = np.ones((500, 500))

a[0:500, 0:500] = b
plt.imshow(a)
plt.show()