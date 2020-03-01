import pandas
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

colnames = ['Timestamp', 'UTC', 'Callsign', 'Position', 'Altitude', 'Speed', 'Direction']

data = pandas.read_csv(r"C:\Users\siddh\Projects\Synthetic Vision System\Flights\f1.csv", names=colnames)

positions = data.Position.tolist()
import pandas
import matplotlib.pyplot as plt

colnames = ['Timestamp', 'UTC', 'Callsign', 'Position', 'Altitude', 'Speed', 'Direction']

data = pandas.read_csv(r"C:\Users\siddh\Projects\Synthetic Vision System\Flights\f1.csv", names=colnames)

positions = data.Position.tolist()
altitudes = data.Altitude.tolist()
print(len(positions))
print(len(altitudes))
latitudes = []
longitudes = []
elevations = []
for i in range(1, len(positions)):
    position = positions[i].split(",")
    longitudes.append(float(position[0]))
    latitudes.append(float(position[1]))
    elevations.append(float(altitudes[i]))

fig = plt.figure()
ax = fig.gca(projection = '3d')
ax.plot(longitudes, latitudes, elevations)
plt.show()