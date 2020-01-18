package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
		System.out.println(" 1. file content: " + inputJSON);
		
		//Step2 Replace
		inputJSON = inputJSON.replace("'corners':", "'corners':[{'Point':");
		
		System.out.println(" 2. file content: " + inputJSON);
		
		//Step3: Append end part
		inputJSON = inputJSON.replace("]}, {'height'", "]}]}, {'height'");
		System.out.println(" 3. file content: " + inputJSON);
		
		//Step 4: append end part
		inputJSON = inputJSON + "]}]}" ;
		System.out.println(" 4. file content: " + inputJSON);
		
		//Step 5: prepend List part in beginning
		inputJSON = "{'list':[" + inputJSON;
		System.out.println(" 5. file content: " + inputJSON);
		
		return inputJSON;
			
	}
	
	public static List<Building> loadBuildingsFromJSON(String path) throws IOException{
		
		String s = readTextFileContent(path);
		s = updateJSON(s);
		
		List<Building> buildingsList = (List<Building>) JSONUtil.convertJSONtoObject(s);
		System.out.println(buildingsList);
		return buildingsList;
	}
	
	/*
	public static void main(String[] args){
		
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
	}
	
	*/
}
