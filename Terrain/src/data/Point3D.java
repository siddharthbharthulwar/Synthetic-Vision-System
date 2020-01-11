package data;

public class Point3D {

	private float x;
	private float y;
	private float z;
	
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public Point3D(float x, float y, float z) {
		setX(x);
		setY(y);
		setZ(z);
		
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
	
	public String toString() {
		String s = "(";
		return s + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
	
}
