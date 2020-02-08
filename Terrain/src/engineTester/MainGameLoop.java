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
import data.BuildingV2;
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
 
public class MainGameLoop {
 
    public static void main(String[] args) throws IOException {
 
        DisplayManager.createDisplay(800, 800);
        Loader loader = new Loader();
        TextMaster.init(loader);

        FontType font = new FontType(loader.loadTexture("segoeUI"), new File("res/segoeUI.fnt"));
        GUIText text = new GUIText("Synthetic Vision System", 1, font, new Vector2f(0, 0), 0.5f, true);
        text.setColour(1, 1, 1);
        
        List<Building> buildings = FileUtil.loadBuildingsFromJSON("res/data.json");
        
   //     System.out.println(" ==== Building List read is: " + buildings);
   //     System.out.println(" ==== Building List Size is: " + buildings.size());
   //     System.out.println(" ==== Building List Class is: " + buildings.getClass());
        
        
        List<Building> buildingList = null;

        for(Object obj:buildings) {
        	// System.out.println("  ==== Building class for OBJ is: " + obj.getClass());
        	 
        	// System.out.println(" ==== Building List 2 is: " + buildingList);
        	 
        	 buildingList = (ArrayList<Building>) obj;
      	 
        }
        
        Runway r = new Runway(45, 10000, new Vector2f(750, 1000), new Vector2f(1000, 750), 15, 6);
		
        glideSlopeMap rwyMap = new glideSlopeMap(r, 1001.5f, 20);
        List<guidingBox> guideList = rwyMap.getBoxes();
        /*
        List<Building> buildingList = new ArrayList<Building>();
        List<normPoint> p = new ArrayList<normPoint>();
		p.add(new normPoint(0, 0));
		p.add(new normPoint(150, 0));
		p.add(new normPoint(150, 150));
		p.add(new normPoint(75, 255));
		p.add(new normPoint(60, 266));
		p.add(new normPoint(0, 150));
		Building bld = new Building(50, p);
		buildingList.add(bld);
        */
        float[] textureCoords = {
        		1, 1	
		};
        
        float[] normals = {
                
        		0, -0.5f, 0,
        		0, -0.5f, 0,
        		0.5f, 0, 0,
        		0.5f, 0, 0,
        		0, 0.5f, 0,
        		0, 0.5f, 0,
        		-0.5f, 0, 0,
        		-0.5f, 0, 0,
        		0, 0, 0,
        		0, 0, 0, 
        		0, 0, 0.5f, 
        		0, 0, 0.5f,
        		0, -0.5f, 0,
        		0, -0.5f, 0,
        		0.5f, 0, 0,
        		0.5f, 0, 0,
        		0, 0.5f, 0,
        		0, 0.5f, 0,
        		-0.5f, 0, 0,
        		-0.5f, 0, 0,
        		0, 0, 0,
        		0, 0, 0, 
        		0, 0, 0.5f, 
        		0, 0, 0.5f
        };
        
               
        
        
        List<TexturedModel> staticModels = new ArrayList<TexturedModel>();
        List<Entity> entities = new ArrayList<Entity>();
        
      
        float[] vertices = {
        	
        	0, 0, 0,
        	0, 50, 0,
        	50, 0, 0,
        	50, 50, 0,
        	50, 0, 0,
        	50, 50, 0,
        	50, 0, 50,
        	50, 50, 50,
        	50, 0, 50,
        	50, 50, 50,
        	0, 0, 50,
        	0, 50, 50,
        	0, 0, 50,
        	0, 50, 50,
        	0, 0, 0,
        	0, 50, 0,
        	
        	
        	0, 50, 0,
        	50, 50, 0,
        	50, 50, 50,
        	0, 50, 50
        		
        };
        
        int[] indicesNew = {
        	
        	1, 0, 3,
        	0, 2, 3,
        	
        	5, 4, 7,
        	4, 6, 7,
        	
        	9, 8, 11,
        	8, 10, 11,
        	13, 12, 15,
        	12, 14, 15,
        	
        	19, 16, 18,
        	16, 17, 18
        		
        };
        
        float[] normalsNew = {
        	
        	0, 0, 0.9f,
        	0, 0, 0.9f,
        	0, 0, 0.9f,
        	0, 0, 0.9f,
        	0.9f, 0, 0,
        	0.9f, 0, 0,
        	0.9f, 0, 0,
        	0.9f, 0, 0,
        	0, 0,- 0.9f,
        	0, 0,- 0.9f,
        	0, 0, -0.9f,
        	0, 0, -0.9f,
        	-0.9f, 0, 0,
        	-0.9f, 0, 0,
        	-0.9f, 0, 0,
        	-0.9f, 0, 0,
        	0, -0.9f, 0,
        	0, -0.9f, 0,
        	0, -0.9f, 0,
        	0, -0.9f, 0
        	
        };
        
        for (Building building: buildingList) {
        	//System.out.println(" ==== Building = " + building);
        	
        	building.setHeight(building.getHeight());
        	building.vertices = Building.vecToArray(building.generateVertices());
        	building.indices = Building.intToArray(building.generateIndices());
        	building.normals = Building.vecToArray(building.generateVertexNormals());
        	
        	
        	
        	
           // System.out.println(" ==== Building.... vertices = " + building.getVertices() + ", indices = " + building.getIndices());
        	if (true) {
        		RawModel model = loader.loadToVAO(building.vertices, textureCoords, building.normals, building.indices);
            	System.out.println(building.generateIndices());
            	System.out.println(building.generateVertices());
            	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
            	ModelTexture texture = staticModel.getTexture();
                texture.setShineDamper(10000);
                texture.setReflectivity(0.0f);
                
                staticModels.add(staticModel);
        	}
        	
            //System.out.println(building.getCorners().size());
            
            
            //System.out.println(arrayToString(building.generateIndices()));
           // System.out.println(building.generateIndices().length);
            
        	
        }
        
        RawModel treeModel = OBJLoader.loadObjModel("tree", loader);
        TexturedModel staticTreeModel = new TexturedModel(treeModel, new ModelTexture(loader.loadTexture("water")));
        ModelTexture treeTexture = staticTreeModel.getTexture();
        Entity tree = new Entity(staticTreeModel, new Vector3f(500, 0, -500), 0, 0, 0, 100);
        entities.add(tree);
        
        
        float[] guidingBoxNormals = {
        		
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	
        	1, 1, 1,1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1
        };
        
        
        
        for (guidingBox guide: guideList) {
        	RawModel model = loader.loadToVAO(guide.generateVertices(), textureCoords, guidingBoxNormals, guide.generateIndices());
        	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        	ModelTexture texture = staticModel.getTexture();
            texture.setShineDamper(1000);
            texture.setReflectivity(0.0f);
            
            staticModels.add(staticModel);
        }
        
        
        
        
        RawModel model = loader.loadToVAO(r.generateVertices(), textureCoords, normals, r.generateIndices());
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
      	Entity runway = new Entity(staticModel, new Vector3f(21000, 0, -10000), 0, 0, 0, 1);


        
        
        for (int j = 0; j < buildingList.size() + guideList.size(); j++) {
        	entities.add(new Entity(staticModels.get(j), new Vector3f(7000, -10, -3000), 0, 0, 0, 1));
        	
        }
        
        for (int j = 0; j < guideList.size(); j++) {
        	entities.add(new Entity(staticModels.get(j), new Vector3f(0, 0, 0), 0, 0, 0, 1));
        	
        }
   
        entities.add(runway);
        Camera camera = new Camera(11100);
        

        Light light = new Light(new Vector3f(10000, -4206, 5700), new Vector3f(1,1,1), 11100);
        
        
        //******************************TERRAIN TEXTURE******************
        
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("lower"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("water2"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("higher"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("white"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendmap"));
        
        
        
        
        //*********************************END TERRAIN TEXTURE
        
        
        List<GuiTexture> g22 = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("2-2"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g22.add(gui);
        
        List<GuiTexture> g13 = new ArrayList<GuiTexture>();
        GuiTexture gui2 = new GuiTexture(loader.loadTexture("1-3"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g13.add(gui2);
        
        List<GuiTexture> g04 = new ArrayList<GuiTexture>();
        GuiTexture gui3 = new GuiTexture(loader.loadTexture("0-4"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g04.add(gui3);
        
        List<GuiTexture> g31 = new ArrayList<GuiTexture>();
        GuiTexture gui4 = new GuiTexture(loader.loadTexture("3-1"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g31.add(gui4);
        
        List<GuiTexture> g40 = new ArrayList<GuiTexture>();
        GuiTexture g5 = new GuiTexture(loader.loadTexture("4-0"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g40.add(g5);
        
        
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        
        
        
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");
        Terrain terrain2 = new Terrain(-1, -1, loader, texturePack, blendMap, "heightmap");
        Terrain terrain3 = new Terrain(-1, 0, loader, texturePack, blendMap, "heightmap");
        Terrain terrain4 = new Terrain(0, 0, loader, texturePack, blendMap, "heightmap");

         
     
        MasterRenderer renderer = new MasterRenderer();
        System.out.println("SETUP");
        while(!Display.isCloseRequested()){
        	
            camera.move();
            System.out.println(camera.getPosition());
            //light.setPosition(camera.getPosition());
            renderer.processTerrain(terrain);

            for (Entity e: entities) {
            	renderer.processEntity(e);
            }
            
            
            
            renderer.render(light, camera);
            int config = glideslope.PAPIConfiguration(camera.getPosition(), r.getMidpoint());
            

            
            if (config == 1) {
            	guiRenderer.render(g04);
            }
            else if (config == 2) {
            	guiRenderer.render(g13);
            }
            
            else if (config == 3) {
            	guiRenderer.render(g22);
            }
            
            else if (config == 4) {
            	guiRenderer.render(g31);
            }
            
            else {
            	guiRenderer.render(g40);
            }
            TextMaster.render();
            
            
            
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