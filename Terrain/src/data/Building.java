package data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import util.PolygonTriangulationUtil;

public class Building {

	private int height;
	private List<normPoint> corners;
	
	public Building(int height, List<normPoint> corners) {
		setHeight(height);
		setCorners(corners);
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = 2000 + height;
	}
	public List<normPoint> getCorners() {
		return corners;
	}
	public void setCorners(List<normPoint> corners) {
		this.corners = corners;
	}
	
	public float[] floatVertProcess() {
		
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
		
		/*
		for (int j = 4; j < 2 * this.corners.size(); j++) {
			
			if (j % 2 == 1) {
				continuousIndices.add(1);
				continuousIndices.add(j - 2);
				continuousIndices.add(j);
			}
		}
		*/
		List<Point> truePoints = new ArrayList<Point>();
		for (normPoint p: corners) {
			Point n = new Point();
			n.setLocation(p.getX(), p.getY());
			truePoints.add(n);
			
		}
		
		System.out.println(" >>>>> truePoints SIZE = " + truePoints.size());
		List<Integer> roofIndices = PolygonTriangulationUtil.getPolygonTriangulationIndices(truePoints, true);
		
		System.out.println("SUCCESSFUL");
		
		
//to be implemented with the ear cutting algorithm

		/*
		for (int j = 4; j < 2 * this.corners.size(); j++) {
			
			if (j % 2 == 1) {
				continuousIndices.add(1);
				continuousIndices.add(j - 2);
				continuousIndices.add(j);
			}
		}
		*/
		
		for (int i = 0; i < roofIndices.size(); i ++) {
			roofIndices.set(i, (roofIndices.get(i) * 2) + 1);
		}
		
		continuousIndices.addAll(roofIndices);
		
		
		int[] finalIndices = new int[continuousIndices.size()];
		
		for (int j = 0; j < continuousIndices.size(); j++) {
			finalIndices[j] = continuousIndices.get(j);
		}
		
		System.out.println("RETURN SUCCESSFUL");
		return finalIndices;
	
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
}
