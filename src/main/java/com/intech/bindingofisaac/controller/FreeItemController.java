package com.intech.bindingofisaac.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class FreeItemController {
	public ArrayList<FreeItemView> items = new ArrayList<>();

	public FreeItemController() {
	}

	public void dropItem(FreeItemType itemType, double positionX, double positionY)
	{
		FreeItemView newItemView = new FreeItemView(itemType, positionX, positionY);
		items.add(newItemView);
	}

	public void showItems(long now, JeuController jeuController) {
		GraphicsContext gc = jeuController.getGc();
		ImageView issacImage = jeuController.getImageIsaac();
		ItemController itemController = jeuController.itemController;

		for (int i = 0; i < this.items.size(); i++) {
			FreeItemView itemView = this.items.get(i);
			if (itemView != null && (!itemView.exists(now) || hit(itemView, issacImage, itemController))) {
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
	}

	private boolean hit(FreeItemView itemView, ImageView issacImage, ItemController itemController) {
		boolean hitted = false;
		if (itemView != null && itemView.hit(issacImage)) {
			hitted = true;
			// add to item
			switch (itemView.getItemType())
			{
				case CLE:
					itemController.addOrRemoveCle(1);
					break;
				case BOMBE:
					itemController.addOrRemoveBomb(1);
					break;
				case COIN:
				default:
					itemController.addOrRemoveCoin(1);
					break;
			}
		}
		return hitted;
	}
}
