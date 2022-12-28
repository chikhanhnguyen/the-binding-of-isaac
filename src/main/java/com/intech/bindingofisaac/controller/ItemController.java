package com.intech.bindingofisaac.controller;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ItemController {

	// Reference to the view
	private Text coinText;
	private Text bombeText;
	private Text cleText;

	// Reference to the model
	private Integer nbCoin;
	private Integer nbBomb;
	private Integer nbCle;

	public Integer getNbBomb() {
		return nbBomb;
	}
	public Integer getNbCoin() {
		return nbCoin;
	}
	public Integer getNbCle() {
		return nbCle;
	}

	public ItemController() {
		this.nbCoin = 5;
		this.nbBomb = 5;
		this.nbCle = 5;
		this.coinText = new Text(50, 84, "0" + this.nbCoin);
		this.bombeText = new Text(50, 105, "0" + this.nbBomb);
		this.cleText = new Text(50, 125, "0" + this.nbCle);
	}

	public void clone(ItemController itemController)
	{
		this.nbCoin = itemController.nbCoin;
		this.nbBomb = itemController.nbBomb;
		this.nbCle = itemController.nbCle;
		this.coinText.setText("0" + this.nbCoin);
		this.bombeText.setText("0" + this.nbBomb);
		this.cleText.setText("0" + this.nbCle);
	}

	// si la quantité est négative => remove
	// si la quantité est positive => add
	public void addOrRemoveBomb(int quantite) {
		this.nbBomb += quantite;
		this.nbBomb = Math.max(0, this.nbBomb);
		// then update view
		if (this.nbBomb >= 10)
		{
			this.bombeText.setText(this.nbBomb.toString());
		} else
		{
			this.bombeText.setText("0" + this.nbBomb);
		}
	}

	// si la quantité est négative => remove
	// si la quantité est positive => add
	public void addOrRemoveCoin(int quantite) {
		this.nbCoin += quantite;
		this.nbCoin = Math.max(0, this.nbCoin);
		// then update view
		if (this.nbCoin >= 10)
		{
			this.coinText.setText(this.nbCoin.toString());
		} else
		{
			this.coinText.setText("0" + this.nbCoin);
		}
	}

	// si la quantité est négative => remove
	// si la quantité est positive => add
	public void addOrRemoveCle(int quantite) {
		this.nbCle += quantite;
		this.nbCle = Math.max(0, this.nbCle);
		// then update view
		if (this.nbCle >= 10)
		{
			this.cleText.setText(this.nbCle.toString());
		} else
		{
			this.cleText.setText("0" + this.nbCle);
		}
	}
 
	public void addItems(AnchorPane root) {
		//////////////////////////////
		// Ajouter les items
		// ajouter coin
		ImageView coinImage = new ImageView("/images/coin.png");
		coinImage.setLayoutX(32);
		coinImage.setLayoutY(72);
		coinImage.setFitHeight(15);
		coinImage.setFitWidth(15);
		// ajouter bombe
		ImageView bombeImage = new ImageView("/images/bombe.png");
		bombeImage.setLayoutX(30);
		bombeImage.setLayoutY(90);
		bombeImage.setFitHeight(18);
		bombeImage.setFitWidth(18);
		// ajouter cle
		ImageView cleImage = new ImageView("/images/cle.png");
		cleImage.setLayoutX(35);
		cleImage.setLayoutY(113);
		cleImage.setFitHeight(13);
		cleImage.setFitWidth(13);
		//////////////////////////////
		// Ajouter nombre items
		// nb Coin
		this.coinText.setFill(Color.WHITE);
		this.coinText.setFont(Font.font("Gamegirl Classic", 13));
		// nb bombe
		this.bombeText.setFill(Color.WHITE);
		this.bombeText.setFont(Font.font("Gamegirl Classic", 13));
		// nb Cle
		this.cleText.setFill(Color.WHITE);
		this.cleText.setFont(Font.font("Gamegirl Classic", 13));
		//
		ArrayList<ImageView> itemImages = new ArrayList<ImageView>();
		itemImages.add(coinImage);
		itemImages.add(bombeImage);
		itemImages.add(cleImage);
		ArrayList<Text> itemText = new ArrayList<Text>();
		itemText.add(this.coinText);
		itemText.add(this.bombeText);
		itemText.add(this.cleText);
		//
		root.getChildren().addAll(itemImages);
		root.getChildren().addAll(itemText);
	}
}