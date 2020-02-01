import scipy
import scipy.cluster.hierarchy as sch
import numpy as np
import matplotlib.pylab as plt

#X = scipy.randn(1001,2)
harris = np.load('harrisresponse1.npy')
var = np.load('vargrads1.npy')


X = []

for i in range(len(harris)):
  X.append([harris[i], var[i]])
d = sch.distance.pdist(X)

Z= sch.linkage(d,method='complete')

P =sch.dendrogram(Z)

T = sch.fcluster(Z, 0.5*d.max(), 'distance')
#array([4, 5, 3, 2, 2, 3, 5, 2, 2, 5, 2, 2, 2, 3, 2, 3, 2, 5, 4, 5, 2, 5, 2,
#       3, 3, 3, 1, 3, 4, 2, 2, 4, 2, 4, 3, 3, 2, 5, 5, 5, 3, 2, 2, 2, 5, 4,
#       2, 4, 2, 2, 5, 5, 1, 2, 3, 2, 2, 5, 4, 2, 5, 4, 3, 5, 4, 4, 2, 2, 2,
#       4, 2, 5, 2, 2, 3, 3, 2, 4, 5, 3, 4, 4, 2, 1, 5, 4, 2, 2, 5, 5, 2, 2,
#       5, 5, 5, 4, 3, 3, 2, 4], dtype=int32)

sch.leaders(Z,T)
# (array([190, 191, 182, 193, 194], dtype=int32),
#  array([2, 3, 1, 4,5],dtype=int32))

def plot_tree(P, pos=None):
    plt.clf()
    icoord = scipy.array(P['icoord'])
    dcoord = scipy.array(P['dcoord'])
    color_list = scipy.array(P['color_list'])
    print(len(color_list))
    xmin, xmax = icoord.min(), icoord.max()
    ymin, ymax = dcoord.min(), dcoord.max()
    if pos:
        icoord = icoord[pos]
        dcoord = dcoord[pos]
        color_list = color_list[pos]
    fig, ax = plt.subplots()

    for xs, ys, color in zip(icoord, dcoord, color_list):
        ax.plot(xs, ys, color)


    ax.set_xlabel('Weighted Average of Harris Response Function')
    ax.set_ylabel('Height')
    ax.set_title('Agglomerative Hierarchical Cluster Dendogram')
    plt.show()


  
plot_tree(P)