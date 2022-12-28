package com.intech.bindingofisaac.controller;

import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class BombeView extends CommunObjet {
	private boolean etat; // true => en état normal, false => en train d'eploser
	private long changerEtatTime;
	private long normalDurationInNanoSeconds = (long) 3000000000.0; // 3s
	private long explosionDurationInNanoSeconds = (long) 1000000000.0; // 1s
	private ArrayList<ImageView> hittedImages = new ArrayList<ImageView>();
	
	public BombeView() {
		this.setImage("/images/bombe.png", 0, 28, 29);
		this.etat = true;
		this.changerEtatTime = System.nanoTime() + this.normalDurationInNanoSeconds;
	}

	public boolean verifierEtat(long now) {
		boolean supprimerBombe = false;
		if (this.etat) {
			if (now >= this.changerEtatTime) {
				this.etat = false;
				this.changerEtatTime = now + this.explosionDurationInNanoSeconds;
				double newPositionX = this.getPositionX() - 31;
				double newPositionY = this.getPositionY() - 41;
				this.setPosition(newPositionX, newPositionY);
				this.setImage("/images/explode.gif", 0, 90, 112);
			}
		} else {
			if (now >= this.changerEtatTime) {
				supprimerBombe = true;
			}
		}

		return supprimerBombe;
	}

	public boolean hit(ImageView objet)
	{
		boolean collision = !hittedImages.contains(objet) && !etat && objet.getBoundsInParent().intersects(this.getBoundsInParent());
		if (collision)
		{
			hittedImages.add(objet);
		}
		return collision;
	}
}
