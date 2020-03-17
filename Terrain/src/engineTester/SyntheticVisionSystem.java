package engineTester;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import data.Building;
import data.BuildingEnvironment;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Movement;
import entities.RunwayV2;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guidance.GlideMap;
import guidance.guidingBox;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import paths.Path;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrain.Terrain;
import util.FileUtil;
import java.math.*;

import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class SyntheticVisionSystem {

	private BuildingEnvironment buildingEnvironment;
	
	//PROBABLY ADD MORE TEXT FIELDS HERE
	private GUIText altitude;
	private GUIText airspeed;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> lineEntities = new ArrayList<Entity>();
	private List<GuiTexture> guis = new ArrayList<GuiTexture>();
	
	private List<TexturedModel> staticModels = new ArrayList<TexturedModel>();
	
	private final float[] textureCoords = {1, 1, 1, 1};
	private final float[] guidingBoxNormals = {1, 1, 1, 1, 1, 1, 1, 
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
	};
	
	
	private GlideMap glideMap;
	private List<Vector3f> intendedPath = new ArrayList<Vector3f>(); //@todo: THIS SHOULD BE A CLASS
	private Path path;
	
	private Camera camera;
	private Light light;
	
	//RENDERERS:
	
	private GuiRenderer guiRenderer;
	private Terrain terrain;
	private Movement movement;
	private MasterRenderer renderer;
	private Loader loader;
	
	@SuppressWarnings("unchecked")
	public SyntheticVisionSystem(int displayWidth, int displayHeight) throws IOException {
		
		//BASIC DISPLAY INITIALIZATION
		DisplayManager.createDisplay(displayWidth, displayHeight);
		this.loader = new Loader();
		TextMaster.init(loader);
		
		//END BASIC DISPLAY INITIALIZATION
		
		//FONT AND TEXT INITIALIZATION
		FontType font = new FontType(loader.loadTexture("segoeUI"), new File("res/segoeUI.fnt"));
		
		this.altitude = new GUIText("Altitude", 1, font, new Vector2f(0.25f, 0.5f), 0.5f, true);
		this.altitude.setColour(0, 1, 0);
		
		this.airspeed = new GUIText("Airspeed", 1, font, new Vector2f(0.5f, 0.5f), 0.5f, true);
		this.airspeed.setColour(0, 1, 0);
		
		//END FONT AND TEXT INITIALIZATION
		
		//BUILDING ENVIRONMENT INITIALIZATION
		
		List<Building> buildings1 = FileUtil.loadBuildingsFromJSON("res/data.json");
		List<Building> buildingList1 = null;
		
		for (Object obj: buildings1) {
			
			buildingList1 = (ArrayList<Building>) obj;
		}
		
		this.buildingEnvironment = new BuildingEnvironment(buildingList1, 15);
		
		List<TexturedModel> staticModels = new ArrayList<TexturedModel>();
		
		for (Building building: this.buildingEnvironment.highRiskBuildings) {
			
			building.setHeight(building.getHeight());
			building.vertices = Building.vecToArray(building.generateVertices());
			building.indices = Building.intToArray(building.generateIndices());
			building.normals = Building.vecToArray(building.generateVertexNormals());
			
			RawModel model = loader.loadToVAO(building.vertices, this.textureCoords, 
					building.normals, building.indices);
			
			TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("red")));
			Entity e = new Entity(staticModel, new Vector3f(10000, -850, -70000), 0, 0, 0, 1);
			this.entities.add(e);
			
		}
		
		for (Building building: this.buildingEnvironment.lowRiskBuildings) {
			
			building.setHeight(building.getHeight());
			building.vertices = Building.vecToArray(building.generateVertices());
			building.indices = Building.intToArray(building.generateIndices());
			building.normals = Building.vecToArray(building.generateVertexNormals());
			
			RawModel model = loader.loadToVAO(building.vertices, this.textureCoords, 
					building.normals, building.indices);
			
			TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
			Entity e = new Entity(staticModel, new Vector3f(10000, -850, -70000), 0, 0, 0, 1);
			this.entities.add(e);
			
		}
		
		//END BUILDING ENVIRONMENT INITIALIZATION
		
		//RUNWAY AND GLIDESLOPE INITIALIZATION
		
		RunwayV2 runway = new RunwayV2(new Vector2f(2600, 0), new Vector2f(2680, 0), 5000, -800, 
				22, 100, 4, 150, 4, 450, 90, 20, false);
		
		this.glideMap = new GlideMap(runway, 50, 2400, 3, 175, 50, 15);
				
		for (guidingBox guide: this.glideMap.boxes) {
			
			RawModel model = loader.loadToVAO(guide.generateVertices(),
					this.textureCoords, this.guidingBoxNormals, 
					guide.generateIndices());
			TexturedModel staticModel = new TexturedModel(model, 
					new ModelTexture(loader.loadTexture("white")));
			
			this.intendedPath.add(guide.getPosition());
			this.staticModels.add(staticModel);
					
		}
		
		//END RUNWAY AND GLIDESLOPE INITIALIZATION
		
		//ENTITY INDEXING AND AGGREGATION
		/*
		for (int i = 0; i < 1; i++) {
			this.entities.add(new Entity(this.staticModels.get(i), 
					new Vector3f(70000, -10, -175000), 
					0, 0, 0, 1));
			
		}
		*/
		Vector3f boxLocation = this.glideMap.boxes.get(8).getPosition();
		this.camera = new Camera(10000, boxLocation, runway.getTarget());
		this.light = new Light(new Vector3f(0, 1000, 0), 
				new Vector3f(1, 1, 1), 11100);
		
		//END ENTITY INDEXING AND AGGREGATION
		
		//TERRAIN TEXTURES
		
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("water"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("black"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("white"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendmap"));
        
        this.terrain = new Terrain(0, -1, loader, texturePack, 
        		blendMap, "heightmap");
        
        GuiTexture gui = new GuiTexture(loader.loadTexture("hud"), new Vector2f(0.194f, -0.5f), new Vector2f(1, 1));
        this.guis.add(gui);
        
        //END TERRAIN TEXTURES
        
        //ENTITY AGGREGATION V2
        
        
	}
	
	public void initialize() {
		
		this.renderer = new MasterRenderer();
		this.guiRenderer = new GuiRenderer(this.loader);
		int speed = 150;
		
		while (!Display.isCloseRequested()) {
			
			this.camera.move();
			int upper = 1;
			int lower = -1;
			if (speed <= 140) {
				speed += 2;
			}
			else if (speed >= 160) {
				speed -= 2;
			}
			else {
				speed += (int) (Math.random() * (upper - lower)) + lower;
			}
			this.renderer.processTerrain(this.terrain);
			
			for (Entity e: this.entities) {
				
				this.renderer.processEntity(e);
			}
			
			this.renderer.render(this.light, this.camera);
			this.guiRenderer.render(this.guis);
						
			this.airspeed.setTextString(Integer.toString(speed));
			this.altitude.setTextString(Integer.toString((int) (this.camera.getPosition().getY() + 1000)));
			
			TextMaster.render();
			DisplayManager.updateDisplay();
		}
		
		TextMaster.cleanUp();
		this.guiRenderer.cleanUp();
		this.renderer.cleanUp();
		this.loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
	
	public static void main(String[] args) throws IOException {
		
		SyntheticVisionSystem svs = new SyntheticVisionSystem(1000, 1000);
		svs.initialize();
	}
}
