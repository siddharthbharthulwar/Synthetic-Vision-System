package entities;


import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import data.normPoint;

public class Runway<Vertex2f>{
	
	private float width;
	private float length;
	private Vector2f anchor1;
	private Vector2f anchor2;
	private float elevation;
	private float thresholdSize;
	private Vector2f midpoint;
	
	public static Vector2f normalize(Vector2f init) {
		
		float x = init.getX();
		float y = init.getY();
		
		float squaredsum = (x * x) + (y * y);
		float magnitude = (float) Math.sqrt(squaredsum);
		
		return new Vector2f(x / magnitude, y / magnitude);
		
	}
	
	public Runway(float width, float length, Vector2f anchor1, Vector2f anchor2, float elevation, float threshsize) {
		
		this.width = width;
		this.length = length;
		this.anchor1 = anchor1;
		this.anchor2 = anchor2;
		this.elevation = elevation;
		this.thresholdSize = threshsize;
		this.midpoint = new Vector2f((this.anchor1.x + this.anchor2.x) / 2, (this.anchor1.y + this.anchor2.y) / 2);
		
	}
	
	
	public float[] generateVertices() {
		
		
		//VECTOR CALCULATIONS:
		
		Vector2f dirVec = new Vector2f(this.anchor1.getX() - this.anchor2.getX(), this.anchor1.getY() - this.anchor2.getY());
		Vector2f dot = new Vector2f(1, -1 * (dirVec.getY() / dirVec.getX()));
		dot = normalize(dot);
		
		
		Vector2f pos1 = new Vector2f(this.anchor1.getX() + (dot.getX() * this.length), this.anchor1.getY() + (dot.getY() * this.length));
		Vector2f pos2 = new Vector2f(this.anchor2.getX() + (dot.getX() * this.length), this.anchor2.getY() + (dot.getY() * this.length));
		
		System.out.println(pos1);
		System.out.println(pos2);
		
		
		//BASE RUNWAY LEN 12:
		float[] base = {
				
			this.anchor1.getX(), this.elevation, this.anchor1.getY(),
			this.anchor2.getX(), this.elevation, this.anchor2.getY(),
			pos1.getX(), this.elevation, pos1.getY(),
			pos2.getX(), this.elevation, pos2.getY()
				
		};
		/*
		float[] threshmarkings = {
				
				
		}
		*/
		return base;
		
	}
	
	
	public int[] generateIndices() {
		
		//BASE RUNWAY:
		
		int[] ind = {
		2, 0, 1,
		2, 1, 3
		};
		
		return ind;
	}
	
/*
	public static void main(String[] args) {
		
		Runway r = new Runway(45, 1000, new Vector2f(750, 1000), new Vector2f(1000, 750), 15);
		float[] f = r.generateVertices();
	}
	
	*/
	
}