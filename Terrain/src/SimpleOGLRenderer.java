import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11. *;
import org.lwjgl.*;

public class SimpleOGLRenderer {
	
	public SimpleOGLRenderer() {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Trial of LWJGL");
			Display.create();
		}
		
		catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while (!Display.isCloseRequested()) {
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			
			glBegin(GL_QUADS);
				glVertex2i(50, 50);
				glVertex2i(50, 100);
				glVertex2i(100, 100);
				glVertex2i(100, 50);
			glEnd();
			
			glBegin(GL_LINES);
				glVertex2i(100, 100);
				glVertex2i(200, 200);
			glEnd();
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	
	
	public static void main(String[] args) {
		new SimpleOGLRenderer();
		
	}
}
