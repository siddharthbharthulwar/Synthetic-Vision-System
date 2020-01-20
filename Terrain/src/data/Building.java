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
		this.height = 2000 + height;
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
			bef.add((float) 0);

			bef.add(p.getY());
			
			bef.add(p.getX());
			bef.add(height);

			bef.add(p.getY());
		}
		
		int count = 0;
		while (count < bef.size()) {
			fin[count] = bef.get(count);
			count +=1;
		}
		return fin;
	}
	
	public int[] generateIndices() {
		
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
		
		
		//to be implemented with the ear cutting algorithm
		
		
		for (int j = 4; j < 2 * this.corners.size(); j++) {
			
			if (j % 2 == 1) {
				continuousIndices.add(1);
				continuousIndices.add(j - 2);
				continuousIndices.add(j);
			}
		}
		
		
		int[] finalIndices = new int[continuousIndices.size()];
		
		for (int j = 0; j < continuousIndices.size(); j++) {
			finalIndices[j] = continuousIndices.get(j);
		}
		
		
		return finalIndices;
		
	}
	
	public String toString() {
		
		String s = "";
		s += "Height: " + this.height;
		s += ", Corners: [";
		
		for (Point p: this.corners) {
			s += p;
			s += ", ";
			
		}
		
		s += "]";
		
		return s;
		
	}
}
