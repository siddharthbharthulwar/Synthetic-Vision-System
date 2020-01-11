package data;

public class Point {
	
	private float x;
	private float y;
	
	public Point(float x, float y) {
		setX(x);
		setY(y);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Point3D convertTo3D(float height) {
		return new Point3D(this.x, this.y, height);
	}
	
	public String toString() {
		String s = "(";
		return s + this.x + ", " + this.y + ")";
	}
	
}
