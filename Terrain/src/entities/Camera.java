package entities;
 
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
 
public class Camera {
     
    private Vector3f position = new Vector3f(0,0,0);
    private Vector3f direction;
    private float pitch;
    private float yaw;
    private float roll = 20;
    private int speed;
    private Vector3f finalPosition;
    
    public Camera(int s, Vector3f initialPosition, Vector3f finalPosition){
    	this.position = initialPosition;
    	this.speed = s;
    	/*
    	this.direction = new Vector3f(0, getUnitDirectionVector(initialPosition, 
    			finalPosition).getY() + 0.005f, getUnitDirectionVector(initialPosition, finalPosition).getZ());
    	*/
    	this.direction = new Vector3f(0, -0.052335956f, -0.99862953475f);
    	System.out.println(this.direction);
    	this.finalPosition = finalPosition;
    }
    
    public Vector3f getFinalPosition() {
    	return this.finalPosition;
    }
     
    public void move(){
    	
    	
    	//SLOW COMMANDS
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            position.z-=this.speed * 0.01f;
            
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            position.x+=this.speed * 0.01f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            position.x-=this.speed * 0.01f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            position.z+=this.speed * 0.01f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_R)){
            position.y+=this.speed * 0.01f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F)){
            position.y-=this.speed* 0.01f;
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
        	this.pitch -= 0.2f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
        	this.pitch += 0.2f;
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
        	this.yaw -= 0.2f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
        	this.yaw += 0.2f;
        }
        
        
        
        //FAST COMMANDS
        
        if(Keyboard.isKeyDown(Keyboard.KEY_I)){
            position.z-=this.speed * 0.1f;
            
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_L)){
            position.x+=this.speed * 0.1f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_J)){
            position.x-=this.speed * 0.1f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_K)){
            position.z+=this.speed * 0.1f;
        }

    }
    
    public void move(int speed, int state) {
    	
    	if (state == 0) {
    		this.position.y += speed * this.direction.getY();
    		this.position.z += speed * this.direction.getZ();
    		
    		
    	}
    	else if (state == 1) {

    		this.position.y += (speed / 15) * this.direction.getY();
    		this.position.z += (speed / 3) * this.direction.getZ();
    		this.pitch -= 0.01;
    		
    	}
    	else if (state == 2) {
    		

    		this.position.y += (speed / 100) * this.direction.getY();
    		this.position.z += (speed / 7) * this.direction.getZ();
    	}
    }
    
    public void moveVector(int speed) {
    	if (true) {

        	position.y += speed * this.direction.getY();
        	position.z += speed * this.direction.getZ();
    	}
    	//this.position.getZ() >= this.finalPosition.getZ()
    	else {
    		position.z += (speed / 10) * this.direction.getZ();
    	}
    	
    }
    
    public void rotateVector(int speed) {
    	
    	
    	
    }

    public Vector3f getUnitDirectionVector(Vector3f initialPos, Vector3f finalPos) {
    	
    	Vector3f difference = new Vector3f(finalPos.getX() - initialPos.getX(), finalPos.getY() - initialPos.getY(),
    			finalPos.getZ() - initialPos.getZ());
    	difference.normalise();
    	return difference;
    	
    }
    
    
 
    public Vector3f getPosition() {
        return position;
    }
 
    public float getPitch() {
        return pitch;
    }
 
    public float getYaw() {
        return yaw;
    }
 
    public float getRoll() {
        return roll;
    }
     
     
 
}