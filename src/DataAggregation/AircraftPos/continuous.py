import math
import pandas as pd 

flightPath = "D:\\Documents\\School\\2019-20\\ISEF 2020\\FR24\\HV5022_228b0df3.csv"

class Flight:
    def __init__(self, pathVal):
        self.path = pathVal
    

f1 = Flight(flightPath)
print(f1.path)
        