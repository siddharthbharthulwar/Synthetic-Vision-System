package data;

import org.lwjgl.util.vector.Vector3f;

public class Vegetation {
	
	public Vector3f position;
	public float scaleFactor;
	
	public Vegetation(Vector3f position, float scaleFactor) {
		
		this.position = position;
		this.scaleFactor = scaleFactor;
		
	}

}
