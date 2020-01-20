

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;


public class GraphicsAlgoPanel extends JPanel {
	
	//Regular shaped 
	//private Point[] points = {new Point(100, 300),  new Point(150, 300), new Point(300, 150), new Point(300, 100), new Point(100, 100)};
	
	//L shaped 
	//private Point[] points = {new Point(100, 300),  new Point(150, 300), new Point(150, 150), new Point(300, 150), new Point(300, 100), new Point(100, 100)};
	
	//U shaped 1
	//private Point[] points = {new Point(100, 300),  new Point(150, 300), new Point(150, 150), new Point(300, 150), new Point(300, 300), new Point(350, 300), new Point(350, 100), new Point(100, 100)};
	
	//U shaped 2
	private Point[] points = {new Point(100, 100), new Point(100, 300),  new Point(150, 300), new Point(150, 150), new Point(300, 150), new Point(300, 300), new Point(350, 300), new Point(350, 100)};
		
	
	private static final int POINT_OVAL_SIZE = 6;
	
	private Polygon polygonShape = getPolygonShape(getInitialPointsList());
	
	private List<Point> getInitialPointsList(){
		List<Point> pointsList = new ArrayList<Point>();
		
		for(Point p:points){
			pointsList.add(p);
		}
		return pointsList;
	}
	
    public void paintComponent(Graphics g){
		
		Graphics2D g2d = (Graphics2D)g;
		
		long start = System.currentTimeMillis();
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		List<Point> pointsList = getInitialPointsList();
		System.out.println("....points list = " + pointsList);
		
		//Collections.reverse(pointsList);
		
		drawShapeOutline(g2d, pointsList);		
		drawCentroid(g2d);
		
		//Old Algo
		//drawTriangleFillFixedAnchor(g2d);
			
		System.out.println("....points list after reverse = " + pointsList);
		
		//New Algo
		drawTriangleFillEarCutting(g2d, pointsList);
		
		//drawShapeOutline(g2d);		
		//drawCentroid(g2d);
		
		long end = System.currentTimeMillis();
		
		System.out.println("...finished rendering.....Time taken = " + (end-start) + "mS");
    }
    
    private Polygon getPolygonShape(List<Point> pointsList){
    	
    	Polygon poly = new Polygon();
    	
    	for(Point p:getInitialPointsList()){
    		poly.addPoint((int)p.getX(), (int)p.getY());
    	}
		return poly;
    }


	private void drawCentroid(Graphics2D g2d) {
			
		int sumX = 0;
		int sumY = 0;
		for(Point p:getInitialPointsList()){
			sumX+= p.getX();
			sumY+= p.getY();
		}
		
		int centroidX = sumX / points.length;
		int centroidY = sumY / points.length;
		
		g2d.setColor(Color.RED);
		g2d.drawOval(centroidX-POINT_OVAL_SIZE/2, centroidY-POINT_OVAL_SIZE/2, POINT_OVAL_SIZE, POINT_OVAL_SIZE);	
	}

    /**
     * Old algo - fixed anchor
     * @param g2d
     */
	private void drawTriangleFillFixedAnchor(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.ORANGE);
		int index = 0;
		Point startingPoint = points[0];
		
		for(Point p:getInitialPointsList()){
			
			if(index < points.length-2){
				
				renderTriangle(g2d, startingPoint, points[index+1], points[index+2]);
				
				boolean isLine1InsideShape = isLineInsideShape(polygonShape, startingPoint, points[index+1]);
				boolean isLine2InsideShape = isLineInsideShape(polygonShape, startingPoint, points[index+2]);
				
				System.out.println(index + ". P1 = " + startingPoint + ", P2 = " + points[index+1] + " === Is LINE1 inside shape? " + isLine1InsideShape);
				System.out.println(index + ". P1 = " + startingPoint + ", P2 = " + points[index+2] + " === Is LINE2 inside shape? " + isLine2InsideShape);
			}			
			index++;
		}	
	}
	/**
	 * New algo - using Ear Cutting algorithm
	 * @param g2d
	 * @param pointsList
	 */
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
	
	private int getIndexOfPoint(Point point, List<Point> pointsList) {
		return pointsList.indexOf(point);
	}
	
	private void renderTriangle(Graphics2D g2d, Point p1, Point p2, Point p3){
		
		System.out.println("==== Rendering Triangle, P1 = " + p1 + ", P2 = " + p2 + ", P3 = " + p3);
		
		Polygon poly = new Polygon();
		poly.addPoint((int)p1.getX(), (int)p1.getY());
		poly.addPoint((int)p2.getX(), (int)p2.getY());
		poly.addPoint((int)p3.getX(), (int)p3.getY());
		
		g2d.setColor(new Color(255,240,240));
		g2d.fillPolygon(poly);
		
		g2d.setColor(Color.ORANGE);
		g2d.drawPolygon(poly);	
	}

	private void drawShapeOutline(Graphics2D g2d, List<Point> pointsList) {
		drawPointsAndLines(g2d, pointsList);			
	}
	
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


	private void drawPointsAndLines(Graphics2D g2d, List<Point> pointsList) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.BLUE);
		
		int index = 0;
		Point startingPoint = pointsList.get(0);
		for(Point p:pointsList){
			g2d.drawOval((int)p.getX()-POINT_OVAL_SIZE/2, (int)p.getY()-POINT_OVAL_SIZE/2, POINT_OVAL_SIZE, POINT_OVAL_SIZE);
			g2d.setColor(Color.GREEN);
			g2d.drawString(Integer.toString(index), (int)p.getX()-POINT_OVAL_SIZE/2-7, (int)p.getY()-POINT_OVAL_SIZE/2);
			g2d.setColor(Color.BLUE);
			
			if(index>0){
				Point previousPoint = pointsList.get(index-1);
				g2d.drawLine((int)p.getX(), (int)p.getY(), (int)previousPoint.getX(), (int)previousPoint.getY());
			}
			//draw last line
			if(index==pointsList.size()-1){
				g2d.drawLine((int)p.getX(), (int)p.getY(), (int)startingPoint.getX(), (int)startingPoint.getY());
			}
			
			index++;
		}	
	}
}
