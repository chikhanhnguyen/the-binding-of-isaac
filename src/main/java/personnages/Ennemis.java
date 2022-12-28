package personnages;

import java.sql.ClientInfoStatus;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.util.*;

import com.intech.bindingofisaac.controller.BalleEnnemyController;
import com.intech.bindingofisaac.controller.FreeItemController;
import com.intech.bindingofisaac.controller.FreeItemType;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Ennemis extends Perso {
	private int SceneMaxX = 800;
	private int SceneMaxY = 600;
	public BalleEnnemyController balleEnnemyController = new BalleEnnemyController();
	private double x, y;
	private ImageView ennemiView;
	private AnchorPane scene;
	private ImageView issac;
	public static int collision = 0;
	
	public ImageView getIssac() {
		return issac;
	}

	public void setIssac(ImageView issac) {
		this.issac = issac;
	}

	public ImageView getEnnemiView() {
		return ennemiView;
	}
	private Image ennemiImage;
	private Random rand = new Random();
	public ImageView getRadaEnnemiView() {
		int radaAdjust = 10;
		ImageView radaView = new ImageView(ennemiImage);
		switch (this.currentDirection)
		{
			case UP:
				radaView.setLayoutX(this.ennemiView.getLayoutX());
				radaView.setLayoutY(this.ennemiView.getLayoutY() - radaAdjust);
				break;
			case DOWN:
				radaView.setLayoutX(this.ennemiView.getLayoutX());
				radaView.setLayoutY(this.ennemiView.getLayoutY() + radaAdjust);
				break;
			case LEFT:
				radaView.setLayoutX(this.ennemiView.getLayoutX() - radaAdjust);
				radaView.setLayoutY(this.ennemiView.getLayoutY());
				break;
			case RIGHT:
				radaView.setLayoutX(this.ennemiView.getLayoutX() + radaAdjust);
				radaView.setLayoutY(this.ennemiView.getLayoutY());
				break;
			default:
				break;
		}
		return radaView;
	}

	public AnchorPane getScene() {
		return scene;
	}

	// pour le deplacement d'ennemi
	private final double vitesse = 0.7;
	private int minMaintenirDirectionDurationInMilliseconds = 2000;
	private int maxMaintenirDirectionDurationInMilliseconds = 5000;
	private double nextChange;
	private DirectionEnum currentDirection;
	private HashSet<DirectionEnum> allowedNextDirections;
	// pour le tir
	private int minNextTirInMilliseconds = 500;
	private int maxNextTirInMilliseconds = 3000;
	private double nextTir;
	//
	public Ennemis(String nom, int life, int atk, int i, int j, AnchorPane scene) {
		super(nom, life, atk);
		this.scene = scene;
		this.x = i;
		this.y = j;
		this.setEnnemiView("/ennemie1.png", 0, 50, 50);
		ennemiView.setLayoutX(i);
		ennemiView.setLayoutY(j);
		this.nextChange = 0;
		this.shouldChangeDirection();
		this.shouldTir(rand.nextInt(3) * 1000);
		this.resetDirections();
	}

	private boolean shouldChangeDirection()
	{
		double now = System.currentTimeMillis();
		if (now > this.nextChange)
		{
			this.nextChange = now + minMaintenirDirectionDurationInMilliseconds + rand.nextInt(maxMaintenirDirectionDurationInMilliseconds - minMaintenirDirectionDurationInMilliseconds + 1);
			return true;
		}
		return false;
	}

	private boolean shouldTir(int delayInMillis)
	{
		double now = System.currentTimeMillis();
		if (now > this.nextTir)
		{
			this.nextTir = now + delayInMillis + minNextTirInMilliseconds + rand.nextInt(maxNextTirInMilliseconds - minNextTirInMilliseconds + 1);
			return true;
		}
		return false;
	}

	private void setEnnemiView(String filename, double ratio, double width, double height) {
		Image i = new Image(getClass().getResource(filename).toExternalForm());
		double imageHeight;
		double imageWidth;
		if (ratio > 0) {
			imageHeight = i.getHeight() * ratio;
			imageWidth = i.getWidth() * ratio;
		} else {
			imageHeight = height;
			imageWidth = width;
		}
		Image toReturn = new Image(filename, imageWidth, imageHeight, true, false);
		//
		this.ennemiImage = toReturn;
		this.ennemiView = new ImageView(toReturn);
	}

	private void resetDirections()
	{
		this.allowedNextDirections = new HashSet<>();
		this.allowedNextDirections.add(DirectionEnum.UP);
		this.allowedNextDirections.add(DirectionEnum.DOWN);
		this.allowedNextDirections.add(DirectionEnum.LEFT);
		this.allowedNextDirections.add(DirectionEnum.RIGHT);
		ArrayList<DirectionEnum> array = new ArrayList<>(this.allowedNextDirections);
		this.currentDirection = array.get(rand.nextInt(array.size()));
		this.allowedNextDirections.remove(this.currentDirection);
	}

	private void updateAllowedNextDirections(DirectionEnum nextDirection)
	{
		switch (nextDirection)
		{
			case LEFT:
				this.allowedNextDirections.add(DirectionEnum.RIGHT);
				break;
			case RIGHT:
				this.allowedNextDirections.add(DirectionEnum.LEFT);
				break;
			case DOWN:
				this.allowedNextDirections.add(DirectionEnum.UP);
				break;
			case UP:
				this.allowedNextDirections.add(DirectionEnum.DOWN);
				break;
			default:
				break;
		}
	}

	public void randomChangerDirection(boolean hitMur, DirectionEnum hitDirection)
	{
		ArrayList<DirectionEnum> array = new ArrayList<>(this.allowedNextDirections);
		DirectionEnum nextDirection = array.get(rand.nextInt(array.size()));
		if (!hitMur)
		{
			this.allowedNextDirections.add(this.currentDirection);
			this.updateAllowedNextDirections(nextDirection);
		} else
		{
			this.allowedNextDirections.remove(hitDirection);
			this.updateAllowedNextDirections(nextDirection);
			//this.updateAllowedNextDirections(hitDirection, nextDirection);
		}
		this.allowedNextDirections.remove(nextDirection);
		this.currentDirection = nextDirection;
	}

	public double getX() {
		return this.ennemiView.getLayoutX();
	}

	public double getY() {
		return this.ennemiView.getLayoutY();
	}

	public void tir(double xIsaac, double yIsaac) {
		if (this.shouldTir(0))
		{
			var newBalle = balleEnnemyController.addNewBalle(this);
			balleEnnemyController.moveToPlayer(this, newBalle, xIsaac, yIsaac);
		}
	}

	public void move() {
		if (this.shouldChangeDirection())
		{
			this.randomChangerDirection(false, DirectionEnum.UP);
		}
		switch (this.currentDirection)
		{
			case UP:
				this.ennemiView.setLayoutY(this.ennemiView.getLayoutY() - this.vitesse);
				break;
			case DOWN:
				this.ennemiView.setLayoutY(this.ennemiView.getLayoutY() + this.vitesse);
				break;
			case LEFT:
				this.ennemiView.setLayoutX(this.ennemiView.getLayoutX() - this.vitesse);
				break;
			case RIGHT:
				this.ennemiView.setLayoutX(this.ennemiView.getLayoutX() + this.vitesse);
				break;
			default:
				break;
		}
	}

	public boolean outOfScene()
	{
		if (this.ennemiView.getLayoutX() >= -50 && this.ennemiView.getLayoutY() >= -50 &&
			this.ennemiView.getLayoutX() <= SceneMaxX && this.ennemiView.getLayoutY() <= SceneMaxY) return false;
		return true;
	}
	public void render(GraphicsContext gc) {
		gc.drawImage(this.ennemiView.getImage(), this.ennemiView.getLayoutX(), this.ennemiView.getLayoutY());
	}

	public void dropItem(FreeItemController freeItemController)
	{
		// aléatoire
		FreeItemType freeItemType = FreeItemType.values()[(int)(Math.random()*FreeItemType.values().length)];
		freeItemController.dropItem(freeItemType,
			this.getEnnemiView().getLayoutX(),
			this.getEnnemiView().getLayoutY());
	}
}
