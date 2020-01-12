package engineTester;
 
import models.RawModel;
import models.TexturedModel;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
 
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrain.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import entities.Camera;
import entities.Entity;
import entities.Light;
import guis.GuiRenderer;
import guis.GuiTexture;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay(1200, 800);
        Loader loader = new Loader();
        
     // cube ///////////////////////////////////////////////////////////////////////
//      v6----- v5
  //   /|      /|
  //  v1------v0|
  //  | |     | |
  //  | |v7---|-|v4
  //  |/      |/
  //  v2------v3

  // vertex coords array for glDrawArrays() =====================================
  // A cube has 6 sides and each side has 2 triangles, therefore, a cube consists
  // of 36 vertices (6 sides * 2 tris * 3 vertices = 36 vertices). And, each
  // vertex is 3 components (x,y,z) of floats, therefore, the size of vertex
  // array is 108 floats (36 * 3 = 108).

        
        
        float[] vertices = {			
				0.5f, -0.5f, 0.5f,
				-0.5f, -0.5f, 0.5f,
				-0.5f, -0.5f, -0.5f,
				0.5f, -0.5f, -0.5f,
				0.5f, 0.5f, 0.5f,
				-0.5f, 0.5f, 0.5f,
				-0.5f, 0.5f, -0.5f,
				0.5f, 0.5f, -0.5f
				
		};
        
        int[] indices = {
                0, 1, 2,
                0, 2, 3,
                4, 0, 3,
                4, 3, 7,
                5, 4, 7,
                5, 7, 6,
                1, 6, 2,
                0, 4, 5,
                0, 5, 1,
                3, 7, 6,
                1, 5, 6,
                3, 6, 2
 
        };
        
		
        /*
        float[] vertices = {
        	
        	0.5f, 0, 0,
        	1.0f, 1.0f, 0,
        	0.5f, 1.5f, 0,
        	0, 1.0f, 0
        	
        	
        };
        
        int[] indices = {
        		
        		0, 1, 2, 3
        };
         
         
         
        */
        
        float[] verticesTest = {
        		
        		213, 1, 0,
        		123, 7, 0,
        		145, 16, 0,
        		132, 16, 0,
        		186, 7, 0,
        		156, 14, 0,
        		170, 11, 0,
        		198, 5, 0,
        		
        		213, 1, 15,
        		123, 7, 15,
        		145, 16, 15,
        		132, 16, 15,
        		186, 7, 15,
        		156, 14, 15,
        		170, 11, 15,
        		198, 5, 15
        };
        
        
        float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
        
        float[] normals = {
                
                0.2f, 0.2f, 0.2f,
                0.5f, 0.5f, 0.5f
           
        };
       
        //RawModel model = OBJLoader.loadObjModel("cube", loader);
        RawModel model = loader.loadToVAO(verticesTest, textureCoords, normals, indices);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
        /*
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(100);
        texture.setReflectivity(1);
         */
        Entity entity = new Entity(staticModel, new Vector3f(25,0,0),90,0,0,1);
        
        

        Light light = new Light(new Vector3f(500,500,-20), new Vector3f(0,1,0));
        
        
        //******************************TERRAIN TEXTURE******************
        
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grass"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("white"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendmap"));
        
        
        
        
        //*********************************END TERRAIN TEXTURE
        
        /*
        List<GuiTexture> guis = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("green"), new Vector2f(0.75f, 0.75f), new Vector2f(0.15f, 0.25f));
        guis.add(gui);
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        */
        
        
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");

         
        Camera camera = new Camera(110);
         
        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested()){
        	
            camera.move();
            
            renderer.processTerrain(terrain);
            
          
            renderer.processEntity(entity);
            renderer.render(light, camera);
            //guiRenderer.render(guis);
            DisplayManager.updateDisplay();
        }
        //guiRenderer.cleanUp();
        renderer.cleanUp();
        
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}