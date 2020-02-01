package entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class CenterlineMarking {
	
	public Vector3f anchorPoint;
	public float heading;
	public float width;
	public float height;
	
	public static Vector2f normalize(Vector2f init) {
		
		float x = init.getX();
		float y = init.getY();
		
		float squaredsum = (x * x) + (y * y);
		float magnitude = (float) Math.sqrt(squaredsum);
		
		return new Vector2f(x / magnitude, y / magnitude);
		
	}
	
	public CenterlineMarking(Vector3f anchorPoint, float heading, float width, float height) {
		
		this.anchorPoint = anchorPoint;
		this.heading = heading;
		this.width = width;
		this.height = height;
		
	}
	
	public Vector2f parallelNormVector() {
		
		return normalize(new Vector2f((float)Math.cos(this.heading), (float)Math.sin(this.heading)));
	}
	
	public Vector2f perpendicularNormVector() {
		
		Vector2f dot = new Vector2f(1, -1 * (this.parallelNormVector().getY() / this.parallelNormVector().getX()));
		return dot;
	}
	/*
	public float[] generateVertices() {
		
		Vector3f p12d = this.anchorPoint + new Vector3f((this.width / 2) * this.perpendicularNormVector().getX(), (this.width / 2) * this.perpendicularNormVector().getY(), this.height);//((this.width / 2) * this.perpendicularNormVector())
		
	}
	
	public int[] generateIndices() {
		
		
	}
	*/

}
