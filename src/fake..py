import matplotlib.pyplot as plt

a = [0, 0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.1]
b = [100, 99.56, 98.56, 98.51, 97.77, 95.11, 92.44, 86.54, 82.11, 77.6, 69.0]

plt.plot(a, b)
plt.title("Spatial Accuracy vs Epsilon")
plt.xlabel("Epsilon")
plt.ylabel("Intersection over Union Accuracy")
plt.show()