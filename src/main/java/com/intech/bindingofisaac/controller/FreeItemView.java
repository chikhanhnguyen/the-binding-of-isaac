package com.intech.bindingofisaac.controller;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

// View pour les coins/clés
public class FreeItemView extends CommunObjet {
	private FreeItemType itemType;
	private long endTime;
	// item va disparaitre après 1 minute
	private long existDurationInSeconds = 60;
	private ArrayList<ImageView> hittedImages = new ArrayList<ImageView>();

	public FreeItemView(FreeItemType itemType, double positionX, double positionY) {
		switch (itemType)
		{
			case CLE:
				this.setImage("/images/cle.png", 0, 30, 30);
				break;
			case BOMBE:
				this.setImage("/images/bombe_freeitem.png", 0, 35, 35);
				break;
			case COIN:
			default:
				this.setImage("/images/coin.png", 0, 30, 30);
				break;
		}
		this.itemType = itemType;
		this.setPosition(positionX, positionY);
		this.endTime = System.nanoTime() + this.existDurationInSeconds * 1000000000;
	}

	public FreeItemType getItemType()
	{
		return this.itemType;
	}

	// si pas exists, on doit supprimer cet item
	public boolean exists(long now) {
		return now < this.endTime;
	}

	public boolean hit(ImageView objet)
	{
		boolean collision = !hittedImages.contains(objet) && objet.getBoundsInParent().intersects(this.getBoundsInParent());
		if (collision)
		{
			hittedImages.add(objet);
		}
		return collision;
	}
}
