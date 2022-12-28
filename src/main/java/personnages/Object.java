package personnages;

import javafx.scene.Node;
//import javafx.scene.shape.Rectangle;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Object {
	
	protected int x, y;
	protected String name;
	protected boolean collision = false;
	protected float velX = 0, velY = 0;
	protected Node corps;// pour me permettre de matérialiser des nodes (sachant que tout élément javafx est une node)
	
	
	
	
	
	
	public Object(int x, int y) {
		
		this.x = x;
		this.y = y;
	};
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	

	public Node getCorps() {
		return corps;
	}

	public void setCorps(Node corps) {
		this.corps = corps;
		this.collision = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	
	
}
