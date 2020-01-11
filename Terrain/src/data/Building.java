package data;

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
}
