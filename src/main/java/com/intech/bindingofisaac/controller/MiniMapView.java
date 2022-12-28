package com.intech.bindingofisaac.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;

import java.util.ArrayList;

// View for mini map
public class MiniMapView extends CommunObjet {
	private Pair<Integer, Integer> positionMiniMap;

	public MiniMapView(String salleImageFileName, Pair<Integer, Integer> positionSalleInMiniMap) {
		int width = 20;
		int height = 20;
		this.setImage(salleImageFileName, 0, width, height);
		double positionX = 840 + positionSalleInMiniMap.getKey() * width;
		double positionY = 5 + positionSalleInMiniMap.getValue() * height;
		this.setPosition(positionX, positionY);
	}
}
