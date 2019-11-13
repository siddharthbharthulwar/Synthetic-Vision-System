package game;

import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.*;
import org.lwjgl.input.*;



public class inputDemo {
	
	List<Box> shapes = new ArrayList<Box>(16);
	

	
	public inputDemo() {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("First Window");
			Display.create();
			
		}
		
		catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		shapes.add(new Box(15, 15));
		shapes.add(new Box(100, 150));
		
		
		//OPENGL Initialization Code
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 640, 480, 0, 1, -1);
		
		
		while(!Display.isCloseRequested()) {
			//Render 
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Display.destroy();
				System.exit(0);
			}
			
			for (Box box : shapes) {
				box.draw();
			}
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	private static class Box {
		public int x, y;
		public boolean selected = false;
		
		private float colorRed, colorBlue, colorGreen;
		
		Box(int x, int y){
			this.x =x;
			this.y=y;
			
			Random randomGenerator = new Random();
			colorRed = randomGenerator.nextFloat();
			colorBlue = randomGenerator.nextFloat();
			colorGreen = randomGenerator.nextFloat();
		}
		
		boolean inBounds(int mousex, int mousey) {
			
			if (mousex > x && mousex < x + 50 && mousey > y && mousey < y + 50) {
				return true;
			}
			else {
				return false;
			}			
		}
		
		void update(int dx, int dy) {
			x += dx;
			y += dy;
			
		}
		
		
		
		void randomizeColors() {
			Random randomGenerator = new Random();
			colorRed = randomGenerator.nextFloat();
			colorBlue = randomGenerator.nextFloat();
			colorGreen = randomGenerator.nextFloat();
		}
		
		void draw() {
			GL11.glColor3f(colorRed, colorGreen, colorBlue);
			
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x, y);
				GL11.glVertex2f(x + 50, y);
				GL11.glVertex2f(x + 50, y + 50);
				GL11.glVertex2f(x, y + 50);
		}
	}
	
	
	
	public static void main(String[] args) {
		new inputDemo();
	}
}