package util;


import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class PolygonTriangulationUtil {
	
	private static List<Point> initialPointsList;
	private static boolean firstEntry = true;
	
	private static List<Integer> indicesList = new ArrayList<Integer>();
	
	/**
	 * This method returns a List that contains indices of triangles (3 in sequence) 
	 * for a given Polygon using Ear Cutting algorithm
	 * 
	 * @param pointsList
	 * @return
	 */
	public static List<Integer> getPolygonTriangulationIndices(List<Point> pointsList){
		
		System.out.println("...getPolygonTriangulationIndices()... pointsList = " + pointsList);
        System.out.println("...getPolygonTriangulationIndices()... indicesList = " + indicesList);
        System.out.println("...getPolygonTriangulationIndices()... firstEntry = " + firstEntry);
        	
		if(firstEntry){
			initialPointsList = new ArrayList<Point>(pointsList);
			indicesList.clear();
			firstEntry = false;
		}
							
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
						
						indicesList.add(initialPointsList.indexOf(firstPoint));
						indicesList.add(initialPointsList.indexOf(secondPoint));
						indicesList.add(initialPointsList.indexOf(thirdPoint));
						
						//This is THE triangle to be rendered
						//renderTriangle(firstPoint, secondPoint, thirdPoint);
												
						System.out.println(".....EAR CUTTING....Removing point = " + secondPoint);
						pointsList.remove(secondPoint);
						
						getPolygonTriangulationIndices(pointsList);
					}
				}			
			}
		}else{
			//we are done LAST TRIANGLE
			System.out.println(".....ALL DONE!");
			Point firstPoint = pointsList.get(0);
			Point secondPoint = pointsList.get(1);
			Point thirdPoint = pointsList.get(2);
			
			indicesList.add(initialPointsList.indexOf(firstPoint));
			indicesList.add(initialPointsList.indexOf(secondPoint));
			indicesList.add(initialPointsList.indexOf(thirdPoint));
				
			return indicesList;			
		}
		
		return indicesList;		
	}
	
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
	
    private static Polygon getPolygonShape(List<Point> pointsList){
    	
    	Polygon poly = new Polygon();
    	
    	for(Point p:pointsList){
    		poly.addPoint((int)p.getX(), (int)p.getY());
    	}
		return poly;
    }
}
