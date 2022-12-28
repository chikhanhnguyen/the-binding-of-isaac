package com.intech.bindingofisaac.controller;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

// View pour le marchand
public class MarchandView extends CommunObjet {
	private MarchandType itemType;
	private boolean mort;

	public MarchandView(MarchandType itemType, double positionX, double positionY) {
		this.mort = false;
		this.itemType = itemType;
		switch (itemType)
		{
			case NORMAL:
				this.setImage("/images/Shopkeeper.png", 0, 60, 60);
				break;
			case SPECIAL:
			default:
				this.setImage("/images/Special_Shopkeeper.png", 0, 60, 60);
				break;
		}
		this.setPosition(positionX, positionY);
	}

	public void changeLife(boolean mort)
	{
		if (mort != this.mort)
		{
			this.mort = mort;
			switch (this.itemType)
			{
				case NORMAL:
					if (mort)
					{
						this.setImage("/images/Hanging_Shopkeeper.png", 0, 120, 120);
						this.setPosition(this.getPositionX(), this.getPositionY() - 40);
					} else {
						this.setImage("/images/Shopkeeper.png", 0, 60, 60);
						this.setPosition(this.getPositionX(), this.getPositionY() + 40);
					}
					break;
				case SPECIAL:
				default:
					if (mort)
					{
						this.setImage("/images/Special_Hanging_Shopkeeper.png", 0, 120, 120);
						this.setPosition(this.getPositionX(), this.getPositionY() - 40);
					} else {
						this.setImage("/images/Special_Shopkeeper.png", 0, 60, 60);
						this.setPosition(this.getPositionX(), this.getPositionY() + 40);
					}
					break;
			}
		}
	}

	public MarchandType getItemType()
	{
		return this.itemType;
	}
}

enum MarchandType {
	NORMAL,
	SPECIAL
}
