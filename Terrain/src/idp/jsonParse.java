package idp;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class jsonParse {
	

	public static void main(String[] args) throws Exception {

		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader("res/data.json"));

		JSONObject jsonObject = (JSONObject) obj;

		for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
		    String key = (String) iterator.next();
		    System.out.println(jsonObject.get(key));
		    JSONArray k = (JSONArray) jsonObject.get(key);
		    JSONObject p = (JSONObject) k.get(1);
		    JSONArray b = (JSONArray) p.get("corners");
		    System.out.println(b);
		    JSONArray z = (JSONArray) p.get(1);
		    System.out.println(z);
		}
	}
}
