package game;

import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class display {

	
	public display() {
		try {
			Display.setDisplayMode(new DisplayMode(640, 800));
			Display.setTitle("First Window");
			Display.create();
			
		}
		
		catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		while(!Display.isCloseRequested()) {
			//Render 
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new display();
	}
}
