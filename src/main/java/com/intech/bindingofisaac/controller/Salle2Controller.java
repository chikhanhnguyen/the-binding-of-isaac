package com.intech.bindingofisaac.controller;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import personnages.Ennemis;


public class Salle2Controller extends SalleAvecEnnemyController implements Initializable{
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.setSalle(2);
		rocher1 = solid.rocher(600, 250);
		rocher2 = solid.rocher(400, 230);
		nodoorbas = solid.affmurBas(410, 450);
		nodoordroite = solid.affmurDroite(815, 200);
		getScene().getChildren().addAll(List.of(rocher1, rocher2, nodoorbas, nodoordroite));
		imageIsaac = getImageIsaac();
	

		imageIsaac.setImage(getImage());
		view = new Rectangle2D(getOffsetX(), getOffsetY(), getWidth(), getHeight());
		imageIsaac.setViewport(view);
		imageIsaac.setPickOnBounds(true);
		imageIsaac.toFront();

		listE.add(new Ennemis("ennemi1", 90, 90, 200, 200, scene));
		listE.add(new Ennemis("ennemi2",90,90,300,150, scene));
		listE.add(new Ennemis("ennemi3",90,90,500,200, scene));

		super.initialize(location, resources);
	}
}