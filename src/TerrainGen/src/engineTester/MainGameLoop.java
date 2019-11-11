package engineTester;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) throws LWJGLException {
		
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				-0.5f, 0.5f, 0,
				-0.5f, -0.5f, 0,
				0.5f,-0.5f, 0, 
				0.5f, 0.5f, 0
				  };
		
		int[] indices = {
				0, 1, 3,
				3, 1, 2
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			
			renderer.prepare();
			renderer.render(model);
			//game logic
			//render
			
			DisplayManager.updateDisplay();
			
		}
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
