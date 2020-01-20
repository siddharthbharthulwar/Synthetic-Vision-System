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
	
	private boolean isLineInsideShape(Polygon polyShape, Point p1, Point p2){
		
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
	
	private List<Point> getInitialPointsList(){
		List<Point> pointsList = new ArrayList<Point>();
		
		for(Point p:points){
			pointsList.add(p);
		}
		return pointsList;
	}
	
	private int getIndexOfPoint(Point point, List<Point> pointsList) {
		return pointsList.indexOf(point);
	}
	
	private Polygon getPolygonShape(List<Point> pointsList){
	    	
	    	Polygon poly = new Polygon();
	    	
	    	for(Point p:getInitialPointsList()){
	    		poly.addPoint((int)p.getX(), (int)p.getY());
	    	}
			return poly;
	    }

	private void drawTriangleFillEarCutting(Graphics2D g2d, List<Point> pointsList){
			
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
							renderTriangle(g2d, firstPoint, secondPoint, thirdPoint);
							
							int firstPointIndex = getIndexOfPoint(firstPoint, getInitialPointsList());
							int secondPointIndex = getIndexOfPoint(secondPoint, getInitialPointsList());
							int thirdPointIndex = getIndexOfPoint(thirdPoint, getInitialPointsList());
							
							System.out.println(".....Traingle Points Indices = (" + firstPointIndex + ", " + secondPointIndex + ", " + thirdPointIndex + ")");
							
							
							System.out.println(".....EAR CUTTING....Removing point = " + secondPoint);
							pointsList.remove(secondPoint);
							
							drawTriangleFillEarCutting(g2d, pointsList);
						}
					}
					
				}
			}else{
				//we are done
				System.out.println(".....ALL DONE!");
				renderTriangle(g2d, pointsList.get(0), pointsList.get(1), pointsList.get(2));
			}
					
		}
}
