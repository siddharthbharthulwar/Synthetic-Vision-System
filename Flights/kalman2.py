import numpy as np
from scipy.stats import multivariate_normal as mvn
import matplotlib.pyplot as plt
class trajectory3D():
    
    def __init__(self, seed=123, ndat=100, delta_time = 0.1, q=2., r=0.5):        
        self.ndat = ndat
        self.seed = seed
        self.q = q
        self.dt = delta_time
        dt = self.dt
        self.r = r
        self.A = np.array([
            [1, 0, 0, dt, 0, 0],
            [0, 1, 0, 0, dt, 0],
            [0, 0, 1, 0, 0, dt],
            [0, 0, 0, 1, 0, 0],
            [0, 0, 0, 0, 1, 0],
            [0, 0, 0, 0, 0, 1]
        ])
        self.Q = self.q * np.diag([dt, dt, dt, dt, dt, dt])
        self.H = np.array([
            [1, 0, 0, 0, 0, 0],
            [0, 1, 0, 0, 0, 0],
            [0, 0, 1, 0, 0, 0]
        ])
        self.R = self.r**2 * np.eye(3)
        
        self.m0 = np.array([0., 0., 0., 1., 1., 1.])
        self.X = np.zeros(shape=(self.A.shape[0], self.ndat))
        self.Y = np.zeros(shape=(self.H.shape[0], self.ndat))
        self._simulate()
        
    def _simulate(self):
        np.random.seed(self.seed)
        
        x = self.m0;
        for t in range(self.ndat):
            q = mvn.rvs(cov=self.Q)
#            print('q:\n', q)
            x = self.A.dot(x) + q
#            print('x: \n', x)
            y = self.H.dot(x) + mvn.rvs(cov=self.R)
#            print('y:\n', y)
            self.X[:,t] = x.flatten()
            self.Y[:,t] = y.flatten()
        plt.plot(self.X)
        plt.plot(self.Y)
        plt.show()
#            print('-------')


ter = trajectory3D()