import srtm

geo_elevation_data = srtm.get_data()
image = geo_elevation_data.get_image((500, 500), (39, 40), (104, 105), 5000)
image.show()