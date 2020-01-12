package engineTester;
 
import models.RawModel;
import models.TexturedModel;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import data.Building;
import data.Point;
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

        
        List<Point> pointList = new ArrayList<Point>();
        pointList.add(new Point(0, 1));
        pointList.add(new Point(0, 0));
        pointList.add(new Point(1, 0));
        pointList.add(new Point(1,1));
        
        
        
        Building b = new Building(4, pointList);
        float[] vertices = b.floatVertProcess();
        
        int[] indices = {
        		
        		1, 0, 3,
        		0, 2, 3,
        		3, 2, 5,
        		2, 4, 5,
        		5, 4, 7,
        		3, 6, 7,
        		7, 6, 9,
        		6, 8, 9
        		
        };

        float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				

				
		};
        
        float[] normals = {
                
                0.2f, 0.2f, 0.2f,
                0.5f, 0.5f, 0.5f
           
        };
       
        //RawModel model = OBJLoader.loadObjModel("cube", loader);
        RawModel model = loader.loadToVAO(vertices, textureCoords, normals, indices);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("grass")));
        
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(100);
        texture.setReflectivity(1);
        
        Entity entity = new Entity(staticModel, new Vector3f(25,-2,-20),90,0,0,1);
        
        

        Light light = new Light(new Vector3f(500,500,-1), new Vector3f(1,1,1));
        
        
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

         
        Camera camera = new Camera(5);
         
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