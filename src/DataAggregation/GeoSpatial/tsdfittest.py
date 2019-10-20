import tsd
lat, lon = 42, 3
aoi = tsd.utils.geojson_geometry_object(lat, lon, 5000, 5000)

# search Landsat-8 images available on the AOI with Development Seed's API
x = tsd.search_devseed.search(aoi, satellite='Landsat-8')