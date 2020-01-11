package processing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class jsonParse {

	public static void main(String[] args) {
		ObjectMapper mapper=new ObjectMapper();
        ArrayList heightList=new ArrayList<Integer>(); //list of heights
        ArrayList cornerList=new ArrayList<float[]>(); //list of corners
        
        ArrayList finalCornerList = new ArrayList<ArrayList<Float>>(heightList.size());
        
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
        /*
        for (int i = 0; i < heightList.size(); i++) {
        	for (int j = 0; i < ((ArrayNode) cornerList.get(i)).size(); j++) {
        		finalCornerList.set(i, cornerList.get(index))
        	}
        }
        */
        System.out.println(((ArrayNode) cornerList.get(0)));
        System.out.println(((ArrayNode) cornerList.get(0)).get(0).get(0).get(0));
	}
}
