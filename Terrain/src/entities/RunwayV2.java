package entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class RunwayV2 {
	
	private Vector2f anchor1;
	private Vector2f anchor2;
	
	private Vector2f anchor3;
	private Vector2f anchor4;
	
	
	private float length;
	private boolean direction;
	
	public RunwayV2(Vector2f anchor1, Vector2f anchor2, float length, boolean direction) {
		
		this.anchor1 = anchor1;
		this.anchor2 = anchor2;
		
		this.anchor3 = new Vector2f(this.anchor1.getX(), this.anchor1.getY() - this.length);
		this.anchor4 = new Vector2f(this.anchor2.getX(), this.anchor2.getY() - this.length);
		
		this.length = length;
		this.direction = direction;
	}
	
	/*
	public List<Vector3f> generateBaseVertices(){
		
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		vertices.add(new Vector3f())
	}
	*/
	

}
