package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
			  System.out.println(lines.get(0));

	}
	
	public List<Float> ParseLine(String line){
		
		return null;
	}
	
	/*
	public static void main(String[] args) {
		
	File file = new File("res/sensors.txt");
	Scanner sc = null;
	List<String> lines = new ArrayList<String>();
	
	try {
		sc = new Scanner(file);
		   // Check if there is another line of input
		while(sc.hasNextLine()){
		    String str = sc.nextLine();
		    lines.add(str);
		   }
		   
		  } catch (IOException  exp) {
		   // TODO Auto-generated catch block
		   exp.printStackTrace();
		  }
		  
		  sc.close();
		  System.out.println(lines.get(0));

	}
	*/
}
