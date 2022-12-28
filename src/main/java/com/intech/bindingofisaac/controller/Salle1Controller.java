package com.intech.bindingofisaac.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;

public class Salle1Controller extends JeuController implements Initializable{
// 
//	@FXML
//	private EnnemisController ennemiesController;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.setSalle(1);
		rocher1 = solid.rocher(600, 170);
		nodoorgauche = solid.affmurGauche(52, 200);

		getScene().getChildren().addAll(List.of(rocher1, nodoorgauche));
		imageIsaac = getImageIsaac();
		
		imageIsaac.setLayoutX(273.0);
		imageIsaac.setLayoutY(198.0);
		imageIsaac.setImage(getImage());
		
		view = new Rectangle2D(getOffsetX(), getOffsetY(), getWidth(), getHeight());
		imageIsaac.setViewport(view);
		imageIsaac.setPickOnBounds(true);
		imageIsaac.toFront();
//		ennemiesController.setIsaac(this.imageIsaac);

		super.initialize(location, resources);
	}











}
