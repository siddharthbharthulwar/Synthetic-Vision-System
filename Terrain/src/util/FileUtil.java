package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

import data.Building;

public class FileUtil {
	
	public static String readTextFileContent(String fileNameFull) throws IOException {	
		String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(fileNameFull))); 
	    return data; 
	}
	
	public static String updateJSON(String inputJSON) {
		
		//Step1: Remove "
		inputJSON = inputJSON.replace("\"", "");
		//System.out.println(" 1. file content: " + inputJSON);
		
		//Step2 Replace
		inputJSON = inputJSON.replace("'corners':", "'corners':[{'Point':");
		
		//System.out.println(" 2. file content: " + inputJSON);
		
		//Step3: Append end part
		inputJSON = inputJSON.replace("]}, {'height'", "]}]}, {'height'");
		//System.out.println(" 3. file content: " + inputJSON);
		
		//Step 4: append end part
		inputJSON = inputJSON + "]}]}" ;
		//System.out.println(" 4. file content: " + inputJSON);
		
		//Step 5: prepend List part in beginning
		inputJSON = "{'list':[" + inputJSON;
		//System.out.println(" 5. file content: " + inputJSON);
		
		return inputJSON;
			
	}
	
	public static List<Building> loadBuildingsFromJSON(String path) throws IOException{
		
		String s = readTextFileContent(path);
		s = updateJSON(s);
		
		List<Building> buildingsList = (List<Building>) JSONUtil.convertJSONtoObject(s);
		//System.out.println(buildingsList);
		return buildingsList;
	}
	
	public static void testMethod() {
		
		String test = "143.68655 |     157.51659 |     162.42039 |     161.84357 |     165.35153 |     186.91006 |     186.91006 |   73084.40625 |  -11093.49805 |    -251.51691 |      -0.04216 |      -0.00204 |       0.00047 |      -0.01986 |       0.17382 |      57.23893 |      55.91833 |       4.81124 |      -0.01150 |      57.23508 |      -4.83026 |      -0.02514 |      51.86249 |       4.20436 |    2080.24561 |    2083.15283 |       0.00000 |    1919.09692 |      51.50000 |       4.00000 |   14049.89355 |     490.79962 |  -40375.95703 |      69.87096 |      -7.47722 |     -45.20831 |     548.32037 |       0.09024 | ";
		
		StringTokenizer tokenizer = new StringTokenizer(test, "|");
		
		int index = 1;
		
		while(tokenizer.hasMoreTokens()) {
			
			String token = tokenizer.nextToken();		
			token = token.trim();		
			System.out.println(index + " === token is: " + token);
			index++;
		}
			
	}
	
	
	public static void main(String[] args){
		
		testMethod();
		
		/*
		
		try {
			String fileName ="res/data.json";
			
			String fileContent = FileUtil.readTextFileContent(fileName);
			
			fileContent = updateJSON(fileContent);
			
			List<Building> buildingsList = (List<Building>)JSONUtil.convertJSONtoObject(fileContent);
			
			System.out.println(" 6. Buildings List PARSED: " + buildingsList);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	
}
