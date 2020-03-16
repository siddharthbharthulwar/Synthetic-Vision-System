package entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import guidance.GlideMap;

public class Movement {

	public Vector3f initialPosition;
	public Vector3f transitionPosition;
	public Vector3f finalPosition;
	public RunwayV2 runway;
	
	public float average(float one, float two) {
		
		return (one + two) / 2;
	}
	
	public Movement(RunwayV2 runway, GlideMap gsMap, int indexDown) {
		
		this.runway = runway;
		Vector2f anchor3 = runway.anchor3;
		Vector2f anchor4 = runway.anchor4;
		this.transitionPosition = new Vector3f(average(anchor3.getX(), anchor4.getX()), runway.elevation, average(anchor3.getY(), anchor4.getY()));
		this.initialPosition = gsMap.centroids.get(indexDown);
		float averageWidth = (runway.anchor1.getX() + runway.anchor4.getX()) / 2;
		float averageHeight = (runway.anchor1.getY() + runway.anchor4.getY()) / 2;
		this.finalPosition = new Vector3f(averageWidth, runway.elevation, averageHeight);
		
	}
	
	public int calculateCameraState(Camera camera, Vector3f displacement) {
		
		float camPos = camera.getPosition().getZ();
		float initialZ = this.initialPosition.z + displacement.getZ();
		float transitionZ = this.transitionPosition.z + displacement.getZ();
		float finalZ = this.finalPosition.z + displacement.getZ();
		
		
		System.out.println(camPos);
		System.out.println(finalZ);
		if (camPos >= transitionZ) {
			return 0;
		}
		else if ((camPos <= transitionZ) && (camPos >= finalZ)) {
			
			return 1;
			
		}

		else {
			return 2;
		}

		
	}
	
	
}
