import numpy as np 
from pyqtgraph import QtCore, QtGui
import pyqtgraph.opengl as gl
import sys
from opensimplex import OpenSimplex
import aggregation as ag 
import matplotlib.pyplot as plt


SRTM1 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e004_1arc_v3.tif"
SRTM2 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n52_e005_1arc_v3.tif"
SRTM3 = "D:\\Documents\\School\\2019-20\\ISEF 2020\SRTM\\n51_e004_1arc_v3.tif"
SRTM4 = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SRTM\\n51_e005_1arc_v3.tif"

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
        self.ypoints = range(-200, 220, self.nsteps)
        self.xpoints = range(-200, 220, self.nsteps)

        self.nfaces = len(self.ypoints)
        print(len(self.ypoints))

        self.act = ag.TerrainGrid((SRTM3), (1,1), 0)
        self.tmp = self.act.arrayValues[2100:2520, 600:1020]
        plt.imshow(self.tmp)
        plt.show()


        verts = np.array([[x, y, self.tmp[n, m]] for n, x in enumerate(self.xpoints) for m, y in enumerate(self.ypoints)], dtype = np.float32)

        faces = []
        colors = []

        for m in range(self.nfaces - 1):
            yoff = m * self.nfaces
            for n in range(self.nfaces - 1):
                faces.append([n + yoff, yoff + n + self.nfaces, yoff + n + self.nfaces + 1])
                faces.append([n + yoff, yoff + n + 1, yoff + n + self.nfaces + 1])
                colors.append([0,0,0,1])
                colors.append([0,0,0,1])

        
        faces = np.array(faces)
        colors = np.array(colors)
        self.m1 = gl.GLMeshItem(
            vertexes = verts,
            faces = faces, 
            faceColors = colors,
            smooth = False,
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

