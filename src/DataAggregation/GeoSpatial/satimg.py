import aggregation as ag
import matplotlib as plt


a = "D:\\Documents\\School\\2019-20\\ISEF 2020\\SatelliteImagery\\3601228_n1e226\\CO\\2014\\201403_denver_area02_co_0x1500m_utm_cnir\\vol001\\n1e226.tif"

p1 = ag.TerrainGrid(a, (1,1), 0)
p1.show('gray')