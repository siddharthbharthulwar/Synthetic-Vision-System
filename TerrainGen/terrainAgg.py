import numpy as np
from pyqtgraph.Qt import QtCore, QtGui
import pyqtgraph.opengl as gl
import time, sys


class Terrain(object):
    def __init__(self):
        self.app = QtGui.QApplication(sys.argv)
        self.w = gl.GLViewWidget()
        self.w.setGeometry(0, 1100, 1920, 1080)
        self.w.show()
        self.w.setWindowTitle('Synthetic Vision System')
        self.w.setCameraPosition(distance = 30, elevation = 8)

        grid = gl.GLGridItem()
        grid.scale(2,2,2)
        self.w.addItem(grid)
    

    def start(self):
        if (sys.flags.interactive != 1) or not hasattr(QtCore, 'PYQT_VERSION'):
            QtGui.QApplication.instance().exec_()

    def up(self):
        t = 0
        n = 9
        while t < 100:
            self.w.setCameraPosition(distance = 30, elevation = n)
            n = n + 0.1
            t = t + 1
            time.sleep(0.1)



if __name__ == '__main__':
    t = Terrain()
    t.start()