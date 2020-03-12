package paths;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class Path {

	public List<Vector3f> points;
	
	public float[] vertices;
	public int[] indices;
	
	private List<Vector3f> pointVertices;
	
	public float[] normals = {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
	
	public Path(List<Vector3f> points) {
		
		this.points = points;
		this.generateVertices();
		this.generateIndices();
		
	}

	public void generateVertices() {
		
		List<Float> floatVertices = new ArrayList<Float>();
		
		for (Vector3f vert: this.points) {
			
			floatVertices.add(vert.getX());
			floatVertices.add(vert.getY());
			floatVertices.add(vert.getZ());
		}
		
		float[] ret = new float[floatVertices.size()];
		
		System.out.println(floatVertices);
		for (int i = 0; i < floatVertices.size(); i++) {
			
			ret[i] = floatVertices.get(i);
		}
		
		this.vertices = ret;
	}
	
	public void generateIndices() {
		
		List<Integer> listInteger = new ArrayList<Integer>();
		for (int i = 0; i < this.points.size(); i++) {
			
			listInteger.add(i);
			listInteger.add(i + 1);
		}
		int[] indices = new int[listInteger.size()];

		System.out.println(listInteger);
		for (int i = 0; i < listInteger.size(); i++) {
			
			indices[i] = listInteger.get(i);
		}
		this.indices = indices;
	}
	
	
	/*
	public static void main(String[] args) {
		
		List<Vector3f> points = new ArrayList<Vector3f>();
		
		points.add(new Vector3f(0, 0, 0));
		//points.add(new Vector3f(50, 50, 50));
		points.add(new Vector3f(50, 0, 100));
		
		Path path = new Path(points);
		
	}
	
	*/
	
}
