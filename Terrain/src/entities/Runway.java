package entities;


import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Runway<Vertex2f>{
	
	private Vector2f coord1;
	private Vector2f coord2;
	private Vector2f coord3;
	private Vector2f coord4;
	private float elevation;
	
	/*
	 *
	 *   1--------4
	 *   
	 *   
	 *   
	 *   
	 *   
	 *   2--------3
	 
	 */
	
	public Runway(Vector2f one, Vector2f two, Vector2f three, Vector2f four, float elev) {
		
		this.coord1 = one;
		this.coord2 = two;
		this.coord3 = three;
		this.coord4 = four;
		this.elevation = elev;
		
	}

	public float[] genVertices() {
		
		float[] fin = new float[12];
		fin[0] = coord1.x;
		fin[1] = coord1.y;
		fin[2] = this.elevation;
		
		fin[3] = coord2.x;
		fin[4] = coord2.y;
		fin[5] = this.elevation;
		
		fin[6] = coord3.x;
		fin[7] = coord3.y;
		fin[8] = this.elevation;
		
		fin[9] = coord4.x;
		fin[10] = coord4.y;
		fin[11] = this.elevation;
		
		return fin;
		
	}
	
	public int[] genIndices() {
		
		int[] fin = new int[6];
		fin[0] = 1;
		fin[1] = 2;
		fin[2] = 4;
		
		fin[3] = 2;
		fin[4] = 3;
		fin[5] = 4;
		
		return fin;
	}
	
	
}