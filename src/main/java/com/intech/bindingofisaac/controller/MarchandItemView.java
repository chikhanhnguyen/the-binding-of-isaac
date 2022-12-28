package com.intech.bindingofisaac.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.util.ArrayList;


// View pour les items de marchand
public class MarchandItemView extends CommunObjet {
	private MarchandItemType itemType;
	private ArrayList<ImageView> hittedImages = new ArrayList<ImageView>();
	private int valeurCoin; // coins

	public MarchandItemView(MarchandItemType itemType, double positionX, double positionY) {
		switch (itemType)
		{
			case CLE:
				this.valeurCoin = 2;
				this.setImage("/images/cle.png", 0, 40, 40);
				break;
			case BOMBE:
				this.valeurCoin = 1;
				this.setImage("/images/bombe_freeitem.png", 0, 45, 45);
				break;
			case COEUR:
			default:
				this.valeurCoin = 3;
				this.setImage("/images/coeurFull.png", 0, 40, 40);
				break;
		}
		this.itemType = itemType;
		this.setPosition(positionX, positionY);
	}

	public MarchandItemType getItemType()
	{
		return this.itemType;
	}
	public int getValeurCoin() { return this.valeurCoin; }

	@Override
	public void render(GraphicsContext gc) {
		CommunObjet coinImageView = new CommunObjet();
		coinImageView.setImage("/images/coin.png", 0, 20, 20);
		coinImageView.setPosition(this.getPositionX() + 15, this.getPositionY() + 60);
		//
		gc.setFont(new Font("Gamegirl Classic",13));
		gc.setFill(Color.WHITE);
		gc.fillText(String.valueOf(this.valeurCoin), this.getPositionX() - 5, this.getPositionY() + 70);
		//
		coinImageView.render(gc);
		super.render(gc);
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

enum MarchandItemType {
	CLE,
	BOMBE,
	COEUR
}
