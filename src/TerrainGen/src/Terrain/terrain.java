package Terrain;

import engine.io.Window;

public class terrain implements Runnable{
	
	public Thread game;
	public static Window window;
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	public int frames;
	public static long time;
	public void start() {
		game = new Thread(this, "game");
		game.run();
	}
	
	public static void init() {
		System.out.println("Initializing Game!");
		window = new Window(WIDTH, HEIGHT, "game");
		window.create();
		time = System.currentTimeMillis();
	}
	
	public void run() {
		init();
		while (!window.shouldClose()) {
			update();
			render();
		}
	}
	
	
	private void update() {
		window.update();

	}
	
	private void render() {
		window.swapBuffers();
	}
	
	
	
	public static void main(String[] args) {
		new terrain().start();
	}
	
	

}
