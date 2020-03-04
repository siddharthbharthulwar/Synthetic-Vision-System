import time
import warnings
from mpl_toolkits.mplot3d import Axes3D

import numpy as np
import matplotlib.pyplot as plt

from sklearn.cluster import AgglomerativeClustering

harris = np.load('harrisresponse.npy')
var = np.load('vargrads.npy')

var2 = np.add(var, 5 * np.random.rand(len(var)))

X = []

for i in range(len(harris)):
  X.append([harris[i], var[i]])

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

ax.scatter(harris, var, var2)
plt.show()


cl = AgglomerativeClustering()
cl.fit_predict(X)

buildingshar = []
vegetationhar = []
buildingsyik = []

buildingsvar = []
vegetationvar = []
vegetationyik = []

for i in range(len(cl.labels_)):
  if (cl.labels_[i] == 1):
    buildingshar.append(harris[i])
    buildingsvar.append(var[i])
    buildingsyik.append(var2[i])
  else:
    vegetationhar.append(harris[i])
    vegetationvar.append(var[i])
    vegetationyik.append(var2[i])

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.set_xlabel('Average Lateral Harris Response')
ax.set_ylabel('STD of Gradient Derivative')
ax.set_zlabel('Average Vertical Harris Response')
ax.set_title('Agglomerative Hierarchical Clustering for Structure Footprint Features')
ax.scatter(buildingshar, buildingsvar, buildingsyik)
ax.scatter(vegetationhar, vegetationvar, vegetationyik)
plt.show()