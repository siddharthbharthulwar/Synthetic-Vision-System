package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import data.Building;
import data.Point;

/**
 *********************************************************
 * 
 * JSONUtil is used for converting Objects to JSON String 
 * and vice versa using Xstream. We use this to Serialize
 * the objects between the Client and Server.
 * 
 ********************************************************* 
 *
 * @author Shridhar Bharthulwar <br>
 * Copyright 2012-16 Shridhar Bharthulwar
 * 
 *********************************************************
 */
public class JSONUtil {
	
	private static void setAliases(XStream xstream){
		
		xstream.alias("Building", Building.class);
		xstream.alias("Point", Point.class);
				
	}
	
	public static String convertObjectToJSON(Object obj){
		
		try{
			
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			setAliases(xstream);
			
			return xstream.toXML(obj);
			
		}catch(Throwable e){
			e.printStackTrace();
			//CustomLogger.warning(JSONUtil.class, "Error while converting Object to JSON ", e);
		}
		
		return null;
	}
    public static Object convertJSONtoObject(String json){
    	
        try{
			
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			setAliases(xstream);
			
			return xstream.fromXML(json);
			
		}catch(Throwable e){
			
			e.printStackTrace();
			//CustomLogger.warning(JSONUtil.class, "Error while converting JSON to Object ", e);
		}
		
		return null;
	}
    
    public static void main(String[] args){
    	
    	List<Point> corners1 = new ArrayList<Point>();
    	corners1.add(new Point(300.5f, 200.0f));
    	corners1.add(new Point(350.5f, 250.0f));
    	corners1.add(new Point(360.5f, 260.0f));
    	
    	List<Point> corners2 = new ArrayList<Point>();
    	corners2.add(new Point(600.5f, 200.0f));
    	corners2.add(new Point(650.5f, 250.0f));
    	corners2.add(new Point(760.5f, 260.0f));
    	
    	
    	List<Building> buildings = new ArrayList<Building>();
    	Building building1 = new Building(200, corners1);
    	Building building2 = new Building(300, corners2);
    	
    	buildings.add(building1);
    	buildings.add(building2);
    	   	
    	String buildingsJSON = JSONUtil.convertObjectToJSON(buildings);
    	
    	//System.out.println(" ---- Buildings JSON is = " + buildingsJSON);
    	
    	buildingsJSON = buildingsJSON.replace("\"", "'");
    	
    	//System.out.println(" ---- Buildings JSON after replace is = " + buildingsJSON);
    	
    	List<Building> buildingList  =  (List<Building>)JSONUtil.convertJSONtoObject(buildingsJSON) ;
    	
    	//System.out.println(" ---- Buildings List is = " + buildingList);

    	 
    }
}
