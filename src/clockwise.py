import numpy as np 
import matplotlib.pyplot as plt 
import tsp

a = [(1,3), (2,1), (1,1), (2,3), (3,2)]

t = tsp.tsp(a)

print(t)