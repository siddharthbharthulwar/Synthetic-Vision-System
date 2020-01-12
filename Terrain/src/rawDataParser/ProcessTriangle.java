package rawDataParser;

import java.util.List;

import data.Point;
import data.Point3D;

public class ProcessTriangle {
	


	public static float[] process(List<Point> points, float height) {
		
		Point3D one = new Point3D(points.get(0).getX(), points.get(0).getY(), 0);
		Point3D two = new Point3D(points.get(1).getX(), points.get(1).getY(), 0);
		
		Point3D oneHeight = new Point3D(points.get(0).getX(), points.get(0).getY(), height);
		Point3D twoHeight = new Point3D(points.get(1).getX(), points.get(1).getY(), height);
		
		float[] vertices = {one.getX(), one.getY(), one.getZ(), two.getX(), two.getY(), two.getZ(), 
				twoHeight.getX(), twoHeight.getY(), twoHeight.getZ(), oneHeight.getX(), oneHeight.getY(), oneHeight.getZ()};
		
		return vertices;
		
		
	}
}
