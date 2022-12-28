package com.intech.bindingofisaac.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import personnages.Perso;
import personnages.SolidObject;

public class JeuController extends Perso implements Initializable {

	protected SceneController sceneController;
	public void setSceneController(SceneController sceneController)
	{
		this.sceneController = sceneController;
	}
	private BooleanProperty zPressed = new SimpleBooleanProperty();
	private BooleanProperty qPressed = new SimpleBooleanProperty();
	private BooleanProperty sPressed = new SimpleBooleanProperty();
	private BooleanProperty dPressed = new SimpleBooleanProperty();

	protected Rectangle2D view;
	protected Animation animation;
	private boolean alreadyPressed = false;

	@FXML
	protected ImageView imageIsaac = new ImageView(getImage());

	private double t = 2;

	MiniMapController miniMapController;
	ItemController itemController;
	BombeController bombeController;
	FreeItemController freeItemController;
	MarchandController marchandController;
	// controller des balles d'Isaac
	BalleController balleController;
	//
	protected boolean isCurrentSalle;
	protected boolean showMarchand;
	//

	private BooleanBinding keyPressed = zPressed.or(qPressed).or(sPressed).or(dPressed);

	// private float movementVariable = (float) 2.2;
	private float velX = (float) 4;
	private float velY = (float) 4;

	Rectangle murHaut1 = new Rectangle();
	Rectangle murHaut2 = new Rectangle();
	Rectangle murBas1 = new Rectangle();
	Rectangle murBas2 = new Rectangle();
	Rectangle murDroit1 = new Rectangle();
	Rectangle murDroit2 = new Rectangle();
	Rectangle murGauche1 = new Rectangle();
	Rectangle murGauche2 = new Rectangle();
	Rectangle portegauche = new Rectangle();
	Rectangle trapp = new Rectangle();
	Rectangle portedroite = new Rectangle();
	Rectangle portebas = new Rectangle();
	Rectangle portehaut = new Rectangle();
	Rectangle rochergauche1 = new Rectangle();
	Rectangle rocherhaut1 = new Rectangle();
	Rectangle rocherbas1 = new Rectangle();
	Rectangle rocherdroite1 = new Rectangle();
	Rectangle rochergauche2 = new Rectangle();
	Rectangle rocherhaut2 = new Rectangle();
	Rectangle rocherbas2 = new Rectangle();
	Rectangle rocherdroite2 = new Rectangle();
	Rectangle murporteHaut = new Rectangle();
	Rectangle murporteBas = new Rectangle();
	Rectangle murporteDroite = new Rectangle();
	Rectangle murporteGauche = new Rectangle();
	ImageView rocher1 = new ImageView();
	ImageView affportehaut = new ImageView();
	ImageView rocher2 = new ImageView();
	ImageView nodoorgauche = new ImageView();
	ImageView nodoorbas = new ImageView();
	ImageView nodoordroite = new ImageView();
	ImageView rocher3 = new ImageView();
	ImageView rocher4 = new ImageView();
	ImageView portail = new ImageView();
	ImageView affmurhaut  = new ImageView();
	ImageView affportebas  = new ImageView();
	ImageView affportegauche  = new ImageView();
	ImageView affportedroite  = new ImageView();
	ImageView trap  = new ImageView();
	public int xvert = 460;
	public int y1 = 385;
	public int y2 = 65;
	public int yhor = 240;
	public int x1 = 770;
	public int x2 = 135;
	SolidObject solid = new SolidObject(0, 0);
	private Stage stage;


	ArrayList<Rectangle> rectangleArrayList = new ArrayList<Rectangle>();
	ArrayList<Image> coeurImages = new ArrayList<Image>();
	//
	private Canvas gameCanvas;
	private GraphicsContext gc;
	private AnimationTimer coeurTimer;
	public int APP_HEIGHT = 1000;
	public int APP_WIDTH = 1000;

	public GraphicsContext getGc()
	{
		return this.gc;
	}

