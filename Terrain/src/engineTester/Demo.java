package engineTester;
 
import models.RawModel;
import models.TexturedModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import data.Building;
import data.normPoint;
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
import entities.Runway;
import entities.RunwayV2;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guidance.glideSlopeMap;
import guidance.guidingBox;
import guis.GuiRenderer;
import guis.GuiTexture;
import logic.glideslope;

import toolbox.arrayUtils;
import util.FileUtil;
 
public class Demo {
 
    public static void main(String[] args) throws IOException {
 
        DisplayManager.createDisplay(800, 800);
        Loader loader = new Loader();
        TextMaster.init(loader);

        List<Building> buildings1 = FileUtil.loadBuildingsFromJSON("res/data.json");
        //List<Building> buildings2 = FileUtil.loadBuildingsFromJSON("res/data2.json");
       // buildings1.addAll(buildings2);
        
        List<Building> buildingList = null;

        for(Object obj:buildings1) {
        	 
        	 buildingList = (ArrayList<Building>) obj;
      	 
        }
        
        @SuppressWarnings("rawtypes")
        RunwayV2 runwayw = new RunwayV2(new Vector2f(1000, 0), new Vector2f(2000, 0), 20000, 150, 15, 300, 300);
        @SuppressWarnings("unchecked")
        
		/*
        glideSlopeMap rwyMap = new glideSlopeMap(r, 1001.5f, 20);
        List<guidingBox> guideList = rwyMap.getBoxes();
        */
        float[] textureCoords = {
        		1, 1	
		};
   
        
        List<TexturedModel> staticModels = new ArrayList<TexturedModel>();
        List<Entity> entities = new ArrayList<Entity>();
        
      
        
        for (Building building: buildingList) {
        	
        	building.setHeight(building.getHeight());
        	building.vertices = Building.vecToArray(building.generateVertices());
        	building.indices = Building.intToArray(building.generateIndices());
        	building.normals = Building.vecToArray(building.generateVertexNormals());
        	
        	
        	
        	
        	if (true) {
        		RawModel model = loader.loadToVAO(building.vertices, textureCoords, building.normals, building.indices);
            	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
            	ModelTexture texture = staticModel.getTexture();
                texture.setShineDamper(10000);
                texture.setReflectivity(0.0f);
                
                staticModels.add(staticModel);
        	}
        	
            
        	
        }
        
        
        
        
        float[] guidingBoxNormals = {
        		
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	
        	0, 0, 0,

        };
        
        
        /*
        for (guidingBox guide: guideList) {
        	RawModel model = loader.loadToVAO(guide.generateVertices(), textureCoords, guidingBoxNormals, guide.generateIndices());
        	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        	ModelTexture texture = staticModel.getTexture();
            texture.setShineDamper(1000);
            texture.setReflectivity(0.0f);
            
            staticModels.add(staticModel);
        }
        */
        
        
        
        RawModel model = loader.loadToVAO(runwayw.vertices, textureCoords, guidingBoxNormals, runwayw.indices);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
      	Entity runway = new Entity(staticModel, new Vector3f(20000, 10000, -20000), 0, 0, 0, 1);


        
        
        for (int j = 0; j < buildingList.size(); j++) {
        	entities.add(new Entity(staticModels.get(j), new Vector3f(70000, -10, -30000), 0, 0, 0, 1));
        	
        }
        /*
        for (int j = 0; j < guideList.size(); j++) {
        	entities.add(new Entity(staticModels.get(j), new Vector3f(0, 0, 0), 0, 0, 0, 1));
        	
        }
        */
        
   
        entities.add(runway);
        Camera camera = new Camera(11110);
        

        Light light = new Light(new Vector3f(10000, -4206, 5700), new Vector3f(1,1,1), 11100);
        
        
        //******************************TERRAIN TEXTURE******************
        
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("lower"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("water2"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("higher"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("white"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendmap"));
        
        
        
        
        //*********************************END TERRAIN TEXTURE
        
        
        List<GuiTexture> guis = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("2-2"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        guis.add(gui);
        
        
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        
        
        
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");

         
     
        MasterRenderer renderer = new MasterRenderer();
        System.out.println("SETUP");
        while(!Display.isCloseRequested()){
        	
            camera.move();
            light.setPosition(new Vector3f(camera.getPosition().getX(), camera.getPosition().getY() - 1000, camera.getPosition().getZ()));
            renderer.processTerrain(terrain);

            for (Entity e: entities) {
            	renderer.processEntity(e);
            }
            
            
            
            renderer.render(light, camera);
            

            
            guiRenderer.render(guis);
            
            
            
            DisplayManager.updateDisplay();
            
        }
        
        
        //***********CLEAN UP *************
        
        TextMaster.cleanUp();
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}