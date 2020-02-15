package entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class RunwayV2{
	
	private Vector2f anchor1;
	private Vector2f anchor2;
	
	private Vector2f anchor3;
	private Vector2f anchor4;
	
	private float length;
	private float elevation;
	
	private List<Vector3f> centerlinePoints;
	public float[] baseVertices;
	public float[] centerlineVertices;
	public int[] baseIndices;
	public int[] centerlineIndices;
	
	private float pianoLength;
	private float pianoSpacing;
	
	public float[] pianoVertices;
	public int[] pianoIndices;
	
	private float touchdownPointDisplacement;
	private float touchdownLength;
	
	public RunwayV2(Vector2f anchor1, Vector2f anchor2, float length, float elevation, 
			int numberOfMarkings, float marklength, float markwidth, float pianoLength, float pianoSpacing
			, float touchdownPointDisplacement, float touchdownLength) {
	
		this.anchor1 = anchor1;
		this.anchor2 = anchor2;
		this.length = length;
		this.elevation = elevation;
		this.touchdownPointDisplacement = touchdownPointDisplacement;
		this.touchdownLength = touchdownLength;
		this.generateFullCenterlineVertices(numberOfMarkings, markwidth, marklength);
		this.baseVertices = vecListToArray(this.generateVertices());
		this.centerlineVertices = vecListToArray(this.centerlinePoints);
		this.baseIndices = intListToArray(this.generateBaseIndices());
		this.centerlineIndices = intListToArray(this.generateCenterlineIndices());
		
		this.pianoLength = pianoLength;
		this.pianoSpacing = pianoSpacing;
		
		this.pianoVertices = vecListToArray(this.generateNormPianoKeyVertices());
		System.out.println(this.generateNormPianoKeyVertices());
		
		this.generatePianoKeysIndices();

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
		Vector3f zero = new Vector3f(position.getX() - wid, this.elevation + 5, position.getZ() - len);
		Vector3f one = new Vector3f(position.getX() + wid, this.elevation + 5, position.getZ() - len);
		
		Vector3f two = new Vector3f(position.getX() - wid, this.elevation + 5, position.getZ() + len);
		Vector3f three = new Vector3f(position.getX() + wid, this.elevation + 5, position.getZ() + len);
		

		
		List<Vector3f> vec = new ArrayList<Vector3f>();
		vec.add(zero);
		vec.add(one);
		vec.add(two);
		vec.add(three);
		return vec;
	}
	
	public static List<Vector3f> listDimensionalizeShift(List<Vector2f> verts, float elevation){
		
		List<Vector3f> ret = new ArrayList<Vector3f>();
		
		for (int i = 0; i < verts.size(); i++) {
			
			Vector2f temp = verts.get(i);
			ret.add(new Vector3f(temp.getX(), elevation + 5, temp.getY()));
			
		}
		return ret;
		
	}

	public List<Vector3f> generateNormPianoKeyVertices(){
		
		List<Vector2f> pianoVertices = new ArrayList<Vector2f>();
		
		float dist = this.anchor2.getX() - this.anchor1.getX();
		
		float l = this.pianoSpacing;
		float w = this.pianoSpacing;
		float W = (dist - (10 * w)) / 8;
		float v = this.pianoLength;
		float x = this.anchor3.getX();
		float y = this.anchor3.getY();
		
		pianoVertices.add(new Vector2f(x + w, y - w));
		pianoVertices.add(new Vector2f(x + w + W, y - w));
		pianoVertices.add(new Vector2f(x + w, y - w - v));
		pianoVertices.add(new Vector2f(x + w + W, y - w - v));
		
		pianoVertices.add(new Vector2f(x + w + W + l, y - w));
		pianoVertices.add(new Vector2f(x + w + 2 * W + l, y - w));
		pianoVertices.add(new Vector2f(x + w + W + l, y - w - v));
		pianoVertices.add(new Vector2f(x + w + 2 * W + l, y - w - v));
		
		pianoVertices.add(new Vector2f(x + w + 2 * W + 2 * l, y - w));
		pianoVertices.add(new Vector2f(x + w + 3 * W + 2 * l, y - w));
		pianoVertices.add(new Vector2f(x + w + 2 * W + 2 * l, y - w - v));
		pianoVertices.add(new Vector2f(x + w + 3 * W + 2 * l, y - w - v));

		pianoVertices.add(new Vector2f(x + w + 3 * W + 3 * l, y - w));
		pianoVertices.add(new Vector2f(x + w + 4 * W + 3 * l, y - w));
		pianoVertices.add(new Vector2f(x + w + 3 * W + 3 * l, y - w - v));
		pianoVertices.add(new Vector2f(x + w + 4 * W + 3 * l, y - w - v));

		
		
		
		
		pianoVertices.add(new Vector2f(x + 3 * w + 4 * W + 3 * l, y - w));
		pianoVertices.add(new Vector2f(x + 3 * w + 5 * W + 3 * l, y - w));
		pianoVertices.add(new Vector2f(x + 3 * w + 4 * W + 3 * l, y - w - v));
		pianoVertices.add(new Vector2f(x + 3 * w + 5 * W + 3 * l, y - w - v));
		

		pianoVertices.add(new Vector2f(x + 3 * w + 5 * W + 4 * l, y - w));
		pianoVertices.add(new Vector2f(x + 3 * w + 6 * W + 4 * l, y - w));
		pianoVertices.add(new Vector2f(x + 3 * w + 5 * W + 4 * l, y - w - v));
		pianoVertices.add(new Vector2f(x + 3 * w + 6 * W + 4 * l, y - w - v));
		

		pianoVertices.add(new Vector2f(x + 3 * w + 6 * W + 5 * l, y - w));
		pianoVertices.add(new Vector2f(x + 3 * w + 7 * W + 5 * l, y - w));
		pianoVertices.add(new Vector2f(x + 3 * w + 6 * W + 5 * l, y - w - v));
		pianoVertices.add(new Vector2f(x + 3 * w + 7 * W + 5 * l, y - w - v));
		

		pianoVertices.add(new Vector2f(x + 3 * w + 7 * W + 6 * l, y - w));
		pianoVertices.add(new Vector2f(x + 3 * w + 8 * W + 6 * l, y - w));
		pianoVertices.add(new Vector2f(x + 3 * w + 7 * W + 6 * l, y - w - v));
		pianoVertices.add(new Vector2f(x + 3 * w + 8 * W + 6 * l, y - w - v));
		
		
		
		pianoVertices.add(new Vector2f(x + w + W + l, y - w - this.touchdownPointDisplacement));
		pianoVertices.add(new Vector2f(x + w + 3 * W + 2 * l, y - w - this.touchdownPointDisplacement));
		pianoVertices.add(new Vector2f(x + w + W + l, y - w - this.touchdownPointDisplacement - this.touchdownLength));
		pianoVertices.add(new Vector2f(x + w + 3 * W + 2 * l, y - w - this.touchdownPointDisplacement - this.touchdownLength));
		
		pianoVertices.add(new Vector2f(x + 3 * w + 5 * W + 4 * l, y - w - this.touchdownPointDisplacement));
		pianoVertices.add(new Vector2f(x + 3 * w + 7 * W + 5 * l, y - w - this.touchdownPointDisplacement));
		pianoVertices.add(new Vector2f(x + 3 * w + 5 * W + 4 * l, y - w - this.touchdownPointDisplacement - this.touchdownLength));
		pianoVertices.add(new Vector2f(x + 3 * w + 7 * W + 5 * l, y - w - this.touchdownPointDisplacement - this.touchdownLength));
		
		
		return listDimensionalizeShift(pianoVertices, this.elevation);
	}
	

	
	public void generatePianoKeysIndices() {
		
		int[] ret = {
				
			2, 0, 3,
			0, 1, 3,
			6, 4, 7,
			4, 5, 7,
			10, 8, 11,
			8, 9, 11,
			14, 12, 15, 
			12, 13, 15,
			18, 16, 19,
			16, 17, 19,
			22, 20, 23, 
			20, 21, 23, 
			26, 24, 27,
			24, 25, 27,
			30, 28, 31,
			28, 29, 31,
			
			34, 32, 35,
			32, 33, 35,
			
			38, 36, 39,
			36, 37, 39
				
		};
		
		this.pianoIndices = ret;
		
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
		
		this.anchor3 = three;
		this.anchor4 = four;
		
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