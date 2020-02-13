package entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class RunwayV2{
	
	private Vector2f anchor1;
	private Vector2f anchor2;
	
	private float length;
	private float elevation;
	
	private List<Vector3f> centerlinePoints;
	public float[] baseVertices;
	public float[] centerlineVertices;
	public int[] baseIndices;
	public int[] centerlineIndices;
	
	public RunwayV2(Vector2f anchor1, Vector2f anchor2, float length, float elevation, int numberOfMarkings, float marklength, float markwidth) {
	
		this.anchor1 = anchor1;
		this.anchor2 = anchor2;
		this.length = length;
		this.elevation = elevation;
		this.generateFullCenterlineVertices(numberOfMarkings, markwidth, marklength);
		this.baseVertices = vecListToArray(this.generateVertices());
		this.centerlineVertices = vecListToArray(this.centerlinePoints);
		this.baseIndices = intListToArray(this.generateBaseIndices());
		this.centerlineIndices = intListToArray(this.generateCenterlineIndices());
		
		
		
		
	}
	
	public static int[] intListToArray(List<Integer> in) {
		
		int[] ret = new int[in.size()];
		
		for (int i = 0; i < in.size(); i++) {
			ret[i] = in.get(i);
		}
		return ret;
	}
	
	public static float[] vecListToArray(List<Vector3f> vecs) {
		
		float[] ret = new float[vecs.size() * 3];
		List<Float> temp = new ArrayList<Float>();
		
		for (Vector3f vector: vecs) {
			temp.add(vector.getX());
			temp.add(vector.getY());
			temp.add(vector.getZ());
		}
		
		for (int i = 0; i < temp.size(); i++) {
			
			ret[i] = temp.get(i);
			
		}
		return ret;
		
	}
	
	public static Vector2f normalize(Vector2f init) {
		
		float x = init.getX();
		float y = init.getY();
		
		float squaredsum = (x * x) + (y * y);
		float magnitude = (float) Math.sqrt(squaredsum);
		
		return new Vector2f(x / magnitude, y / magnitude);
		
	}
	
	public Vector3f centerlinePointDownDistance(float distance) {
		
		Vector2f midpoint = new Vector2f((this.anchor1.x + this.anchor2.x) / 2, (this.anchor1.y + this.anchor2.y)/2);
		Vector3f ret = new Vector3f(midpoint.getX(), this.elevation + 15, midpoint.getY() + distance);
		return ret;
		
		
	}
	
	public List<Vector3f> centerlinePositionGeneration(int markings){
		
		float distance = this.length / markings;
		List<Vector3f> centerlinePositions = new ArrayList<Vector3f>();
		
		for (int i = 1; i < markings; i++) {
			
			centerlinePositions.add(this.centerlinePointDownDistance(i * distance));
			
			
		}
		return centerlinePositions;
	}
	
	public List<Vector3f> verticesAtCenterPoint(Vector3f position, float width, float length){
		
		float len = (length / 2);
		float wid = (width / 2);
		Vector3f zero = new Vector3f(position.getX() - wid, this.elevation + 30, position.getZ() - len);
		Vector3f one = new Vector3f(position.getX() + wid, this.elevation + 30, position.getZ() - len);
		
		Vector3f two = new Vector3f(position.getX() - wid, this.elevation + 30, position.getZ() + len);
		Vector3f three = new Vector3f(position.getX() + wid, this.elevation + 30, position.getZ() + len);
		

		
		List<Vector3f> vec = new ArrayList<Vector3f>();
		vec.add(zero);
		vec.add(one);
		vec.add(two);
		vec.add(three);
		return vec;
	}
	
	public List<Vector3f> generateFullCenterlineVertices(int num, float width, float length){
		
		List<Vector3f> ret = new ArrayList<Vector3f>();
		
		List<Vector3f> positions = this.centerlinePositionGeneration(num);
		
		for (Vector3f vec: positions) {
			
			ret.addAll(verticesAtCenterPoint(vec, width, length));
			
		}
		this.centerlinePoints = ret;
		return ret;
	}
	
	public List<Vector3f> generateVertices(){
		
		
		
		List<Vector3f> baseVertices = new ArrayList<Vector3f>();
		baseVertices.add(new Vector3f(this.anchor1.getX(), this.elevation, this.anchor1.getY()));
		baseVertices.add(new Vector3f(this.anchor2.getX(), this.elevation, this.anchor2.getY()));
		
		Vector2f three = new Vector2f(this.anchor1.getX(), this.anchor1.getY() + this.length);
		Vector2f four = new Vector2f(this.anchor2.getX(), this.anchor2.getY() + this.length);
		
		baseVertices.add(new Vector3f(three.getX(), this.elevation, three.getY()));
		baseVertices.add(new Vector3f(four.getX(), this.elevation, four.getY()));
		
		
		return baseVertices;
		
	
	
	}
	
	
	public List<Integer> generateBaseIndices(){
		
		List<Integer> ind = new ArrayList<Integer>();
		
		//BASE INDICES
		ind.add(2);
		ind.add(0);
		ind.add(1);
		
		ind.add(2);
		ind.add(1);
		ind.add(3);
		//END BASE INDICES
		

		return ind;
		
	}
	
	public List<Integer> generateCenterlineIndices(){
		
		List<Integer> ind = new ArrayList<Integer>();
		for (int i = 4; i <= this.centerlinePoints.size(); i+=4) {
			
			ind.add(i + 2);
			ind.add(i);
			ind.add(i + 3);
			
			ind.add(i);
			ind.add(i + 1);
			ind.add(i + 3);
			
		}
		return ind;
	}


}