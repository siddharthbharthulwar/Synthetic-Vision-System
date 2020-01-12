package data;

import java.util.ArrayList;
import java.util.List;

public class Building {

	private int height;
	private List<Point> corners;
	
	public Building(int height, List<Point> corners) {
		setHeight(height);
		setCorners(corners);
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<Point> getCorners() {
		return corners;
	}
	public void setCorners(List<Point> corners) {
		this.corners = corners;
	}
	
	public float[] floatVertProcess() {
		
		List<Point> points = this.getCorners();
		float height = this.getHeight();
		
		float[] fin = new float[points.size() * 6];
		ArrayList<Float> bef = new ArrayList<Float>();
		for (Point p: points) {
			bef.add(p.getX());
			bef.add(p.getY());
			bef.add((float) 0);
			
			bef.add(p.getX());
			bef.add(p.getY());
			bef.add(height);
		}
		
		int count = 0;
		while (count < bef.size()) {
			fin[count] = bef.get(count);
			count +=1;
		}
		System.out.println(bef);
		return fin;
	}
}
