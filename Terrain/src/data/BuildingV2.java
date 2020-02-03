package data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import util.PolygonTriangulationUtil;

public class BuildingV2 {

	private int height;
	private List<normPoint> corners;
	public List<Float> vertices;
	public List<Integer> indices;
	public List<Vector3f> vertexNormals;
	
	
	
	public BuildingV2(int height, List<normPoint> corners) {
		setHeight(height);
		setCorners(corners);		
		
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = (2 * height) + 10;
	}
	public List<normPoint> getCorners() {
		return corners;
	}
	public void setCorners(List<normPoint> corners) {
		this.corners = corners;
	}
	
	
	
	public static float[] vecToArray(List<Vector3f> listOfVectors) {
		
		List<Float> temp = new ArrayList<Float>();
		float[] ret = new float[listOfVectors.size() * 3];
		
		for (int i = 0; i < listOfVectors.size(); i++) {
			Vector3f vec = listOfVectors.get(i);
			temp.add(vec.getX());
			temp.add(vec.getY());
			temp.add(vec.getZ());
		}
		
		for (int i = 0; i < temp.size(); i++) {
			
			ret[i] = temp.get(i);
		}
		return ret;
		
	}
	public static int[] intToArray(List<Integer> listOfInts) {
		int[] ret = new int[listOfInts.size()];
		
		for (int i = 0; i < listOfInts.size(); i++) {
			ret[i] = listOfInts.get(i);
		}
		return ret;
	}
	
	public static Vector3f normalize(Vector3f init) {
		
		
			
		float x = init.getX();
		float y = init.getY();
		float z = init.getZ();
		
		float squaredsum = (x * x) + (y * y) + (z * z);
		float magnitude = (float) Math.sqrt(squaredsum);
		
		if (magnitude == 0) {
			return init;
		}
		
		return new Vector3f(x / magnitude, y / magnitude, z / magnitude);
		
	}
	
	public List<Vector3f> generateVertices() {
		
		List<Vector3f> vec = new ArrayList<Vector3f>();
		normPoint init = this.corners.get(0);
		
		//SIDE FACES
		vec.add(new Vector3f(init.getX(), 0, init.getY()));
		vec.add(new Vector3f(init.getY(), this.height, init.getY()));
		
		for (int i = 1; i < this.corners.size(); i++) {
			normPoint corner = this.corners.get(i);
			vec.add(new Vector3f(corner.getX(), 0, corner.getY()));
			vec.add(new Vector3f(corner.getX(), this.height, corner.getY()));
			
			vec.add(new Vector3f(corner.getX(), 0, corner.getY()));
			vec.add(new Vector3f(corner.getX(), this.height, corner.getY()));
		}
		
		vec.add(new Vector3f(init.getX(), 0, init.getY()));
		vec.add(new Vector3f(init.getY(), this.height, init.getY()));
		//END SIDE FACES
		
		for (normPoint point: this.corners) {
			vec.add(new Vector3f(point.getX(), this.height, point.getY()));
		}
		
		return vec;
		
	}
	
	public List<Integer> generateIndices(){
		
		List<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < this.corners.size(); i++) {
			
			int iterate = i * 4;
			indices.add(iterate + 1);
			indices.add(iterate);
			indices.add(iterate + 3);
			indices.add(iterate);
			indices.add(iterate + 2);
			indices.add(iterate + 3);
			
			
		}
		
		List<Point> truePoints = new ArrayList<Point>();
		for (normPoint p: this.corners) {
			
			Point n = new Point();
			n.setLocation(p.getX(), p.getY());
			truePoints.add(n);
		}
		List<Integer> roofIndices = PolygonTriangulationUtil.getPolygonTriangulationIndices(truePoints, true);
		for (int i = 0; i < roofIndices.size(); i++) {
			roofIndices.set(i, roofIndices.get(i) + ((4 * this.corners.size())));
		}
		indices.addAll(roofIndices);
		return indices;
		
	}
	
	public List<Vector3f> generateVertexNormals(){
		
		List<Vector3f> normVertices = this.generateVertices();
		List<Vector3f> vertexNormals = new ArrayList<Vector3f>();
		for (int i = 0; i < normVertices.size() - 2; i+=3) {
			System.out.println(i);
			Vector3f posA = normVertices.get(i);
			Vector3f posB = normVertices.get(i + 1);
			Vector3f posC = normVertices.get(i + 2);
			
			Vector3f edgeAB = Vector3f.sub(posA, posB, null);
			Vector3f edgeBC = Vector3f.sub(posA, posC, null);
			
			Vector3f cross = Vector3f.cross(edgeAB, edgeBC, null);
			cross = normalize(cross);
			
			vertexNormals.add(cross);
			vertexNormals.add(cross);
			vertexNormals.add(cross);
		}
		
		return vertexNormals;
	}
	
	public String toString() {
		
		String s = "";
		s += "Height: " + this.height;
		s += ", Corners: [";
		
		for (normPoint p: this.corners) {
			s += p;
			s += ", ";
			
		}
		
		s += "]";
		
		return s;
		
	}
	
	public static void main(String[] args) {
		
		List<normPoint> p = new ArrayList<normPoint>();
		p.add(new normPoint(0, 0));
		p.add(new normPoint(150, 0));
		p.add(new normPoint(150, 150));
		p.add(new normPoint(0, 150));
		
		
		BuildingV2 b = new BuildingV2(5, p);
		System.out.println(b.generateVertexNormals());
		
	}
}
