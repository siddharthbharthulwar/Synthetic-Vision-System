package engineTester;
 
import models.RawModel;
import models.TexturedModel;
import paths.Path;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import data.Building;
import data.BuildingEnvironment;
import data.normPoint;
import renderEngine.DisplayManager;

import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import renderEngine.LineRenderer;
import shaders.StaticShader;
import terrain.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Movement;
import entities.Runway;
import entities.RunwayV2;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guidance.GlideMap;
import guidance.guidingBox;
import guis.GuiRenderer;
import guis.GuiTexture;

import toolbox.arrayUtils;
import util.FileUtil;
 
public class Demo {
 
    public static void main(String[] args) throws IOException {
 
        DisplayManager.createDisplay(1920, 1080);
        Loader loader = new Loader();
        TextMaster.init(loader);
        
        
        //INITIALIZING ALL TEXT FIELDS HERE:::
        
        FontType font = new FontType(loader.loadTexture("segoeUI"), new File("res/segoeUI.fnt"));
        GUIText text = new GUIText("Synthetic Vision System", 1, font, new Vector2f(0, 0), 0.5f, true);
        text.setColour(0, 1, 0);
        
        GUIText speed = new GUIText("Speed", 1, font, new Vector2f(0.5f, 0.5f), 0.5f, true);
        speed.setColour(0, 1, 0);
        
        GUIText altitude = new GUIText("Altitude", 1, font, new Vector2f(0.25f, 0.5f), 0.5f, true);
        altitude.setColour(0, 1, 0);
        
        //END INITIALIZING ALL TEXT FIELDS ::

        TextMaster.loadText(text);
        List<Building> buildings1 = FileUtil.loadBuildingsFromJSON("res/data.json");
        List<Building> buildings2 = FileUtil.loadBuildingsFromJSON("res/data2.json");
        List<Building> buildings3 = FileUtil.loadBuildingsFromJSON("res/data3.json");
        List<Building> buildings4 = FileUtil.loadBuildingsFromJSON("res/data4.json");
        
        
        List<Building> buildingList1 = null;

        for(Object obj:buildings1) {
        	 
        	 buildingList1 = (ArrayList<Building>) obj;
      	 
        }
        
        List<Building> buildingList2 = null;
        
        for (Object obj: buildings2) {
        	
        	buildingList2 = (ArrayList<Building>) obj;
        }
        
        List<Building> buildingList3 = null;

        for(Object obj:buildings3) {
        	 
        	 buildingList3 = (ArrayList<Building>) obj;
      	 
        }
        
        List<Building> buildingList4 = null;
        
        for (Object obj: buildings4) {
        	
        	buildingList4 = (ArrayList<Building>) obj;
        }
        
    //    buildingList1.addAll(buildingList2);
      //  buildingList1.addAll(buildingList3);
        //buildingList1.addAll(buildingList4);
        BuildingEnvironment buildingEnvironment = new BuildingEnvironment(buildingList1, 15);
        
        float[] textureCoords = {
        		1, 1	
		};
        
        List<TexturedModel> staticModels = new ArrayList<TexturedModel>();
        List<Entity> entities = new ArrayList<Entity>();
   
        for (Building building: buildingEnvironment.highRiskBuildings) {
        	
        	System.out.println(building.getHeight());
        	building.setHeight(building.getHeight());
        	building.vertices = Building.vecToArray(building.generateVertices());
        	building.indices = Building.intToArray(building.generateIndices());
        	building.normals = Building.vecToArray(building.generateVertexNormals());
        	
        	RawModel model = loader.loadToVAO(building.vertices, textureCoords, building.normals, building.indices);
        	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("red")));
        	staticModels.add(staticModel);
        }
        
        for (Building building: buildingEnvironment.lowRiskBuildings) {
        	
        	
        	System.out.println(building.getHeight());
        	building.setHeight(building.getHeight());
        	building.vertices = Building.vecToArray(building.generateVertices());
        	building.indices = Building.intToArray(building.generateIndices());
        	building.normals = Building.vecToArray(building.generateVertexNormals());
        	
        	RawModel model = loader.loadToVAO(building.vertices, textureCoords, building.normals, building.indices);
        	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        	staticModels.add(staticModel);
        }

        
        @SuppressWarnings("rawtypes")
        RunwayV2 runwayw = new RunwayV2(new Vector2f(2600, 0), new Vector2f(2680, 0), 5000, -800, 22, 100, 4, 150, 4, 450, 90, 20, false);
        @SuppressWarnings("unchecked")
        
		GlideMap boxes = new GlideMap(runwayw, 50, 2400, 3, 175, 50, 15);

        List<Vector3f> pathPoints = new ArrayList<Vector3f>();
        
        //@DISPLACEMENT VECTORS
        float dispX = 70000;
        float dispY = -850;
        float dispZ = -186500;
       
        float[] guidingBoxNormals = {
        		
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1,
        	1, 1, 1

        };
        
        for (guidingBox guide: boxes.boxes) {
        	
        	RawModel model = loader.loadToVAO(guide.generateVertices(), textureCoords, guidingBoxNormals, guide.generateIndices());
        	pathPoints.add(guide.getPosition());
        	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        	staticModels.add(staticModel);
        }
        

        
        
        RawModel model = loader.loadToVAO(runwayw.baseVertices, textureCoords, guidingBoxNormals, runwayw.baseIndices);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("black")));
      	Entity runway = new Entity(staticModel, new Vector3f(dispX, -100, dispZ), 0, 0, 0, 1);

        RawModel centerlineModel = loader.loadToVAO(runwayw.centerlineVertices, textureCoords, guidingBoxNormals, runwayw.centerlineIndices);
        TexturedModel staticCenterlineModel = new TexturedModel(centerlineModel, new ModelTexture(loader.loadTexture("white")));
      	Entity centerlines = new Entity(staticCenterlineModel, new Vector3f(dispX, -100, dispZ), 0, 0, 0, 1);

        RawModel pianoModel = loader.loadToVAO(runwayw.pianoVertices, textureCoords, guidingBoxNormals, runwayw.pianoIndices);
        TexturedModel staticPianoModel = new TexturedModel(pianoModel, new ModelTexture(loader.loadTexture("white")));
      	Entity pianoMarkings = new Entity(staticPianoModel, new Vector3f(dispX, -100, dispZ), 0, 0, 0, 1);
        /*
        for (int j = 0; j < buildingList.size() + boxes.boxes.size(); j++) {
        	entities.add(new Entity(staticModels.get(j), new Vector3f(70000, -10, -175000), 0, 0, 0, 1));
        	
        }
        */
        for (int i = 0; i < buildingList1.size(); i++) {
        	entities.add(new Entity(staticModels.get(i), new Vector3f(70000, -900, -178000), 0, 60, 0, 1));

        }
        
        for (int i = buildingList1.size(); i < buildingList1.size() + boxes.boxes.size(); i++) {
        	entities.add(new Entity(staticModels.get(i), new Vector3f(70000, -10, -175000), 0, 0, 0, 1));

        }
        /*
        for (int i = buildingList1.size() + boxes.boxes.size(); i < buildingList1.size() + boxes.boxes.size() + buildingList2.size(); i++) {
        	entities.add(new Entity(staticModels.get(i), new Vector3f(70000, -900, -186000), 0, 60, 0, 1));

        }
        
        for (int i = buildingList1.size() + boxes.boxes.size()+buildingList2.size(); i < buildingList1.size() + boxes.boxes.size() + buildingList2.size() +buildingList3.size(); i++) {
        	entities.add(new Entity(staticModels.get(i), new Vector3f(74000, -900, -186000), 0, 60, 0, 1));

        }
        */
        List<Vector3f> treeList = new ArrayList<Vector3f>();
        treeList.add(new Vector3f(2000, -70, 0));
        treeList.add(new Vector3f(2100, -70, 115));
        treeList.add(new Vector3f(2200, -70, 6500));
        treeList.add(new Vector3f(2250, -70, 6515));
        treeList.add(new Vector3f(2290, -70, 6500));
        // treeList.add(new Vector3f(2000, -70, 0));
        //treeList.add(new Vector3f(2000, -70, 50));
