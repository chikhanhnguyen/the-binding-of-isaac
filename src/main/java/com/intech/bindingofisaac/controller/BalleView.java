package com.intech.bindingofisaac.controller;

import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BalleView extends CommunObjet {
	private ArrayList<ImageView> hittedImages = new ArrayList<ImageView>();
	private boolean balleDeIsaac;

	public BalleView(boolean balleDeIsaac, double positionX, double positionY, double width, double height) {
		this.balleDeIsaac = balleDeIsaac;
		if (balleDeIsaac)
		{
			this.setImage("/images/bullet.png", 0, width, height);
		} else {
			this.setImage("/bulletEn.png", 0, width, height);
		}
		this.setPosition(positionX, positionY);
	}

	public boolean hit(ImageView objet, boolean isTranslate)
	{
		boolean collision = !hittedImages.contains(objet);
		if (isTranslate) {
			// Mettre à jour la bonne position de la balle et de l'objet.
			// C'est une étape essentielle car la balle et l'objet (imageView) est gérée par la
			// 'TranslateTransition'
			Rectangle rectangleBalle = new Rectangle();
			rectangleBalle.setLayoutX(this.getImageView().getLayoutX() + this.getImageView().getTranslateX());
			rectangleBalle.setLayoutY(this.getImageView().getLayoutY() + this.getImageView().getTranslateY());
			rectangleBalle.setHeight(this.getHeight());
			rectangleBalle.setWidth(this.getWidth());
			collision &= objet.getBoundsInParent().intersects(rectangleBalle.getBoundsInParent());
		} else {
			collision &= objet.getBoundsInParent().intersects(this.getBoundsInParent());
		}

		if (collision)
		{
			hittedImages.add(objet);
		}
		return collision;
	}
}
