import math
import pandas

flightPath = "D:\\Documents\\School\\2019-20\\ISEF 2020\\FR24\\HV5022_228b0df3.csv"

class Flight:
    def __init__(self, pathVal):
        self.path = pathVal
        temp = pandas.read_csv(self.path)
        self.heading = temp['Direction']
        self.speed = temp['Speed']
        self.altitude = temp['Altitude']
        self.pos = temp['Position']
        self.time = temp['Timestamp']

    

f1 = Flight(flightPath)
print(f1.altitude)


        