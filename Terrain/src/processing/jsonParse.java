package processing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;


public class jsonParse {

	public static void main(String[] args) {
		ObjectMapper mapper=new ObjectMapper();
        ArrayList heightList=new ArrayList<Integer>(); //list of heights
        ArrayList cornerList=new ArrayList<float[]>(); //list of corners
        JsonNode buildings = null;
		try {
			buildings = mapper.readTree(new File("res/data.json")).get("buildings");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (JsonNode item:buildings) {
           heightList.add(item.get("height"));
           cornerList.add(item.get("corners"));
        }
        
        System.out.println(heightList.get(4));
        System.out.println(cornerList.get(4).getClass());
	}
}
