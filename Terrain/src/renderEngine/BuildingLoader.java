package renderEngine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import data.Building;
import data.Point;
import data.Point3D;
import models.RawModel;

public class BuildingLoader {

	
	public static List<Point3D> loadBuildingToModel(Building building) {
		
		List<Point> corners = building.getCorners();
		System.out.println(corners);
		List<Point3D> corners3D = new ArrayList<Point3D>();
		int height = building.getHeight();
		
		for (Point p: corners) {
			corners3D.add(p.convertTo3D(0));
			corners3D.add(p.convertTo3D(height));
		}
		
		
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> textures = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		
		
		
		float[] verticesArray = null;
		float[] textureCoordsArray = null;
		float[] normalsArray = null;
		int[] indicesArray = null;
		
		return corners3D;
		
		
	}
	
	public static void main(String[] args) {
		List<Point> corners = new ArrayList<Point>();
		corners.add(new Point(150, 150));
		corners.add(new Point(100, 150));
		corners.add(new Point(100, 100));
		corners.add(new Point(150, 100));
		Building b = new Building(6, corners);
		System.out.println(loadBuildingToModel(b));

		
	}
	
}
