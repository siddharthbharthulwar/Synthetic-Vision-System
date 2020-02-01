package guidance;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entities.Runway;

public class glideSlopeMap {

	private List<guidingBox> boxes = new ArrayList<guidingBox>();
	private float separation;
	
	public glideSlopeMap(Runway runway, float separation, int frequency) {
		
		Vector3f anchorPoint = runway.getMidpoint();
		this.separation = separation;
		
		for (int i = 2; i < frequency + 2; i++) {
			
			Vector3f point = new Vector3f(runway.guidelineDownDistance(i * separation).getX(), 20, runway.guidelineDownDistance(i * separation).getY());
			this.boxes.add(new guidingBox(1000, point, 100, 25));
			
		}
		
		
	}
	
	public List<guidingBox> getBoxes(){
		return this.boxes;
	}
	
	
}
