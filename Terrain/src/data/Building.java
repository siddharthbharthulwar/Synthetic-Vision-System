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
	
	public int[] generateIndices() {
		
		List<Integer> continuousIndices = new ArrayList<Integer>();
		for (int i = 0; i < this.corners.size(); i++) {
			
			
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
		
		int[] finalIndices = new int[continuousIndices.size()];
		
		for (int j = 0; j < continuousIndices.size(); j++) {
			finalIndices[j] = continuousIndices.get(j);
		}
		System.out.println(continuousIndices);
		return finalIndices;
		
	}
}
