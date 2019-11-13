package game;

import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class OGLRenderer {

	
	public OGLRenderer() {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("First Window");
			Display.create();
			
		}
		
		catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		//OPENGL Initialization Code
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 640, 480, 0, 1, -1);
		
		
		while(!Display.isCloseRequested()) {
			//Render 
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2i(400, 400); //upper left
				GL11.glVertex2i(450, 400); //upper right
				GL11.glVertex2i(450, 450); //bottom right
				GL11.glVertex2i(400, 450); //bottom left
			GL11.glEnd();

			GL11.glBegin(GL11.GL_LINES);
				GL11.glVertex2i(100, 100);
				GL11.glVertex2i(200, 200);
			GL11.glEnd();
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new OGLRenderer();
	}
}