import numpy as np 
from scipy.stats import entropy
from math import log, e
import pandas as pd 



def calc_entropy_1d(labels, base = None):
    value, counts = np.unique(labels, return_counts = True)
    return entropy(counts, base = base)

