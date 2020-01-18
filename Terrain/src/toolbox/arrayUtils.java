package toolbox;

import java.util.ArrayList;
import java.util.List;

import data.Point;

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
	
	public static List<Point> reverseArrayList(List<Point> alist) 
    { 
        // Arraylist for storing reversed elements 
        ArrayList<Point> revArrayList = new ArrayList<Point>(); 
        for (int i = alist.size() - 1; i >= 0; i--) { 
  
            // Append the elements in reverse order 
            revArrayList.add(alist.get(i)); 
        } 
  
        // Return the reversed arraylist 
        return revArrayList; 
    } 
	
	
}
