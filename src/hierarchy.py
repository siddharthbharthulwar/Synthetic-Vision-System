import scipy
import scipy.cluster.hierarchy as sch
import matplotlib.pylab as plt

X = scipy.randn(1001,2)

d = sch.distance.pdist(X)

Z= sch.linkage(d,method='complete')

P =sch.dendrogram(Z)

plt.savefig('plot_dendrogram.png')

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
    xmin, xmax = icoord.min(), icoord.max()
    ymin, ymax = dcoord.min(), dcoord.max()
    if pos:
        icoord = icoord[pos]
        dcoord = dcoord[pos]
        color_list = color_list[pos]
    for xs, ys, color in zip(icoord, dcoord, color_list):
        plt.plot(xs, ys, color)
    plt.xlim(xmin-10, xmax + 0.1*abs(xmax))
    plt.ylim(ymin, ymax + 0.1*abs(ymax))
    plt.show()

  
plot_tree(P)