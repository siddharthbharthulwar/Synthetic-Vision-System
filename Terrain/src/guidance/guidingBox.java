package guidance;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class guidingBox {

	private float size;
	private Vector3f position;
	private float width;
	private float thickness;
	
	public final float[] normals = {
			
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1	
	};
	
	public guidingBox(float s, Vector3f pos, float w, float th) {
		this.size = s;
		this.position = pos;
		this.width = w;
		this.thickness = th;
	}
	
	public static String arrayToString(float[] var) {
		
		String s = "";
		int count = 0;
		while (count < var.length) {
			s += var[count];
			s += ", ";
			count ++;
		}
		
		return s;
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public static String arrayToString(int[] var) {
		
		String s = "";
		int count = 0;
		while (count < var.length) {
			s += var[count];
			s += ", ";
			count ++;
		}
		
		return s;
	}
	
	
	public float[] generateVertices() {
		
		float x = this.position.x;
		float y = this.position.y;
		float z = this.position.z;
		float s = this.size;
		
		float[] arr = {
				
				
				//bottom left 
				x - s, y - s, z, 
				x - s + this.width, y - s, z,
				x - s + this.width, y - s + this.thickness, z,
				x - s + this.thickness, y - s + this.thickness, z,
				x - s + this.thickness, y - s + this.width, z,
				x - s, y - s + this.width, z,
				
				
				//bottom right
				
				x + s, y - s, z,
				x + s - this.width, y - s, z,
				x + s - this.width, y - s + this.thickness, z,
				x + s - this.thickness, y - s + this.thickness, z,
				x + s - this.thickness, y - s + this.width, z,
				x + s, y - s + this.width, z,
				
				
				//top right
				
				x + s, y + s, z,
				x + s, y + s - this.width, z,
				x + s - this.thickness, y + s - this.width, z,
				x + s - this.thickness, y + s - this.thickness, z,
				x + s - this.width, y + s - this.thickness, z,
				x + s - this.width, y + s, z,
				
				//top left
				
				x - s, y + s, z,
				x - s, y + s - this.width, z,
				x - s + this.thickness, y + s - this.width, z,
				x - s + this.thickness, y + s - this.thickness, z,
				x - s + this.width, y + s - this.thickness, z,
				x - s + this.width, y + s, z
				
				
		};
		System.out.println(arrayToString(arr));

		return arr;
	}
	
	public int[] generateIndices() {
		
		int[] arr = {
				
				//bottom left
				
				0, 1, 3,
				1, 2, 3,
				3, 4, 5,
				5, 0, 3,
				
				//bottom right
				
				7, 6, 9,
				9, 8, 7,
				11, 10, 9,
				11, 9, 6,
				
				//top right
				
				15, 14, 13, 
				17, 16, 15, 
				12, 17, 15,
				12, 15, 13,
				
				//top left
				18, 19, 21,
				21, 19, 20,
				23, 18, 21,
				23, 21, 22
		};
		System.out.println(arrayToString(arr));
		return arr;
		
	}

}
