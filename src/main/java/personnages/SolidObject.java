package personnages;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SolidObject extends Object {

	public SolidObject(int x, int y) {
		super(x, y);
	
	}

	protected Rectangle solid;
	public Rectangle mur;
	public Rectangle rocher;
	public Rectangle porte;
	
	public  Rectangle trap() {
		porte = new Rectangle();
		porte.setLayoutX(443);
		porte.setLayoutY(358);
		porte.setHeight(40);
		porte.setWidth(80);
		porte.setFill(Color.BLUE);
		
		return porte;
		
	}

	public  Rectangle trap2() {
		porte = new Rectangle();
		porte.setLayoutX(175);
		porte.setLayoutY(370);
		porte.setHeight(80);
		porte.setWidth(40);
		porte.setFill(Color.BLUE);
		
		return porte;
		
	}
	public ImageView rocher(int x, int y) {
		ImageView x1 = new ImageView("/images/rock.png");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(64);
		x1.setFitWidth(64);
		return x1;
	}
	
	public ImageView echelle(int x, int y) {
		ImageView x1 = new ImageView("ladder.png");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(80);
		x1.setFitWidth(80);
		return x1;
	}
	
	public ImageView trap(int x, int y) {
		ImageView x1 = new ImageView("trap.png");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(80);
		x1.setFitWidth(80);
		return x1;
	}
	
	public ImageView affporteBasrouge(int x, int y) {
		ImageView x1 = new ImageView("portebasrouge.png");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(65);
		x1.setFitWidth(120);
		return x1;
	}
	public ImageView affporteDroiterouge(int x, int y) {
		ImageView x1 = new ImageView("portedroiterouge.png");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(120);
		x1.setFitWidth(72);
		return x1;
	}
	public ImageView affporteGaucherouge(int x, int y) {
		ImageView x1 = new ImageView("portegaucherouge.png");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(120);
		x1.setFitWidth(75);
		return x1;
	}
	public ImageView affmurHautrouge(int x, int y) {
		ImageView x1 = new ImageView("murhautrouge.png");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(66);
		x1.setFitWidth(120);
		return x1;
	}
	public ImageView affporteHaut(int x, int y) {
		ImageView x1 = new ImageView("portehaut.jpg");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(95);
		x1.setFitWidth(150);
		return x1;
	}
	public ImageView affmurGauche(int x, int y) {
		ImageView x1 = new ImageView("nodoorgauche.jpg");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(150);
		x1.setFitWidth(95);
		return x1;
	}
	public ImageView affmurBas(int x, int y) {
		ImageView x1 = new ImageView("nodoorbas.jpg");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(95);
		x1.setFitWidth(150);
		return x1;
	}
	public ImageView affmurDroite(int x, int y) {
		ImageView x1 = new ImageView("nodoordroite.jpg");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(150);
		x1.setFitWidth(95);
		return x1;
	}
	public ImageView portail(int x, int y) {
		ImageView x1 = new ImageView("portaill.gif");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(64);
		x1.setFitWidth(96);
		return x1;
	}
	public ImageView portailhaut(int x, int y) {
		ImageView x1 = new ImageView("portail.gif");
		x1.setLayoutX(x);//250
		x1.setLayoutY(y);//200
		
		x1.setFitHeight(64);
		x1.setFitWidth(96);
		return x1;
	}
	public  Rectangle rock1(int x, int y) {
		rocher = new Rectangle();
		rocher.setLayoutX(x);
		rocher.setLayoutY(y);
		rocher.setHeight(96);
		rocher.setWidth(96);
		rocher.setFill(Color.BLUE);
		
		return rocher;
	}
	public  Rectangle borderrock1(int x, int y) {
		rocher = new Rectangle();
		rocher.setLayoutX(x);
		rocher.setLayoutY(y);
		rocher.setHeight(1);
		rocher.setWidth(38);
		rocher.setFill(Color.BLUE);
		
		return rocher;
		
	}
	
	
	public  Rectangle borderrock2(int x, int y) {
		rocher = new Rectangle();
		rocher.setLayoutX(x);
		rocher.setLayoutY(y);
		rocher.setHeight(15);
		rocher.setWidth(1);
		rocher.setFill(Color.BLUE);
		
		return rocher;
		
	}
	

	public ArrayList<Rectangle> borderrocher(int x, int y){
		ArrayList<Rectangle> liste = new ArrayList<Rectangle>();
		Rectangle rochergauche = new Rectangle();
		Rectangle rocherhaut = new Rectangle();
		Rectangle rocherbas = new Rectangle();
		Rectangle rocherdroite = new Rectangle();
		
		rochergauche = borderrock2(x + 5, y + 10);
		rocherbas = borderrock1(x + 11, y + 30);
		rocherhaut = borderrock1(x + 11, y + 5);
		rocherdroite = borderrock2(x + 55, y + 10);
		liste.addAll(List.of(rocherbas, rocherhaut, rochergauche, rocherdroite));
		return liste;
		
	}
	
	public  Rectangle porteHaut() {
		porte = new Rectangle();
		porte.setLayoutX(450);
		porte.setLayoutY(16);
		porte.setHeight(32);
		porte.setWidth(65);
		porte.setFill(Color.BLUE);
		
		return porte;
		
	}
	public  Rectangle murporteHaut() {
		porte = new Rectangle();
		porte.setLayoutX(450);
		porte.setLayoutY(0);
		porte.setHeight(62);
		porte.setWidth(65);
		porte.setFill(Color.RED);
		
		return porte;
		
	}
	
	public  Rectangle porteBas() {
		porte = new Rectangle();
		porte.setLayoutX(450);
		porte.setLayoutY(464);
		porte.setHeight(32);
		porte.setWidth(65);
		porte.setFill(Color.BLUE);
		
		return porte;
		
	}
	public  Rectangle murporteBas() {
		porte = new Rectangle();
		porte.setLayoutX(450);
		porte.setLayoutY(450);
		porte.setHeight(32);
		porte.setWidth(65);
		porte.setFill(Color.RED);
		
		return porte;
		
	}
	
	public  Rectangle porteGauche() {
		porte = new Rectangle();
		porte.setLayoutX(90);
		porte.setLayoutY(222);
		porte.setHeight(84);
		porte.setWidth(32);
		porte.setFill(Color.BLUE);
		
		return porte;
		
	}
	public  Rectangle murporteGauche() {
		porte = new Rectangle();
		porte.setLayoutX(0);
		porte.setLayoutY(222);
		porte.setHeight(175);
		porte.setWidth(132);
		porte.setFill(Color.RED);
		
		return porte;
		
	}
	
	
	public  Rectangle porteDroite() {
		porte = new Rectangle();
		porte.setLayoutX(834);
		porte.setLayoutY(222);
		porte.setHeight(84);
		porte.setWidth(32);
		porte.setFill(Color.BLUE);
		
		return porte;
		
	}
	public  Rectangle murporteDroite() {
		porte = new Rectangle();
		porte.setLayoutX(815);
		porte.setLayoutY(220);
		porte.setHeight(84);
		porte.setWidth(32);
		porte.setFill(Color.RED);
		
		return porte;
		
	}
	public  Rectangle murHaut1() {
		mur = new Rectangle();
		name = "mur";
		mur.setLayoutX(50);
		mur.setLayoutY(0);
		mur.setHeight(62);
		mur.setWidth(370);
		mur.setFill(Color.RED);
		return mur;
		
	}
	
	public  Rectangle murHaut2() {
		mur = new Rectangle();
		name = "mur";
		mur.setLayoutX(545);
		mur.setLayoutY(0);
		mur.setHeight(62);
		mur.setWidth(400);
		mur.setFill(Color.RED);
		return mur;
		
	}
	
	
	public  Rectangle murBas1() {
		mur = new Rectangle();
		mur.setLayoutX(50);
		mur.setLayoutY(450);
		mur.setHeight(150);
		mur.setWidth(370);
		mur.setFill(Color.RED);
		return mur;
		
	}
	public  Rectangle murBas2() {
		mur = new Rectangle();
		mur.setLayoutX(545);
		mur.setLayoutY(450);
		mur.setHeight(150);
		mur.setWidth(400);
		mur.setFill(Color.RED);
		return mur;
		
	}
	
	public  Rectangle murDroit1() {
		mur = new Rectangle();
		mur.setLayoutX(815);
		mur.setLayoutY(32);
		mur.setHeight(175);
		mur.setWidth(150);
		mur.setFill(Color.RED);
		return mur;
		
	}
	
	public  Rectangle murDroit2() {
		mur = new Rectangle();
		mur.setLayoutX(815);
		mur.setLayoutY(325);
		mur.setHeight(200);
		mur.setWidth(150);
		mur.setFill(Color.RED);
		return mur;
		
	}
	
	public  Rectangle murGauche1() {
		mur = new Rectangle();
		mur.setLayoutX(0);
		mur.setLayoutY(32);
		mur.setHeight(175);
		mur.setWidth(132);
		mur.setFill(Color.RED);
		
		return mur;
		
	}

	
	public  Rectangle murGauche2() {
		mur = new Rectangle();
		mur.setLayoutX(0);
		mur.setLayoutY(325);
		mur.setHeight(200);
		mur.setWidth(132);
		mur.setFill(Color.RED);
		
		return mur;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public java.awt.Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}