	//	@FXML
	//	protected Image imgIsaac = new Image(getClass().getClassLoader().getResourceAsStream("isaac.png"));


	@FXML
	private AnchorPane scene;
	//	@FXML
	//	private EnnemisController ennemiesController;

	private Perso modelIsaac;

	public ImageView getImageIsaac() {
		return imageIsaac;
	}

	public Perso getModelIsaac() {
		return modelIsaac;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public void setScene(AnchorPane scene) {
		this.scene = scene;
	}

	public void toAnimate(int x, int y, int w, int h) {
		if (!alreadyPressed) {
			imageIsaac.setViewport(new Rectangle2D(x, y, w, h));

			animation = new SpriteAnimation(imageIsaac, Duration.millis(500), getCount(), getColumns(), x, y, w,
					h);
			// animation.setCycleCount(Animation.INDEFINITE);
			animation.play();
		}
	}

	public void toStop() {
		// TODO Auto-generated method stub
		if (animation != null)
		{
			animation.stop();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		keyPressed.addListener(((observableValue, aBoolean, t1) -> {
			if (!aBoolean) {
				timer.start();
			} else {
				timer.stop();
			}
		}));

		this.miniMapController = new MiniMapController();
		this.miniMapController.addBackground(this);
		this.itemController = new ItemController();
		this.itemController.addItems(this.scene);
		this.bombeController = new BombeController();
		this.freeItemController = new FreeItemController();
		this.showMarchand = false;
		this.balleController = new BalleController();
		//
		this.gameCanvas = new Canvas(APP_WIDTH, APP_HEIGHT);
		this.gc = this.gameCanvas.getGraphicsContext2D();
		this.scene.getChildren().add(this.gameCanvas);
		this.afficherCoeurs();
	}

	// creer une liste de rectangle correspondant aux murs
	ArrayList<Rectangle> buildListMurs(String direction, int salle) {
		ArrayList<Rectangle> liste = new ArrayList<Rectangle>();
		ArrayList<Rectangle> border1 = new ArrayList<Rectangle>();
		ArrayList<Rectangle> border2 = new ArrayList<Rectangle>();
		ArrayList<Rectangle> border3 = new ArrayList<Rectangle>();
		ArrayList<Rectangle> border4 = new ArrayList<Rectangle>();
		SolidObject murs = new SolidObject(0, 0);

		if (salle == 1) {
			border1 = murs.borderrocher(600, 170);
			border2 = murs.borderrocher(0, 0);
			border3 = murs.borderrocher(0, 0);
			border4 = murs.borderrocher(0, 0);
		} else if (salle == 2) {
			border1 = murs.borderrocher(600, 250);
			border2 = murs.borderrocher(400, 230);
			border3 = murs.borderrocher(0, 0);
			border4 = murs.borderrocher(0, 0);
		} else if (salle == 3) {
			border1 = murs.borderrocher(600, 370);
			border2 = murs.borderrocher(600, 170);
			border3 = murs.borderrocher(400, 230);
			border4 = murs.borderrocher(0, 0);
		} else if (salle == 6 ) {
			border1 = murs.borderrocher(375, 330);
			border2 = murs.borderrocher(375, 380);
			border3 = murs.borderrocher(530, 330);
			border4 = murs.borderrocher(530, 380);
		} else if (salle == 7 ) {
			border1 = murs.borderrocher(140, 300);
			border2 = murs.borderrocher(190, 300);
			border3 = murs.borderrocher(0, 0);
			border4 = murs.borderrocher(0, 0);
		}else {
			border1 = murs.borderrocher(0, 0);
			border2 = murs.borderrocher(0, 0);
			border3 = murs.borderrocher(0, 0);
			border4 = murs.borderrocher(0, 0);
		}
		if (direction == "bas") {
			murBas1 = murs.murBas1();
			murBas2 = murs.murBas2();
			if(salle == 2|| salle == 4 || salle == 6|| salle == 7) {
				murporteBas = murs.murporteBas();
			}
			liste.addAll(List.of(murBas1, murBas2,murporteBas, border1.get(1), border2.get(1), border3.get(1), border4.get(1)));
			return liste;
		} else if (direction == "haut") {
			if(salle == 2 || salle == 1 || salle ==  5 || salle ==  8) {
				murporteHaut = murs.murporteHaut();
			}
			murHaut1 = murs.murHaut1();
			murHaut2 = murs.murHaut2();
			liste.addAll(List.of(murHaut1, murHaut2,murporteHaut, border1.get(0), border2.get(0), border3.get(0), border4.get(0)));
			return liste;
		} else if (direction == "gauche") {
			if(salle == 3 || salle == 1|| salle == 5|| salle == 6|| salle == 7 || salle ==  8) {
				murporteGauche = murs.murporteGauche();
			}
			murGauche1 = murs.murGauche1();
			murGauche2 = murs.murGauche2();
			liste.addAll(List.of(murGauche1, murGauche2,murporteGauche, border1.get(3), border2.get(3) , border3.get(3), border4.get(3)));
			return liste;
		} else if (direction == "droite") {
			if(salle == 2 || salle == 3 || salle == 4|| salle == 6|| salle == 7 || salle ==  8) {
				murporteDroite = murs.murporteDroite();
			}
			murDroit1 = murs.murDroit1();
			murDroit2 = murs.murDroit2();
			liste.addAll(List.of(murDroit1, murDroit2,murporteDroite, border1.get(2), border2.get(2), border3.get(2), border4.get(2)));
			return liste;
		} else {
			return null;
		}
	}


	//creer une liste de rectangle correspondant aux portes
	private ArrayList<Rectangle> buildListPorte() {
		SolidObject murs = new SolidObject(0, 0);
		if(salle == 6 ) {
			portegauche = murs.porteGauche();
			portebas = murs.porteBas();
			portedroite = murs.porteDroite();
			portehaut = murs.porteHaut();
			trapp = murs.trap();
			rectangleArrayList.addAll(List.of(trapp, portebas, portedroite, portegauche, portehaut));
			return rectangleArrayList;
		}else if(salle == 7 ) {
			portegauche = murs.porteGauche();
			portebas = murs.porteBas();
			portedroite = murs.porteDroite();
			portehaut = murs.porteHaut();
			trapp = murs.trap2();
			rectangleArrayList.addAll(List.of(trapp, portebas, portedroite, portegauche, portehaut));
			return rectangleArrayList;
		}else {
			portegauche = murs.porteGauche();
			portebas = murs.porteBas();
			portedroite = murs.porteDroite();
			portehaut = murs.porteHaut();

			rectangleArrayList.addAll(List.of(portebas, portedroite, portegauche, portehaut));
			return rectangleArrayList;
		}
	}

	public int salle;
	public void setSalle(int salle) {
		this.salle = salle;
	}

	// Méthode de déplacement et appel des méthodes de collisions
	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			if (isCurrentSalle)
			{
				if (zPressed.get()) {
					if (!collision(imageIsaac,buildListMurs("haut", salle))) {
						imageIsaac.setLayoutY(imageIsaac.getLayoutY() - velY);
					}if(salle == 3) {
						SwitchToSalle1(buildListPorte(), salle, "haut");
					}else if (salle == 4) {
						SwitchToSalle3(buildListPorte(), salle, "haut");
					}else if (salle == 6) {
						SwitchToSalle5(buildListPorte(), salle, "haut");
					}else if (salle == 7) {
						SwitchToSalle8(buildListPorte(), salle, "haut");
					}
				}
				if (qPressed.get()) {
					if (!collision(imageIsaac, buildListMurs("gauche", salle))) {
						imageIsaac.setLayoutX(imageIsaac.getLayoutX() - velX);
						if(salle == 2) {
							SwitchToSalle1(buildListPorte(), salle, "gauche");
						}else if(salle == 4) {
							SwitchToSalle5(buildListPorte(), salle, "gauche");
						}else if(salle == 7) {
							SwitchToSalle6(buildListPorte(), salle, "gauche");
						}
					}
				}
				if (sPressed.get()) {
					if (!collision(imageIsaac, buildListMurs("bas", salle))) {
						imageIsaac.setLayoutY(imageIsaac.getLayoutY() + velY);
						if(salle == 1) {
							SwitchToSalle3(buildListPorte(), salle, "bas");
						}else if(salle == 3) {
							SwitchToSalle4(buildListPorte(), salle, "bas");
						}else if(salle == 5) {
							SwitchToSalle6(buildListPorte(), salle, "bas");
						}else if(salle == 6) {
							SwitchToSalle7(buildListPorte(), salle, "bas");
						}else if(salle == 8) {
							SwitchToSalle7(buildListPorte(), salle, "bas");
						}
					}
				}
				if (dPressed.get()) {
					if (!collision(imageIsaac, buildListMurs("droite", salle))) {
						imageIsaac.setLayoutX(imageIsaac.getLayoutX() + velX);
						if(salle == 1) {
							SwitchToSalle2(buildListPorte(), salle, "droite");
						}if(salle == 5) {
							SwitchToSalle4(buildListPorte(), salle, "droite");
						}
					}
				}
			}
		}
	};

