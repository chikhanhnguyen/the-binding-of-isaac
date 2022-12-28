package com.intech.bindingofisaac.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import personnages.Boss;


public class Salle8Controller extends JeuController {
	Boss b = new Boss();
	private Rectangle2D viewBoss;
	private Animation animation;
	
	@FXML
	private ImageView monster_sprite = new ImageView(b.getImage());

	public void toAnimateBoss(int x, int y, int w, int h) {

		
		monster_sprite.setViewport(new Rectangle2D(x, y, w, h));

		animation = new SpriteAnimation(monster_sprite, Duration.millis(500), b.getCount(), b.getColumns(), x, y, w,
				h);
		// animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		this.getScene().getChildren().add(monster_sprite);
	
}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.setSalle(8);

		getScene().getChildren().addAll(List.of());

		imageIsaac = getImageIsaac();
		monster_sprite = new ImageView(b.getImage());
		monster_sprite.setImage(b.getImage());
		viewBoss = new Rectangle2D(b.getCount(),b.getColumns(), b.getWidth(), b.getHeight());
		monster_sprite.setViewport(viewBoss);
		monster_sprite.setPickOnBounds(true);
		monster_sprite.toFront();
		monster_sprite.setFitHeight(150);
		monster_sprite.setFitWidth(100);
		monster_sprite.setLayoutX(285);
		monster_sprite.setLayoutY(150);
		this.getScene().getChildren().add(b.getBalleBoss());
		b.attachToBoss(monster_sprite);
		b.shootLeft();
		b.shootRight();
		b.shootDown();
		b.shootUp();
		
		//			imageIsaac.setLayoutX(770.0);
		//			imageIsaac.setLayoutY(240.0);

		imageIsaac.setImage(getImage());
		view = new Rectangle2D(getOffsetX(), getOffsetY(), getWidth(), getHeight());
		imageIsaac.setViewport(view);
		imageIsaac.setPickOnBounds(true);
		imageIsaac.toFront();

		super.initialize(location, resources);
		this.toAnimateBoss(0, 0, 32, 64);
	}

	
}