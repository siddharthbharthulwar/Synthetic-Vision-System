package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EarCuttingUtil {
	
	private static boolean isLineInsideShape(Polygon polyShape, Point p1, Point p2){
		
		Point midPoint = new Point( (int)(p1.getX() + p2.getX())/2, (int)(p1.getY() + p2.getY())/2 );
		
		if(!polyShape.contains(midPoint)){
			return false;
		}
		
        Point threeFourthPoint = new Point( (int)(3*p1.getX() + p2.getX())/4, (int)(3*p1.getY() + p2.getY())/4 );
		
		if(!polyShape.contains(threeFourthPoint)){
			return false;
		}
		
        Point oneFourthPoint = new Point( (int)(p1.getX() + 3*p2.getX())/4, (int)(p1.getY() + 3*p2.getY())/4 );
		
		if(!polyShape.contains(oneFourthPoint)){
			return false;
		}
				
		return true;
	}
	
	
	private static int getIndexOfPoint(Point point, List<Point> pointsList) {
		return pointsList.indexOf(point);
	}
	
	private static Polygon getPolygonShape(List<Point> pointsList){
	    	
	    	Polygon poly = new Polygon();
	    	
	    	for(Point p:pointsList){
	    		poly.addPoint((int)p.getX(), (int)p.getY());
	    	}
			return poly;
	    }

	private static void drawTriangleFillEarCutting(List<Point> pointsList, List<Point> initialPointsList){
			
			System.out.println("...drawTriangleFillEarCutting()... pointsList = " + pointsList);
			
			Polygon polyShape = getPolygonShape(pointsList);
			
			if(pointsList.size()>3){
				
				System.out.println("...Polygon size is > 3, Will proceed to Cut an Ear!");
				
				for(int i=0; i< pointsList.size(); i++){
					
					if(i < pointsList.size()-2){
						System.out.println(" ...inside for loop, i = " + i);
						Point firstPoint = pointsList.get(i);
						Point secondPoint = pointsList.get(i+1);
						Point thirdPoint = pointsList.get(i+2);
						
						if(isLineInsideShape(polyShape, firstPoint, thirdPoint)){
							
							//This is THE triangle to be rendered
							
							int firstPointIndex = getIndexOfPoint(firstPoint, initialPointsList);
							int secondPointIndex = getIndexOfPoint(secondPoint, initialPointsList);
							int thirdPointIndex = getIndexOfPoint(thirdPoint, initialPointsList);
							
							System.out.println(".....Traingle Points Indices = (" + firstPointIndex + ", " + secondPointIndex + ", " + thirdPointIndex + ")");
							
							
							//System.out.println(".....EAR CUTTING....Removing point = " + secondPoint);
							pointsList.remove(secondPoint);
							
							drawTriangleFillEarCutting(pointsList, initialPointsList);
						}
					}
					
				}
			}else{
				//we are done
				System.out.println(".....ALL DONE!");
			}
					
		}
	private static List<Point> getInitialPointsList(Point[] points){
		List<Point> pointsList = new ArrayList<Point>();
		
		for(Point p:points){
			pointsList.add(p);
		}
		return pointsList;
	}
	
	public static void main(String[] args) {
		
		
		Point[] points = {new Point(100, 100), new Point(100, 300),  new Point(150, 300), new Point(150, 150), new Point(300, 150), new Point(300, 300), new Point(350, 300), new Point(350, 100)};
		List<Point> pointList = getInitialPointsList(points);
		
		drawTriangleFillEarCutting(pointList, pointList);
		
		

	}
}
