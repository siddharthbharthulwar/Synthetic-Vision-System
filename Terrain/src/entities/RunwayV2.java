package entities;

import org.lwjgl.util.vector.Vector2f;

public class RunwayV2 {
	
	private float width;
	private float length;
	private Vector2f anchor1;
	private Vector2f anchor2;
	private float elevation;
	private Vector2f midpoint;
	private float[] vertices;
	private int[] indices;
	
	/*
	 * 
	 * 								1---------2
	 * 
	 * 								3---------4
	 */
	
	private Vector2f p3;
	private Vector2f p4;
	
	private Vector2f anchor3; //endpoint of threshold
	//static methods:
	
	public static Vector2f normalize(Vector2f init) {
			
			float x = init.getX();
			float y = init.getY();
			
			float squaredsum = (x * x) + (y * y);
			float magnitude = (float) Math.sqrt(squaredsum);
			
			return new Vector2f(x / magnitude, y / magnitude);
			
	}
	
	//constructor
	
	public RunwayV2(float width, float length, Vector2f anchor1, Vector2f anchor2, Vector2f anchor3, float elevation) {
		
		this.width = width;
		this.length = length;
		this.anchor1 = anchor1;
		this.anchor2 = anchor2;
		
		this.elevation = elevation;
		
		this.midpoint = new Vector2f((this.anchor1.x + this.anchor2.x) / 2, (this.anchor1.y + this.anchor2.y) / 2);
		
		
	}
	
	public float[] generateBaseVertices() {
			
			
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
			
			return base;
			
	}
	
	public int[] generateBaseIndices() {
			
			//BASE RUNWAY:
			
			int[] ind = {
			2, 0, 1,
			2, 1, 3
			};
			
			return ind;
	}
	
	public float calculateHeading() {
		
		Vector2f dirVec = new Vector2f(this.anchor1.getX() - this.anchor2.getX(), this.anchor1.getY() - this.anchor2.getY());
		Vector2f dot = new Vector2f(1, -1 * (dirVec.getY() / dirVec.getX()));
		dot = normalize(dot);
		Vector2f norm = new Vector2f(0, 1);
		return Vector2f.angle(dot, norm);
	}

}
