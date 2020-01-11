package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;



public class jsonParse {

	public static void main(String[] args) {
		ObjectMapper mapper=new ObjectMapper();
        ArrayList heightList=new ArrayList<Integer>(); //list of heights
        ArrayList cornerList=new ArrayList<float[]>(); //list of corners
        
        ArrayList finalCornerList = new ArrayList<ArrayList<float[]>>();
        
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
        
        System.out.println((cornerList));
        System.out.println(((ArrayNode) cornerList.get(1)).get(0).get(0).get(1));
        
        for (int i = 0; i < heightList.size(); i++) {
        	ArrayList<float[]> temp = new ArrayList<float[]>();
        	for (int j = 0; j < ((ArrayNode) cornerList.get(i)).size(); j++) {
        		JsonNode cx = ((ArrayNode) cornerList.get(i)).get(j).get(0).get(0);
        		JsonNode cy = ((ArrayNode) cornerList.get(i)).get(j).get(0).get(1);
        		float[] fin = {cx.floatValue(), cy.floatValue()};
        		System.out.println(fin[0] + " and " + fin[1]);
        		temp.add(fin);
        	}
        	finalCornerList.add(temp);
        }
        
        System.out.println(finalCornerList);
	}
}
