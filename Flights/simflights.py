import numpy as np 
import matplotlib.pyplot as plt 
from mpl_toolkits.mplot3d import Axes3D

class SimFlight:
    #class for a grid of rastered array tiff files
    def __init__(self, path):

        self.totalTime = []
        self.vIndMph = []
        self.qRad = []
        self.pRad = []
        self.rRad = []
        self.pitch = []
        self.roll = []
        self.heading = []
        self.lat = []
        self.lon = []
        self.altitude = []
        self.vX = []
        self.vY = []
        self.vZ = []

        self.path = path

        self.readFile()

    def readFile(self):

        with open(self.path) as fp:

            line = fp.readline()
            cnt = 1

            while line:
                if (cnt > 1):

                    #print("Line{}: {}".format(cnt, line.strip()))
                    line = fp.readline()

                    currentIn = line.split('|')

                    for i in range(len(currentIn) - 1):
                        current = float(currentIn[i])
                        if (i == 1):
                            self.totalTime.append(current)
                        if (i == 11):
                            self.vIndMph.append(current)                    
                        if (i == 14):
                            self.qRad.append(current)
                        if (i == 15):
                            self.pRad.append(current)
                        if (i == 16):
                            self.rRad.append(current)
                        if (i == 17):
                            self.pitch.append(current)
                        if (i == 18):
                            self.roll.append(current)
                        if (i == 19):
                            self.heading.append(current)
                        if (i == 26):
                            self.lat.append(current)
                        if (i == 27):
                            self.lon.append(current)
                        if (i == 29):
                            self.altitude.append(current)
                        if (i == 37):
                            self.vX.append(current)
                        if (i == 38):
                            self.vY.append(current)
                        if (i == 39):
                            self.vZ.append(current)
                    cnt += 1
                else:
                    line = fp.readline()
                    cnt +=1
    def positionPlot(self):
        fig = plt.figure()
        ax = fig.gca(projection = '3d')
        ax.plot(self.lon, self.lat, self.altitude)
        plt.show()
    def velocityPlot(self):
        fig = plt.figure()
        ax = fig.gca(projection = '3d')
        ax.plot(self.vX, self.vY, self.vZ)
        plt.show()
sim = SimFlight(r"C:\Users\siddh\Projects\Synthetic Vision System\Flights\sim\f2.txt")
sim.positionPlot()
sim.velocityPlot()
