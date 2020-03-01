package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SimData {

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
}
