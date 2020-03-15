package data;

import java.util.ArrayList;
import java.util.List;

public class BuildingEnvironment {

	private List<Building> buildings;
	public List<Building> lowRiskBuildings = new ArrayList<Building>();
	public List<Building> highRiskBuildings = new ArrayList<Building>();
	private float threshold;
	
	public BuildingEnvironment(List<Building> buildings, float threshold) {
		
		this.buildings = buildings;
		this.threshold = threshold;
		for (Building b: buildings) {
			
			if ((float) b.getHeight() > threshold) {
				
				highRiskBuildings.add(b);
			}
			else {
				
				lowRiskBuildings.add(b);
			}
		}
		
	}
	
	
}
