package com.intech.bindingofisaac.controller;

import javafx.scene.Node;

public class GraphicObject {
	protected Node corps;// pour me permettre de matérialiser des nodes (sachant que tout élément javafx
							// est une node)
	protected boolean collision = false;

	public Node getCorps() {
		return corps;
	}

	public void setCorps(Node corps) {
		this.corps = corps;
		this.collision = false;
	}

}