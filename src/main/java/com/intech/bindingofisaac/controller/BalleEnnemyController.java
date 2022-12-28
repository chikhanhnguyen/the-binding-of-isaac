package com.intech.bindingofisaac.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.util.Duration;
import personnages.Ennemis;
import java.util.ArrayList;

public class BalleEnnemyController {
	private int MAX_X = 1200;
	private int MAX_Y = 1200;
	// private Ennemis ennemy;
	public ArrayList<BalleView> balles = new ArrayList<>();

	public BalleEnnemyController() {
	}

	public BalleView addNewBalle(Ennemis ennemy) {
		BalleView balleView = new BalleView(false, ennemy.getX(), ennemy.getY(), 25, 25);
		balles.add(balleView);
		balleView.render(ennemy.getScene());
		return balleView;
	}

	public void moveToPlayer(Ennemis ennemy, BalleView balleView, double xIsaac, double yIsaac) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(balleView.getImageView());
		transition.setDuration(Duration.millis(6500));
		transition.setByX((xIsaac - ennemy.getX()) * 10);
		transition.setByY((yIsaac - ennemy.getY()) * 10);

		transition.setAutoReverse(true);
		transition.play();
	}

	public int collision(ImageView objet, AnchorPane scene) {
		int nbCollision = 0;
		for (int i = 0; i < this.balles.size(); i++) {
			BalleView balle = this.balles.get(i);
			if (balle != null) {
				// Mettre à jour la bonne position de la balle.
				// C'est une étape essentielle car la balle (imageView) est gérée par la
				// 'TranslateTransition'
				balle.setPosition(balle.getImageView().getTranslateX(), balle.getImageView().getTranslateY());
				if (balle.hit(objet, false)) {
					nbCollision += 1;
					// remove balle from scene
					scene.getChildren().remove(balle.getImageView());
					balle = null;
					this.balles.set(i, null);
				}
			}
		}
		// remove hitted balle
		while (this.balles.remove(null)) {
		}
		this.removeBalleOutOfScene(scene);
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
}