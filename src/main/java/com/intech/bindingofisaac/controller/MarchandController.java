package com.intech.bindingofisaac.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class MarchandController {
	public MarchandView marchandView;
	public ArrayList<MarchandItemView> items = new ArrayList<>();

	public MarchandController(MarchandType marchandType) {
		this.marchandView = new MarchandView(marchandType, 455, 380);
		MarchandItemView cleItem = new MarchandItemView(MarchandItemType.CLE, 355, 280);
		MarchandItemView bombeItem = new MarchandItemView(MarchandItemType.BOMBE, 455, 280);
		MarchandItemView coeurItem = new MarchandItemView(MarchandItemType.COEUR, 555, 280);
		items.add(cleItem);
		items.add(bombeItem);
		items.add(coeurItem);
	}

	public void showItems(long now, JeuController jeuController) {
		GraphicsContext gc = jeuController.getGc();
		ImageView issacImage = jeuController.getImageIsaac();

		for (int i = 0; i < this.items.size(); i++) {
			MarchandItemView itemView = this.items.get(i);
			if (itemView != null && hit(itemView, issacImage, jeuController)) {
				itemView = null;
				this.items.set(i, null);
			}
			if (itemView != null) {
				itemView.render(gc);
			}
		}

		// remove null items
		while (this.items.remove(null)) {
		}

		// si tous les items ont été pris => marchand mort
		if (this.items.isEmpty())
		{
			this.marchandView.changeLife(true);
		}

		this.marchandView.render(gc);
	}

	private boolean hit(MarchandItemView itemView, ImageView issacImage, JeuController jeuController) {
		ItemController itemController = jeuController.itemController;
		boolean hitted = false;
		if (itemView != null && itemView.hit(issacImage)) {
			if (itemController.getNbCoin() >= itemView.getValeurCoin())
			{
				// add to item
				switch (itemView.getItemType())
				{
					case CLE:
						hitted = true;
						itemController.addOrRemoveCle(1);
						break;
					case BOMBE:
						hitted = true;
						itemController.addOrRemoveBomb(1);
						break;
					case COEUR:
					default:
						int life = jeuController.getLife();
						if (life < jeuController.maxLife)
						{
							hitted = true;
							jeuController.setLife(life + 2);
						}
						break;
				}
				// soustraction de coin d'Issac
				if (hitted)
				{
					itemController.addOrRemoveCoin(-itemView.getValeurCoin());
				}
			}
		}
		return hitted;
	}
}
