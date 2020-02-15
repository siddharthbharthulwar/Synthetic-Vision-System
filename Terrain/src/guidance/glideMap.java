package guidance;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entities.RunwayV2;

public class glideMap {

	public List<guidingBox> boxes;
	private RunwayV2 runway;
	private int number;
	private float distance;
	public float angle;
	
	private float width;
	private float thickness;
	private float size;
	
	public glideMap(RunwayV2 runway, int number, float distance, float angle, float size, float width, float thickness) {
		
		this.runway = runway;
		this.number = number;
		this.distance = distance;
		this.angle = angle;
		this.width = width;
		this.size = size;
		this.thickness = thickness;
		this.boxes = generateBoxes();
		
		
	}
	
	public List<guidingBox> generateBoxes(){
		
		float localAngle = (float) Math.toRadians(this.angle);
		Vector3f anchor3 = new Vector3f(this.runway.anchor1.getX(), this.runway.elevation, this.runway.anchor1.getY());
		Vector3f anchor4 = new Vector3f(this.runway.anchor2.getX(), this.runway.elevation, this.runway.anchor2.getY());
		Vector3f midpoint = new Vector3f((anchor3.getX() + anchor4.getX()) / 2, this.runway.elevation, anchor3.getZ());
		
		List<guidingBox> ret = new ArrayList<guidingBox>();
		
		for (int i = 0; i < this.number; i++) {
			float x = (float) (this.distance * Math.tan(localAngle));
			Vector3f centerPos = new Vector3f(midpoint.getX(), midpoint.getY() + i * x + 300, midpoint.getZ() + i * this.distance);
			ret.add(new guidingBox(this.size, centerPos, this.width, this.thickness));
			
		}
		return ret;
		
	}
	
}
