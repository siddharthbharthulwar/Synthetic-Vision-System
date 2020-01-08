package logic;

public class glideslope {

	public float distanceFromRunway;
	public float height;
	
	public glideslope(float d, float h) {
		this.distanceFromRunway = d;
		this.height = h;
	}
	
	public float calcSlope() {
		return this.height / this.distanceFromRunway;
	}
	
	public int calcLightConfiguration() {
		return -1;
		//TODO: actually do this method properly
	}
}