	//méthode de collision
	public boolean collision(ImageView imageIsaac, ArrayList<Rectangle> rectangleArrayList) {
		Rectangle murs;
		for (int i = 0; i < rectangleArrayList.size(); i++) {
			murs = rectangleArrayList.get(i);
			if (imageIsaac.getBoundsInParent().intersects(murs.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}

	public boolean SwitchToSalle1(ArrayList<Rectangle> mur, int salle, String direction) {
		ActionEvent action = new ActionEvent();
		for (int i = 0; i < mur.size(); i++) {
			if (imageIsaac.getBoundsInParent().intersects(mur.get(i).getBoundsInParent())) {
				System.out.println("Changement de piece/scene ");
				action.getEventType();
				try {
					switchSalle(3, direction);
				} catch (IOException e) {
				}
				return true;
			}
		}
		return false;
	}
	public boolean SwitchToSalle2(ArrayList<Rectangle> mur, int salle, String direction) {
		ActionEvent action = new ActionEvent();
		for (int i = 0; i < mur.size(); i++) {
			if (imageIsaac.getBoundsInParent().intersects(mur.get(i).getBoundsInParent())) {
				System.out.println("Changement de piece/scene ");
				action.getEventType();
				try {
					switchSalle(1, direction);
				} catch (IOException e) {
				}
				return true;
			}
		}
		return false;
	}
	public boolean SwitchToSalle3(ArrayList<Rectangle> mur, int salle, String direction) {
		ActionEvent action = new ActionEvent();
		for (int i = 0; i < mur.size(); i++) {
			if (imageIsaac.getBoundsInParent().intersects(mur.get(i).getBoundsInParent())) {
				System.out.println("Changement de piece/scene ");
				action.getEventType();
				try {
					switchSalle(2, direction);
				} catch (IOException e) {
				}
				return true;
			}
		}
		return false;
	}
	public boolean SwitchToSalle4(ArrayList<Rectangle> mur, int salle, String direction) {
		ActionEvent action = new ActionEvent();
		for (int i = 0; i < mur.size(); i++) {
			if (imageIsaac.getBoundsInParent().intersects(mur.get(i).getBoundsInParent())) {
				System.out.println("Changement de piece/scene ");
				action.getEventType();
				try {
					switchSalle(4, direction);
				} catch (IOException e) {
				}
				return true;
			}
		}
		return false;
	}
	public boolean SwitchToSalle5(ArrayList<Rectangle> mur, int salle, String direction) {
		ActionEvent action = new ActionEvent();
		for (int i = 0; i < mur.size(); i++) {
			if (imageIsaac.getBoundsInParent().intersects(mur.get(i).getBoundsInParent())) {
				System.out.println("Changement de piece/scene ");
				action.getEventType();
				try {
					switchSalle(5, direction);
				} catch (IOException e) {
				}
				return true;
			}
		}
		return false;
	}
	public boolean SwitchToSalle6(ArrayList<Rectangle> mur, int salle, String direction) {
		ActionEvent action = new ActionEvent();
		for (int i = 0; i < mur.size(); i++) {
			if (imageIsaac.getBoundsInParent().intersects(mur.get(i).getBoundsInParent())) {
				System.out.println("Changement de piece/scene ");
				action.getEventType();
				try {
					switchSalle(6, direction);
				} catch (IOException e) {
				}
				return true;
			}
		}
		return false;
	}
	public boolean SwitchToSalle7(ArrayList<Rectangle> mur, int salle, String direction) {
		ActionEvent action = new ActionEvent();
		for (int i = 0; i < mur.size(); i++) {
			if (imageIsaac.getBoundsInParent().intersects(mur.get(i).getBoundsInParent())) {
				System.out.println("Changement de piece/scene ");
				action.getEventType();
				try {
					switchSalle(7, direction);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}
	public boolean SwitchToSalle8(ArrayList<Rectangle> mur, int salle, String direction) {
		ActionEvent action = new ActionEvent();
		for (int i = 0; i < mur.size(); i++) {
			if (imageIsaac.getBoundsInParent().intersects(mur.get(i).getBoundsInParent())) {
				System.out.println("Changement de piece/scene ");
				action.getEventType();
				try {
					switchSalle(8, direction);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}


	public void switchSalle(int salle, String direction) throws IOException {
		this.resetPressed();
		if(salle == 1) {
			JeuController controller = this.sceneController.activate(2);
			controller.prendreSalleItems(this);
			if(direction == "haut") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y1);
			}else if(direction == "gauche") {
				controller.imageIsaac.setLayoutX(x1);
				controller.imageIsaac.setLayoutY(yhor);
			}else if(direction == "bas") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y2);
			}else if(direction == "droite") {
				controller.imageIsaac.setLayoutX(x2);
				controller.imageIsaac.setLayoutY(yhor);
			}
		} else if(salle == 2) {
			JeuController controller = this.sceneController.activate(3);
			controller.prendreSalleItems(this);
			if(direction == "haut") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y1);
			}else if(direction == "gauche") {
				controller.imageIsaac.setLayoutX(x1);
				controller.imageIsaac.setLayoutY(yhor);
			}else if(direction == "bas") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y2);
			}else if(direction == "droite") {
				controller.imageIsaac.setLayoutX(x2);
				controller.imageIsaac.setLayoutY(yhor);
			}
		}else if(salle == 3) {
			JeuController controller = this.sceneController.activate(1);
			controller.prendreSalleItems(this);
			if(direction == "haut") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y1);
			}else if(direction == "gauche") {
				controller.imageIsaac.setLayoutX(x1);
				controller.imageIsaac.setLayoutY(yhor);
			}else if(direction == "bas") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y2);
			}else if(direction == "droite") {
				controller.imageIsaac.setLayoutX(x2);
				controller.imageIsaac.setLayoutY(yhor);
			}
		}else if(salle == 4) {
			JeuController controller = this.sceneController.activate(4);
			controller.prendreSalleItems(this);
			if(direction == "haut") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y1);
			}else if(direction == "gauche") {
				controller.imageIsaac.setLayoutX(x1);
				controller.imageIsaac.setLayoutY(yhor);
			}else if(direction == "bas") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y2);
			}else if(direction == "droite") {
				controller.imageIsaac.setLayoutX(x2);
				controller.imageIsaac.setLayoutY(yhor);
			}
		}else if(salle == 5) {
			JeuController controller = this.sceneController.activate(5);
			controller.prendreSalleItems(this);
			if(direction == "haut") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y1);
			}else if(direction == "gauche") {
				controller.imageIsaac.setLayoutX(x1);
				controller.imageIsaac.setLayoutY(yhor);
			}else if(direction == "bas") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y2);
			}else if(direction == "droite") {
				controller.imageIsaac.setLayoutX(x2);
				controller.imageIsaac.setLayoutY(yhor);
			}
		}else if(salle == 6) {
			JeuController controller = this.sceneController.activate(6);
			controller.prendreSalleItems(this);
			if(direction == "haut") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y1);
			}else if(direction == "gauche") {
				controller.imageIsaac.setLayoutX(460);
				controller.imageIsaac.setLayoutY(240);

			}else if(direction == "bas") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y2);
			}else if(direction == "droite") {
				controller.imageIsaac.setLayoutX(x2);
				controller.imageIsaac.setLayoutY(yhor);
			}
		}else if(salle == 7) {
			JeuController controller = this.sceneController.activate(7);
			controller.prendreSalleItems(this);
			//
			controller.imageIsaac.setLayoutX(250);
			controller.imageIsaac.setLayoutY(340);
			//			controller.imageIsaac.setLayoutX(460);
			//			controller.imageIsaac.setLayoutY(240);
		}else if(salle == 8) {
			JeuController controller = this.sceneController.activate(8);
			controller.prendreSalleItems(this);
			if(direction == "haut") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y1);
			}else if(direction == "gauche") {
				controller.imageIsaac.setLayoutX(x1);
				controller.imageIsaac.setLayoutY(yhor);
			}else if(direction == "bas") {
				controller.imageIsaac.setLayoutX(xvert);
				controller.imageIsaac.setLayoutY(y2);
			}else if(direction == "droite") {
				controller.imageIsaac.setLayoutX(x2);
				controller.imageIsaac.setLayoutY(yhor);
			}
			//controller.setStage(stage);
		}
	}

	//méthode de déplacement avec animations du personnage et tirs
	@FXML
	public void handleKeyPressed(KeyEvent e) {
		System.out.println(e.getCode());
		if (e.getCode() == KeyCode.Z) {
			this.toAnimate(0, 295, 85, 97);
			zPressed.set(true);
			alreadyPressed = true;
		}
		if (e.getCode() == KeyCode.Q) {
			this.toAnimate(0, 97, 85, 97);
			qPressed.set(true);
			alreadyPressed = true;
		}
		if (e.getCode() == KeyCode.S) {
			this.toAnimate(0, 0, 85, 97);
			sPressed.set(true);
			alreadyPressed = true;
		}
		if (e.getCode() == KeyCode.D) {
			this.toAnimate(0, 194, 85, 97);
			dPressed.set(true);
			alreadyPressed = true;
		}
		if (e.getCode() == KeyCode.J || e.getCode() == KeyCode.I || e.getCode() == KeyCode.K
				|| e.getCode() == KeyCode.L) {
			t += 0.2;
			if (t > 1.4) {
				if (e.getCode() == KeyCode.J) {
					balleController.tir(scene, TirDirection.LEFT, this.getImageIsaac());
				}
				if (e.getCode() == KeyCode.L) {
					balleController.tir(scene, TirDirection.RIGHT, this.getImageIsaac());
				}
				if (e.getCode() == KeyCode.I) {
					balleController.tir(scene, TirDirection.UP, this.getImageIsaac());
				}
				if (e.getCode() == KeyCode.K) {
					balleController.tir(scene, TirDirection.DOWN, this.getImageIsaac());
				}
				t = 0;
			}
		}
		if (e.getCode() == KeyCode.E) {
			bombeController.setBombe(this);
		}
	}

	private void resetPressed()
	{
		alreadyPressed = false;
		zPressed.set(false);
		qPressed.set(false);
		sPressed.set(false);
		dPressed.set(false);
		this.toStop();
	}

	@FXML
	public void handleKeyReleased(KeyEvent e) {
		alreadyPressed = false;
		if (e.getCode() == KeyCode.Z) {
			zPressed.set(false);
			this.toStop();
		}
		if (e.getCode() == KeyCode.Q) {
			qPressed.set(false);
			this.toStop();
		}
		if (e.getCode() == KeyCode.S) {
			sPressed.set(false);
			this.toStop();
		}
		if (e.getCode() == KeyCode.D) {
			dPressed.set(false);
			this.toStop();
		}
		if (e.getCode() == KeyCode.I) {
			t = 1.4;
			this.toStop();
		}
		if (e.getCode() == KeyCode.J) {
			t = 1.4;
			this.toStop();
		}
		if (e.getCode() == KeyCode.K) {
			t = 1.4;
			this.toStop();
		}
		if (e.getCode() == KeyCode.L) {
			t = 1.4;
			this.toStop();
		}
	}
	public JeuController() {
		super("Isaac", 6, 25);
		this.isCurrentSalle = true;
	}

	public void prendreSalleItems(JeuController jeuController)
	{
		// set item and life to the new salle
		this.itemController.clone(jeuController.itemController);
		if (jeuController.marchandController != null)
		{
			this.marchandController = jeuController.marchandController;
		}
		this.setLife(jeuController.life);
	}

	protected void showGameOver() throws IOException {
		this.sceneController.activate(9);
	}

	// pour mettre à jour la santé d'Issac après la collision avec la bombe
	protected void updateCoeurs(GraphicsContext gc) {
		int life = this.getLife();
		if (life > 0) {
			life -= bombeController.collision(imageIsaac);
			this.setLife(life);
		}
		// 1 full coeur = 2 life unit
		coeurImages = new ArrayList<Image>();
		int nbFullCoeurs = Math.round(life / 2);
		int nbHalfCoeurs = life % 2;
		int nbEmptyCoeurs = Math.round(this.maxLife / 2) - nbFullCoeurs - nbHalfCoeurs;
		for (int i = 0; i < nbFullCoeurs; i++) {
			Image coeurImage = new Image("/images/coeurFull.png", 17, 17, true, false);
			coeurImages.add(coeurImage);
		}
		for (int i = 0; i < nbHalfCoeurs; i++) {
			Image coeurImage = new Image("/images/coeurHalf.png", 17, 17, true, false);
			coeurImages.add(coeurImage);
		}
		for (int i = 0; i < nbEmptyCoeurs; i++) {
			Image coeurImage = new Image("/images/coeurNoir.png", 17, 17, true, false);
			coeurImages.add(coeurImage);
		}
		//
		for (int i = 0; i < coeurImages.size(); i++) {
			gc.drawImage(coeurImages.get(i), 90.0 + 20.0 * i, 45.0);
		}
	}

	// afficher bombes + free items + marchand items
	protected void showItems(long now)
	{
		this.miniMapController.showMiniMap(this);
		this.bombeController.showBombes(now, this);
		this.freeItemController.showItems(now, this);
		if (this.marchandController != null && this.showMarchand)
		{
			this.marchandController.showItems(now, this);
		}
	}

	// update les balles d'Isaac
	protected void updateBalleIsaac()
	{
		// par default, la liste d'ennemis est vide
		this.balleController.collision(new ArrayList<>(), this.scene, this.freeItemController);
	}

	private void afficherCoeurs() {
		coeurTimer = new AnimationTimer() {
			public void handle(long now) {
				gc.clearRect(0, 30, APP_WIDTH, APP_HEIGHT);
				//
				if (isCurrentSalle)
				{
					updateBalleIsaac();
					updateCoeurs(gc);
					showItems(now);
					if (getLife() == 0 && isCurrentSalle) {
						try {
							showGameOver();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}
					}
				}
			}
		};
		coeurTimer.start();
	}
	public AnchorPane getScene() {
		return scene;
	}
	public AnimationTimer getTimer() {
		return timer;
	}
	public int getSalle() {
		return salle;
	}
}