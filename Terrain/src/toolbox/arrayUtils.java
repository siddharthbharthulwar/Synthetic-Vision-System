package toolbox;

import java.util.ArrayList;
import java.util.List;

import data.normPoint;

public class arrayUtils {
	public static String arrayToString(float[] var) {
		
		String s = "";
		int count = 0;
		while (count < var.length) {
			s += var[count];
			s += ", ";
			count ++;
		}
		
		return s;
	}
	
	public static String arrayToString(int[] var) {
		
		String s = "";
		int count = 0;
		while (count < var.length) {
			s += var[count];
			s += ", ";
			count ++;
		}
		
		return s;
	}
	
	public static List<normPoint> reverseArrayList(List<normPoint> alist) 
    { 
        // Arraylist for storing reversed elements 
        ArrayList<normPoint> revArrayList = new ArrayList<normPoint>(); 
        for (int i = alist.size() - 1; i >= 0; i--) { 
  
            // Append the elements in reverse order 
            revArrayList.add(alist.get(i)); 
        } 
  
        // Return the reversed arraylist 
        return revArrayList; 
    } 
	
	
}
