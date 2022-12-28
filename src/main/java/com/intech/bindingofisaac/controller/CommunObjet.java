package com.intech.bindingofisaac.controller;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

public class CommunObjet {
	private Image image;
	private ImageView imageView;
	private double positionX;
	private double positionY;
	private double velocityX;
	private double velocityY;
	private double width;
	private double height;

	public CommunObjet() {
		this.positionX = 0;
		this.positionY = 0;
		this.velocityX = 0;
		this.velocityY = 0;
	}

	private void setImage(Image image) {
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.imageView = new ImageView(this.image);
	}

	public void setImage(String filename, double ratio, double width, double height) {
		Image i = new Image(getClass().getResource(filename).toExternalForm());
		double imageHeight;
		double imageWidth;
		if (ratio > 0) {
			imageHeight = i.getHeight() * ratio;
			imageWidth = i.getWidth() * ratio;
		} else {
			imageHeight = height;
			imageWidth = width;
		}
		Image toReturn = new Image(filename, imageWidth, imageHeight, true, false);
		setImage(toReturn);
		//
		this.imageView = new ImageView(this.image);
	}

	public Image getImage() {
		return image;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setPosition(double x, double y) {
		this.positionX = x;
		this.positionY = y;
		this.imageView.setTranslateX(this.positionX);
		this.imageView.setTranslateY(this.positionY);
	}

	public void setVelocity(double x, double y) {
		this.velocityX = x;
		this.velocityY = y;
	}

	public void addVelocity(double x, double y) {
		velocityX += x;
	}

	public void update(double time) {
		positionX += velocityX * time;
		positionY += velocityY * time;
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
	}

	public void render(Pane root) {
		this.imageView.setTranslateX(this.positionX);
		this.imageView.setTranslateY(this.positionY);
		root.getChildren().add(imageView);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public Bounds getBoundsInParent() {
		Rectangle rectangle = new Rectangle();
		rectangle.setLayoutX(positionX);
		rectangle.setLayoutY(positionY);
		rectangle.setHeight(width);
		rectangle.setWidth(height);
		return rectangle.getBoundsInParent();
	}

	public boolean intersects(CommunObjet s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
}
