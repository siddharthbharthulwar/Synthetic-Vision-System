package flightsystems;

import org.lwjgl.util.vector.Vector3f;

public class indicators extends entities.Camera{
	
	private Vector3f position = new Vector3f(0,0,0);
	private int runwayLength;
	private int runwayWidth;
	private float angle;
	
	public boolean[] lights;
	private float[] deviations;
	private float glideslope;
	
	
	
	public indicators(Vector3f pos, int length, int width, float ang) {
		this.position = pos;
		this.runwayLength = length;
		this.runwayWidth = width;
		this.angle = ang;
	}

}
