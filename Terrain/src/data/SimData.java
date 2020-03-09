package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class SimData {
	
	private List<Float> totalTime = new ArrayList<Float>();
	private List<Float> vIndMph = new ArrayList<Float>();
	private List<Float> qRad = new ArrayList<Float>();
	private List<Float> pRad = new ArrayList<Float>();
	private List<Float> rRad = new ArrayList<Float>();
	private List<Float> pitch = new ArrayList<Float>();
	private List<Float> roll = new ArrayList<Float>();
	private List<Float> heading = new ArrayList<Float>();
	private List<Float> lat = new ArrayList<Float>();
	private List<Float> lon = new ArrayList<Float>();
	private List<Float> altitude = new ArrayList<Float>();
	private List<Float> vX = new ArrayList<Float>();
	private List<Float> vY = new ArrayList<Float>();
	private List<Float> vZ = new ArrayList<Float>();
	
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
		
		int index = 0;
		while (tokenizer.hasMoreTokens()) {
			
			String token = tokenizer.nextToken();
			token = token.trim();
			if (!token.isEmpty()) {
				float f = Float.parseFloat(token);
				if (index == 1) {
					totalTime.add(f);
				}
				if (index == 11) {
					vIndMph.add(f);
				}
				if (index == 14) {
					qRad.add(f);
				}
				if (index == 15) {
					pRad.add(f);
				}
				if (index == 16) {
					rRad.add(f);
				}
				if (index == 17) {
					pitch.add(f);
				}
				if (index == 18) {
					roll.add(f);
				}
				if (index == 19) {
					heading.add(f);
				}
				if (index == 26) {
					lat.add(f);
				}
				if (index == 27) {
					lon.add(f);
				}
				if (index == 29) {
					altitude.add(f);
				}
				if (index == 37) {
					vX.add(f);
				}
				if (index == 38) {
					vY.add(f);
				}
				if (index == 39) {
					vZ.add(f);
				}
				
			}

			index ++;
		}
		
	}
	
	
	public static void main(String[] args) {
		
		
		SimData sim = new SimData("res/sensors.txt");
		System.out.println(sim.vX);
		
	}

}