/*
        for (int i = -100; i < 6000; i+= 70) {
        	treeList.add(new Vector3f(3000, -70, i));
        }
        
        for (int i = -100; i < 1000; i+= 70) {
        	treeList.add(new Vector3f(2000, -70, i));
        }
        */
        
        for (Vector3f vector: treeList) {
            RawModel treeModel = OBJLoader.loadObjModel("tree", loader);
            TexturedModel staticTreeModel = new TexturedModel(treeModel, new ModelTexture(loader.loadTexture("water")));
            Entity tree = new Entity(staticTreeModel, Vector3f.add(vector, new Vector3f(dispX, dispY, dispZ), null), 0, 0, 0, 7);
            entities.add(tree);
        }
        
        
   
        entities.add(runway);
        entities.add(centerlines);
        entities.add(pianoMarkings);
        Vector3f boxLocation = boxes.boxes.get(8).getPosition();
        Camera camera = new Camera(10000, new Vector3f(boxLocation.getX() + 70000, boxLocation.getY() -10, 
        		boxLocation.getZ() - 175000), new Vector3f(runwayw.getTarget().getX() + 70000, 
        		runwayw.getTarget().getY() -10, runwayw.getTarget().getZ() - 175000));
        System.out.println(runwayw.getTarget());
        System.out.println(camera.getFinalPosition());
        

        Light light = new Light(new Vector3f(70000, 10000, -185000), new Vector3f(1,1,1), 11100);
        
        
        //******************************TERRAIN TEXTURE******************
        
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("water"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("black"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("white"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendmap"));
        
        
        
        
        //*********************************END TERRAIN TEXTURE
        
        
        List<GuiTexture> guis = new ArrayList<GuiTexture>();
       // GuiTexture gui = new GuiTexture(loader.loadTexture("2-2"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.05f));
        GuiTexture gui = new GuiTexture(loader.loadTexture("hud"), new Vector2f(0.194f, -0.5f), new Vector2f(1, 1));
        guis.add(gui);
        
        
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);

        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");

        Movement mv = new Movement(runwayw, boxes, 8);
        
        List<Entity> lineEntities = new ArrayList<Entity>();
        
        Path path = new Path(pathPoints);
        
        RawModel pathModel = loader.loadToVAO(path.vertices, textureCoords, path.normals, path.indices);
        TexturedModel staticPathModel = new TexturedModel(pathModel, new ModelTexture(loader.loadTexture("red")));
      	Entity pathM = new Entity(staticPathModel, new Vector3f(dispX, -100, dispZ), 0, 0, 0, 1);     
        
        lineEntities.add(pathM);
        
     
        MasterRenderer renderer = new MasterRenderer();
        System.out.println("SETUP");
        while(!Display.isCloseRequested()){
        	//camera.move();
        	camera.move(25, mv.calculateCameraState(camera, new Vector3f(dispX, dispY, dispZ)));
            //System.out.println(mv.calculateCameraState(camera, new Vector3f(dispX, dispY, dispZ)));
            renderer.processTerrain(terrain);
            for (Entity e: entities) {
            	renderer.processEntity(e);
            }
            
            for (Entity e: lineEntities) {
            	
            	renderer.processLine(e);
            }
         
            renderer.render(light, camera);
            guiRenderer.render(guis);
            
            
            text.setTextString("Stall Probability: " + Float.toString(camera.getRoll()));
            speed.setTextString(Float.toString(camera.getPosition().getZ()));
            altitude.setTextString(Float.toString(camera.getPosition().getY() + 1000));
            
            
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