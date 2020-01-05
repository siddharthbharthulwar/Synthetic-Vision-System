package entities;

public class Runway {

	public int width;
	public int length;
	public int n1;
	public int n2;
	public int anchor;
	
	public Runway(int w, int l, int one, int two, int anchor) {
		this.width = w;
		this.length = l;
		this.n1 = one;
		this.n2 = two;
		this.anchor = anchor;
	}
	
}
