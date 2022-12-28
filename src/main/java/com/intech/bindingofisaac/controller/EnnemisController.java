package com.intech.bindingofisaac.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import personnages.Ennemis;

public class EnnemisController implements Initializable {

	@FXML
	private AnchorPane scene;
	
	private ImageView isaac;
	
	public void setIsaac(ImageView isaac) {
		this.isaac = isaac;
	}

	ArrayList<Ennemis> listE = new ArrayList<Ennemis>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}