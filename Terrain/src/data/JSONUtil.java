package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

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
    	
    	List<Point> corners = new ArrayList<Point>();
    	corners.add(new Point(300.5f, 200.0f));
    	corners.add(new Point(350.5f, 250.0f));
    	corners.add(new Point(360.5f, 260.0f));
    	
    	
    	Building building = new Building(200, corners);
    	
    	String buildingJSON = JSONUtil.convertObjectToJSON(building);
    	
    	System.out.println(" ---- Building JSON is = " + buildingJSON);
    	 
    }
}
