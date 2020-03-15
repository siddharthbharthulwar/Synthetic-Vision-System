package kalman;

import java.util.List;

import org.apache.commons.math3.filter.KalmanFilter;
import org.apache.commons.math3.filter.MeasurementModel;
import org.apache.commons.math3.filter.ProcessModel;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.lwjgl.util.vector.Vector3f;

public class MainKalman {

	
	private List<Float> vX;
	private List<Float> vY;
	private List<Float> vZ;
	public Vector3f initialPosition;
	
	public MainKalman(List<Float> vX, List<Float> vY, List<Float> vZ, Vector3f initialPosition) {
		
		this.vX = vX;
		this.vY = vY;
		this.vZ = vZ;
		this.initialPosition = initialPosition;
		
	}
	
	
	
}
