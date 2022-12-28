package personnages;

import com.intech.bindingofisaac.controller.JeuController;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Boss {

	private static final Image IMAGE = new Image("PC Computer - The Binding of Isaac Rebirth - Host.png");
	private static final Image IMAGE2 = new Image("bulletEn.png");
	private ImageView balleBoss = new ImageView(IMAGE2);
	private int balleHeight = 25;
	private int balleWidth = 25;




	private static final int COLUMNS  =  3;
	private static final int COUNT    =  3;
	private static final int OFFSET_X =  0;
	private static final int OFFSET_Y =  0;
	private static final int WIDTH    = 32;
	private static final int HEIGHT   = 64;


	public int getBalleHeight() {
		return balleHeight;
	}
	public int getBalleWidth() {
		return balleWidth;
	}
	public static Image getImage2() {
		return IMAGE2;
	}
	public ImageView getBalleBoss() {
		return balleBoss;
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

	public void attachToBoss(ImageView b) {
		balleBoss.setLayoutX(b.getLayoutX());
		balleBoss.setLayoutY(b.getLayoutY());
		
		System.out.println("Pos X : " + b.getLayoutX());
	}
	//Permet � la balle de faire le mouvement vers la gauche
	public void shootLeft() {
		balleBoss.setFitHeight(25);
		balleBoss.setFitWidth(25);
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(balleBoss);
		translate.setDuration(Duration.millis(2000));
		translate.setByX(-1200);
		translate.play();
	}

	//....vers la droite
	public void shootRight() {
		balleBoss.setFitHeight(25);
		balleBoss.setFitWidth(25);
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(balleBoss);
		translate.setDuration(Duration.millis(2000));
		translate.setByX(1200);
		translate.play();
	}

	//...vers le haut
	public void shootUp() {
		balleBoss.setFitHeight(25);
		balleBoss.setFitWidth(25);
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(balleBoss);
		translate.setDuration(Duration.millis(2000));
		translate.setByY(-1200);
		translate.play();
	}

	//...vers le bas
	public void shootDown() {
		balleBoss.setFitHeight(25);
		balleBoss.setFitWidth(25);
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(balleBoss);
		translate.setDuration(Duration.millis(2000));
		translate.setByY(1200);
		translate.play();
	}

}
