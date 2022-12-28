package com.intech.bindingofisaac.controller;

import personnages.Ennemis;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class BalleController {
	private int MAX_X = 1200;
	private int MAX_Y = 1200;
	public ArrayList<BalleView> balles = new ArrayList<>();

	public void tir(AnchorPane scene, TirDirection direction, ImageView isaacImageView)
	{
		BalleView balleView = new BalleView(true, 0, 0, 25, 25);
		this.attachToPlayer(balleView, isaacImageView);
		balles.add(balleView);
		scene.getChildren().add(balleView.getImageView());
		this.shoot(balleView, direction);
	}

	public void collision(ArrayList<Ennemis> listEnnemis,
		AnchorPane scene, FreeItemController freeItemController)
	{
		for (int i = 0; i < listEnnemis.size(); i++) {
			Ennemis ennemy = listEnnemis.get(i);
			if (ennemy != null && this.collision(ennemy.getEnnemiView(), scene) > 0)
			{
				ennemy.setLife(0);
				// drop item
				ennemy.dropItem(freeItemController);
				listEnnemis.set(i, null);
			}
		}
		while (listEnnemis.remove(null)) {
		}
		this.removeBalleOutOfScene(scene);
	}

	private int collision(ImageView objet, AnchorPane scene) {
		int nbCollision = 0;
		for (int i = 0; i < this.balles.size(); i++) {
			BalleView balle = this.balles.get(i);
			if (balle != null) {
				if (balle.hit(objet, true)) {
					nbCollision += 1;
					// remove balle from scene
					scene.getChildren().remove(balle.getImageView());
					this.balles.set(i, null);
					balle = null;
				}
			}
		}
		// remove hitted balle
		while (this.balles.remove(null)) {
		}
		return nbCollision;
	}

	// supprimer les balles qui sortent de la scene
	private void removeBalleOutOfScene(AnchorPane scene)
	{
		for (int i = 0; i < this.balles.size(); i++) {
			BalleView balle = this.balles.get(i);
			if (balle != null && (Math.abs(balle.getImageView().getTranslateX()) >= MAX_X ||
				Math.abs(balle.getImageView().getTranslateY()) >= MAX_Y)) {
				// remove balle from scene
				scene.getChildren().remove(balle.getImageView());
				this.balles.set(i, null);
				balle = null;
			}
		}
		// remove hitted balle
		while (this.balles.remove(null)) {
		}
	}

	//Permet � la balle de toujours appara�tre � c�t� du player
	public void attachToPlayer(BalleView balleView, ImageView player) {
		balleView.getImageView().setLayoutX(player.getLayoutX());
		balleView.getImageView().setLayoutY(player.getLayoutY());
	}

	private void shoot(BalleView balleView, TirDirection direction) {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(balleView.getImageView());
		translate.setDuration(Duration.millis(2000));
		switch (direction)
		{
			case UP:
				translate.setByY(-MAX_Y);
				break;
			case DOWN:
				translate.setByY(MAX_Y);
				break;
			case RIGHT:
				translate.setByX(MAX_X);
				break;
			case LEFT:
				translate.setByX(-MAX_X);
				break;
			default:
				break;
		}
		translate.play();
	}
}

enum TirDirection {
	RIGHT,
	LEFT,
	UP,
	DOWN
}