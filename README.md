# Synthetic-Vision-System
 The goal of this project is to create a navigational and visual aid for commercial pilots landing in low visibility conditions. The first part of the project consists of parsing geospatial LiDAR data into 3d objects through a novel thresholding technique and novel image segmentation algorithms based on unsupervised learning. The aggregated spatial data will then be used to generate synthetic imagery replicating the environment surrounding the aircraft. Data from other sources (ADS-B and inertial data from the cockpit) will be visualized as well. The first part of the project will be written in Python 3.6 and the second part will use Modern OpenGL from the Lightweight Java Game Library (LWJGL 2) in Java. Interpolation algorithms for the initial processing of LiDAR point clouds are written in C++ but are not included in this repository. 
 
The neural networks and Kalman filters are not found on this repository, but most of the spatial mapping algorithms and synthetic vision are. 
 
 Dependencies:
 - Python 3.6
 - NumPY
 - Rasterio
 - Matplotlib
 - OpenCV
 - SciPy
 - Mayavi
 - GDAL
 - LWJGL 2
 - TSP
