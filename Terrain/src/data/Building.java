package data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import util.PolygonTriangulationUtil;

public class Building {

	private int height;
	private List<normPoint> corners;
	public List<Float> vertices;
	public List<Integer> indices;
	public List<Vector3f> vertexNormals;
	
	
	
	public Building(int height, List<normPoint> corners) {
		setHeight(height);
		setCorners(corners);
		generateVertices();
		generateIndices();
		//System.out.println("Building(...) parameter constructor..... " + this.vertices + " and " + this.indices);
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
	
	public void generateVertices(){
		System.out.println("Genvertices called");
		
		List<normPoint> points = this.getCorners();
		float height = this.getHeight();
		
		float[] fin = new float[points.size() * 6];
		ArrayList<Float> bef = new ArrayList<Float>();
		for (normPoint p: points) {
			bef.add(p.getX());
			bef.add((float) 0);

			bef.add(p.getY());
			
			bef.add(p.getX());
			bef.add(height);

			bef.add(p.getY());
		}
		
		this.vertices = bef;
		
	}
	public static Vector3f normalize(Vector3f init) {
			
			float x = init.getX();
			float y = init.getY();
			float z = init.getZ();
			
			float squaredsum = (x * x) + (y * y) + (z * z);
			float magnitude = (float) Math.sqrt(squaredsum);
			
			return new Vector3f(x / magnitude, y / magnitude, z / magnitude);
		
	}
	
	
	public void generateIndices() {
		System.out.println("genindices called");
		List<Integer> continuousIndices = new ArrayList<Integer>();
		for (int i = 0; i < this.corners.size() - 1; i++) {
			
			
			int indX1 = (2 * i) + 1;
			int indY1  = 2 * i;
			int indZ1  = (2 * i) + 3;
			
			int indX2 = 2 * i;
			int indY2 = (2 * i) + 2;
			int indZ2 = (2 * i) + 3;
			
			continuousIndices.add(indX1);
			continuousIndices.add(indY1);
			continuousIndices.add(indZ1);
			
			continuousIndices.add(indX2);
			continuousIndices.add(indY2);
			continuousIndices.add(indZ2);
			
			
			
		}
		
		//System.out.println(continuousIndices);
		
		int finalInd = this.corners.size() - 1;
		
		continuousIndices.add((2 * finalInd) + 1);
		continuousIndices.add(2 * finalInd);
		continuousIndices.add(1);
		
		continuousIndices.add(2 * finalInd);
		continuousIndices.add(0);
		continuousIndices.add(1);
		
		
		
		List<Point> truePoints = new ArrayList<Point>();
		for (normPoint p: corners) {
			Point n = new Point();
			n.setLocation(p.getX(), p.getY());
			truePoints.add(n);
			
		}
		
	//	System.out.println(" >>>>> truePoints SIZE = " + truePoints.size());
		List<Integer> roofIndices = PolygonTriangulationUtil.getPolygonTriangulationIndices(truePoints, true);
		
	//	System.out.println("SUCCESSFUL");
		
		

		
		for (int i = 0; i < roofIndices.size(); i ++) {
			roofIndices.set(i, (roofIndices.get(i) * 2) + 1);
		}
		
		continuousIndices.addAll(roofIndices);
		this.indices = continuousIndices;
	
	}
	
	public float[] getVertices() {
		float[] ret = new float[this.vertices.size()];
		for (int i = 0; i < this.vertices.size(); i++) {
			ret[i] = this.vertices.get(i);
			
		}
		return ret;
	}
	
	public int[] getIndices() {
		
		int[] ret = new int[this.indices.size()];
		for (int i = 0; i < this.indices.size(); i++) {
			ret[i] = this.indices.get(i);
			
		}
		return ret;
	}
	
	public Vector3f vertexAt(int index) {
		
		return new Vector3f(this.vertices.get(index * 3), this.vertices.get((index * 3) + 1), this.vertices.get((index * 3) + 2));
		
	}
	
	public void generateVectorNormals(){
		
		List<Vector3f> vertexNormals = new ArrayList<Vector3f>();
		
		for (int i = 0; i < this.indices.size(); i +=3) {
			
			//System.out.println(this.indices.get(i) + ", " + this.indices.get(i + 1) + ", " + this.indices.get(i + 2));
			
			int vertA = this.indices.get(i);
			int vertB = this.indices.get(i + 1);
			int vertC = this.indices.get(i + 2);
			
			Vector3f posA = this.vertexAt(vertA);
			Vector3f posB = this.vertexAt(vertB);
			Vector3f posC = this.vertexAt(vertC);
			

			Vector3f edgeAB = Vector3f.sub(posA, posB, null);
			Vector3f edgeAC = Vector3f.sub(posA, posC, null);
			
			Vector3f cross = Vector3f.cross(edgeAB, edgeAC, null);
			
			cross = normalize(cross);
			
			vertexNormals.add(cross);
		//	vertexNormals.add(cross);
		//	vertexNormals.add(cross);
			
			
			
		}
		
		
		this.vertexNormals = vertexNormals;
		
	}
	
	public float[] getVertexNormals() {
		
		float[] fin = new float[this.vertexNormals.size() * 3];
		List<Float> temp = new ArrayList<Float>();
		
		for (int i = 0; i < this.vertexNormals.size(); i++) {
			temp.add(this.vertexNormals.get(i).getX());
			temp.add(this.vertexNormals.get(i).getY());
			temp.add(this.vertexNormals.get(i).getZ());
		}
		
		for (int j = 0; j < temp.size(); j++) {
			fin[j] = temp.get(j);
		}
		System.out.println(fin.length);
		return fin;
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
		
		
		Building b = new Building(5, p);
		
	}
}
