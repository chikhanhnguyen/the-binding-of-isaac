package com.intech.bindingofisaac.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class BombeController {
	public ArrayList<BombeView> bombes = new ArrayList<>();

	public BombeController() {
	}

	public void setBombe(JeuController jeuController) {
		ItemController itemController = jeuController.itemController;
		// update nb bombe
		Integer nbBombe = itemController.getNbBomb();
		if (nbBombe > 0) {
			BombeView bombe = new BombeView();
			bombe.setPosition(jeuController.getImageIsaac().getLayoutX() + 55,
				jeuController.getImageIsaac().getLayoutY() + 31);
			bombes.add(bombe);
			itemController.addOrRemoveBomb(-1);
		}
	}

	public void showBombes(long now, JeuController jeuController) {
		GraphicsContext gc = jeuController.getGc();

		for (int i = 0; i < this.bombes.size(); i++) {
			BombeView bombe = this.bombes.get(i);
			if (bombe != null && bombe.verifierEtat(now)) {
				bombe = null;
				this.bombes.set(i, null);
			}
			if (bombe != null) {
				bombe.render(gc);
			}
		}

		// remove explosed bomb
		while (this.bombes.remove(null)) {
		}
	}

	public int collision(ImageView objet) {
		int nbCollision = 0;
		for (int i = 0; i < this.bombes.size(); i++) {
			BombeView bombe = this.bombes.get(i);
			if (bombe != null && bombe.hit(objet)) {
				nbCollision += 1;
			}
		}
		return nbCollision;
	}
}
