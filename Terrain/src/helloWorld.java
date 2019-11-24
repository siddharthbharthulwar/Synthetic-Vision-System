import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11. *;
import org.lwjgl.*;

public class helloWorld {
	
	public helloWorld() {
		try {
			Display.setDisplayMode(new DisplayMode(1920, 1080));
			Display.setTitle("Trial of LWJGL");
			Display.create();
		}
		
		catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		while (!Display.isCloseRequested()) {
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	
	
	public static void main(String[] args) {
		new helloWorld();
		
	}
}
