import numpy as np 
from pyqtgraph import QtCore, QtGui
import pyqtgraph.opengl as gl
import sys
from opensimplex import OpenSimplex
import aggregation as ag 
import matplotlib.pyplot as plt


DSM9 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ2\\r5_37fz2.tif"
DSM6 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN2\\r5_37fn2.tif"
DSM8 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FZ1\\r5_37fz1.tif"
DSM7 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EZ2\\r5_37ez2.tif"
DSM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37EN2\\r5_37en2.tif"
DSM5 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\AHN\\R5_37FN1\\r5_37fn1.tif"

SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"

eham1 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25CN2\\r5_25cn2.tif"
eham2 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25DN1\\r5_25dn1.tif"
eham3 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25DN2\\r5_25dn2.tif"
eham4 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25CZ2\\r5_25cz2.tif"
eham5 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25DZ1\\r5_25dz1.tif"
eham6 = "D:\\Documents\School\\2019-20\\ISEF 2020\\AHNEHAM\\R5_25DZ2\\r5_25dz2.tif"


DENSRTM = "D:\\Documents\School\\2019-20\\ISEF 2020\\SRTM\\n39_w105_1arc_v3.tif"


class Terrain(object):
    def __init__(self):
        self.app = QtGui.QApplication(sys.argv)
        self.w = gl.GLViewWidget()
        self.w.setGeometry(0, 110, 1920, 1080)
        self.w.show()
        self.w.setWindowTitle("Terrain")
        self.w.setCameraPosition(distance=30, elevation=5)

        grid = gl.GLGridItem()
        grid.scale(12,12,12)
        self.w.addItem(grid)

        self.nsteps = 1
        self.ypoints = range(-200, 500, self.nsteps)
        self.xpoints = range(-200, 500, self.nsteps)

        self.nfaces = len(self.ypoints)


        self.act = ag.TerrainGrid((DSM7), (1,1), 0)
        print(self.act.arrayValues.shape)
        self.tmp = self.act.arrayValues[0:700, 0:700]


        verts = np.array([[x, y, self.tmp[n, m]] for n, x in enumerate(self.xpoints) for m, y in enumerate(self.ypoints)], dtype = np.float32)

        faces = []
        colors = []
        edgeColors = []

        for m in range(self.nfaces - 1):
            yoff = m * self.nfaces
            for n in range(self.nfaces - 1):
                faces.append([n + yoff, yoff + n + self.nfaces, yoff + n + self.nfaces + 1])
                faces.append([n + yoff, yoff + n + 1, yoff + n + self.nfaces + 1])
                colors.append([0,200,0,1])
                colors.append([0,200,0,1])

        print("done with appending")
        faces = np.array(faces)
        colors = np.array(colors)
        self.m1 = gl.GLMeshItem(
            vertexes = verts,
            faces = faces,
            faceColors = colors,
            edgeColor = ([1, 200, 1, 0.25]),
            smooth = True,
            drawEdges = True
        )

        self.m1.setGLOptions('additive')
        self.w.addItem(self.m1)

    def start(self):
        if (sys.flags.interactive != 1) or not hasattr(QtCore, 'PYQT_VERSION'):
            QtGui.QApplication.instance().exec_()


if __name__ == '__main__':
    t = Terrain()
    t.start()

'''

agt = ag.TerrainGrid((SRTM3), (1,1), 0)
bgt = agt.arrayValues[1000:1020, 1000:1020]

gList = []

def iterate(array):
    count = 0
    print(array.shape[0], array.shape[1])
    while count < array.shape[0]:
        count1 = 0
        while count1 < array.shape[1]:
            gList.append(array[count, count1])
            count1 = count1 + 1
        count = count + 1


iterate(bgt)
print(bgt[5, 0])
plt.imshow(bgt)
plt.show()
'''