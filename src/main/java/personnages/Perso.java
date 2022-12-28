package personnages;

import javafx.scene.image.Image;

public abstract class Perso {

	protected String nom;
	protected int life; // 1 full coeur = 2 life unit
	public int maxLife = 6; // 3 full coeurs
	protected int atk;
	
	protected static final Image IMAGE = new Image("Isaac_sprite_v3.png");

    protected static final int COLUMNS  =   4;
    protected static final int COUNT    =  3;
    protected static final int OFFSET_X =  85;
    protected static final int OFFSET_Y =  0;
    protected static final int WIDTH    = 85;
	protected static final int HEIGHT   = 97;

	public Perso(String nom, int life, int atk) {
		this.nom = nom;
		this.life = life;
		this.atk = atk;
	}

    public static Image getImage() {
		return IMAGE;
	}
	public static int getColumns() {
		return COLUMNS;
	}
	public static int getCount() {
		return COUNT;
	}
	public static int getOffsetX() {
		return OFFSET_X;
	}
	public static int getOffsetY() {
		return OFFSET_Y;
	}
	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() {
		return HEIGHT;
	}
	public int getLife() {
		return this.life;
	}

	public void setLife(int life) {
		this.life = Math.min(Math.max(life, 0), maxLife);
	}
}