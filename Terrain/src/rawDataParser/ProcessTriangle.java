package rawDataParser;

import java.util.ArrayList;
import java.util.List;

import data.Building;
import data.Point;
import data.Point3D;

public class ProcessTriangle {
	
	public static float[] floatVertProcess(Building building) {
		
		List<Point> points = building.getCorners();
		float height = building.getHeight();
		
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
	


	public static void main(String[] args) {
		List<Point> points = new ArrayList<Point>();
		points.add(new Point(200, 300));
		points.add(new Point(250,500));
		points.add(new Point(200, 500));
		points.add(new Point(250, 300));
		Building b = new Building(4, points); 
		float[] f = floatVertProcess(b);
		
	}
}
