import numpy as np
from pyqtgraph.Qt import QtCore, QtGui
import pyqtgraph.opengl as gl 
import sys

class Terrain(object):
    def __init__(self):
        self.app = QtGui.QApplication(sys.argv)
        self.w = gl.GLViewWidget()
        self.w.setGeometry(0, 110, 1920, 1080)
        self.w.show()
        self.w.setWindowTitle('Synthetic Vision System')
        self.w.setCameraPosition(distance = 30, elevation = 9)

        grid = gl.GLGridItem()
        grid.scale(2,2,2)
        self.w.addItem(grid)
    

    def start(self):
        if (sys.flags.interactive != 1) or not hasattr(QtCore, 'PYQT_VERSION'):
            QtGui.QApplication.instance().exec_()

if __name__ == '__main__':
    t = Terrain()
    t.start()