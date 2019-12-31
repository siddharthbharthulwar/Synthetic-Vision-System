from OpenGL.GL import *
import numpy as np 


IMAGE_SIZE = 256


class landscape:
    def __init__(self):
        self.pixels = np.zeros(shape=(IMAGE_SIZE, IMAGE_SIZE))
        self.verts = []
        