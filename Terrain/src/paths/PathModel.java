package paths;

import org.lwjgl.util.vector.Vector3f;

import models.RawModel;
import models.TexturedModel;

public class PathModel {
	
	private RawModel model;
	private TexturedModel textureModel;
	
	private float[] vertices;
	private int[] indices = {
			0, 1
	};
	private float[] normals = {
			
		1, 1, 1, 1	
	};
	
	public Vector3f initialPoint;
	public Vector3f finalPoint;
	
	public PathModel(Vector3f initialPoint, Vector3f finalPoint) {
		
		this.initialPoint = initialPoint;
		this.finalPoint = finalPoint;
		
		float[] vertices = {
			
			this.initialPoint.getX(), this.initialPoint.getY(), this.initialPoint.getZ(),
			this.finalPoint.getX(), this.finalPoint.getY(), this.finalPoint.getZ()
				
		};
		
		this.vertices = vertices;
		
		
	}

}
