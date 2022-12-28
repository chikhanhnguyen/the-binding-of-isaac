package com.intech.bindingofisaac.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;

public class MiniMapController {
	private CommunObjet miniIsaac;
	private CommunObjet miniMapBackground;
	private ArrayList<MiniMapView> mapViews = new ArrayList<>();

	public MiniMapController() {
		this.miniIsaac = new CommunObjet();
		this.miniIsaac.setImage("/images/salle_with_isaac.png", 0, 20,20);
		this.miniMapBackground = new CommunObjet();
		this.miniMapBackground.setImage("/images/transparent_minimap_background.jpeg", 0, 140,140);
		this.miniMapBackground.getImageView().setOpacity(0.6);
		this.miniMapBackground.setPosition(835, 5);
		//
		this.addMiniMap("/images/salle_empty.png", new Pair<Integer, Integer>(2, 1)); // salle 1
		this.addMiniMap("/images/salle_empty.png", new Pair<Integer, Integer>(3, 1)); // salle 2
		this.addMiniMap("/images/salle_empty.png", new Pair<Integer, Integer>(2, 2)); // salle 3
		this.addMiniMap("/images/salle_shop.png", new Pair<Integer, Integer>(2, 3)); // salle 4
		this.addMiniMap("/images/salle_empty.png", new Pair<Integer, Integer>(1, 3)); // salle 5
		this.addMiniMap("/images/salle_empty.png", new Pair<Integer, Integer>(1, 4)); // salle 6
		this.addMiniMap("/images/salle_empty.png", new Pair<Integer, Integer>(1, 5)); // salle 7
		this.addMiniMap("/images/salle_boss.png", new Pair<Integer, Integer>(2, 5)); // salle 8
	}

	public void addBackground(JeuController jeuController)
	{
		jeuController.getScene().getChildren().add(this.miniMapBackground.getImageView());
	}

	public void addMiniMap(String salleImageFileName, Pair<Integer, Integer> positionSalleInMiniMap) {
		MiniMapView newMap = new MiniMapView(salleImageFileName, positionSalleInMiniMap);
		mapViews.add(newMap);
	}

	public void showMiniMap(JeuController jeuController) {
		GraphicsContext gc = jeuController.getGc();
		// render background
		for (int i = 0; i < this.mapViews.size(); i++) {
			MiniMapView mapView = this.mapViews.get(i);
			if (mapView != null) {
				mapView.render(gc);
			}
			if (jeuController.getSalle() == i + 1)
			{
				// afficher Isaac sur le mini map
				this.miniIsaac.setPosition(mapView.getPositionX(), mapView.getPositionY());
				this.miniIsaac.render(gc);
			}
		}
	}
}
