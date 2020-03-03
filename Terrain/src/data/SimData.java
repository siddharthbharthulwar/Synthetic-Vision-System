package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class SimData {
	
	private List<Float> latitudes;
	private List<Float> longitudes;
	private List<Float> times;
	private List<Float> velocityTrue;
	private List<Float> pitchDeg;
	private List<Float> rollDeg;
	private List<Float> alphaDeg;
	private List<Float> hpathDeg;
	private List<Float> vpathDeg;
	private List<Float> altitude;
	private List<Float> vX;
	private List<Float> vY;
	private List<Float> vZ;
	private List<Float> distNM;
	private List<Float> tempList = new ArrayList<Float>();
	
	public SimData(String path) {
		
		File file = new File(path);
		Scanner sc = null;
		List<String> lines = new ArrayList<String>();
		
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()){
			    String str = sc.nextLine();
			    lines.add(str);
			   }
			   
			  } catch (IOException  exp) {
			   exp.printStackTrace();
			  }
			  
			  sc.close();
			  for (int i = 1; i < lines.size(); i++) {
				 ParseLine(lines.get(i));
			  }


	}
	
	public void ParseLine(String line){
		
		StringTokenizer tokenizer = new StringTokenizer(line, "|");
		
		while (tokenizer.hasMoreTokens()) {
			
			String token = tokenizer.nextToken();
			token = token.trim();
			if (!token.isEmpty()) {
				this.tempList.add(Float.parseFloat(token));
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		
		SimData sim = new SimData("res/sensors.txt");
		System.out.println(sim.tempList.get(1));
		
	}

}
