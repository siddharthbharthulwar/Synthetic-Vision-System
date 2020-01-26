package guidance;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entities.Runway;

public class glideSlopeMap {
	
	private Vector3f anchor;
	private float separation;
	private List<guidingBox> guideMap;
	
	public glideSlopeMap(Runway runway, float separation) {
		
		this.anchor = runway.getMidpoint();
		this.separation = separation;
		
		
	}
	

	/*
	public static List<guidingBox> createGlideslopeIndicator(int totalDistance, int stepSize){
		
		int count = 0;
		while (count < totalDistance) {
			
		}
		
	}
	
	*/
	
}
