package logic;

import org.lwjgl.util.vector.Vector3f;

public class glideslope{
	
	public static float distance(Vector3f pos1, Vector3f pos2) {
		
		return (float) Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2) + Math.pow(pos1.z - pos2.z, 2));
		
	}
	
	public static float angle(Vector3f pos1, Vector3f pos2, float baseHeight) {
		
		float distance = distance(pos1, pos2);
		return (float) Math.toDegrees(Math.atan(baseHeight / distance));
		
	}
	
	public static int PAPIConfiguration(Vector3f pos1, Vector3f pos2, float baseHeight) {
		
		double angle = (double) angle(pos1, pos2, baseHeight);
		
		if (angle < 2.5) {
			return 1;
		}
		else if (angle <= 2.8 && angle >= 2.5) {
			return 2;
		}
		else if (angle <= 3.5 && angle >= 3.2) {
			return 4;
		}
		else if (angle > 3.5) {
			return 5;
		}
		else {
			return 3;
		}
	}
}