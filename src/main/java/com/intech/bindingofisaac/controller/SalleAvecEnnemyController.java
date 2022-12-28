package com.intech.bindingofisaac.controller;
import personnages.DirectionEnum;
import personnages.Ennemis;

import javafx.scene.canvas.GraphicsContext;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;


public class SalleAvecEnnemyController extends JeuController {

	ArrayList<Ennemis> listE = new ArrayList<Ennemis>();
	@FXML
	private EnnemisController ennemiesController;
	
	@FXML
	protected AnchorPane scene;
	Timer timer = new Timer();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ennemiesController.setIsaac(this.imageIsaac);
		super.initialize(location, resources);
	}

	// pour mettre à jour la santé d'Issac après la collision avec la bombe et la balle
	protected void updateCoeurs(GraphicsContext gc) {
		// check collision avec les balles
		int life = this.getLife();
		if (life > 0) {
			for (Ennemis ennemy : listE) {
				if (ennemy != null)
				{
					life -= ennemy.balleEnnemyController.collision(ennemy.getIssac(), ennemy.getScene());
				}
			}
			this.setLife(life);
		}
		super.updateCoeurs(gc);
	}

	protected void showItems(long now)
	{
		for (int i = 0; i < listE.size(); i++) {
			Ennemis e = listE.get(i);
			if (e != null && e.getLife() > 0 && !e.outOfScene())
			{
				e.setIssac(imageIsaac);
				//
				if (this.bombeController.collision(e.getEnnemiView()) > 0)
				{
					e.setLife(0);
					e.dropItem(this.freeItemController);
					listE.set(i, null);
				} else {
					// move
					if(collision(e.getRadaEnnemiView(),buildListMurs("haut", salle))) {
						e.randomChangerDirection(true, DirectionEnum.UP);
					}
					if(collision(e.getRadaEnnemiView(),buildListMurs("droite", salle))) {
						e.randomChangerDirection(true, DirectionEnum.RIGHT);
					}
					if(collision(e.getRadaEnnemiView(),buildListMurs("gauche", salle))) {
						e.randomChangerDirection(true, DirectionEnum.LEFT);
					}
					if(collision(e.getRadaEnnemiView(),buildListMurs("bas", salle))) {
						e.randomChangerDirection(true, DirectionEnum.DOWN);
					}
					e.move();
					e.render(this.getGc());
					// tir
					e.tir(imageIsaac.getLayoutX(), imageIsaac.getLayoutY());
				}
			} else {
				listE.set(i, null);
			}
		}
		// remove morts ennemies
		while (listE.remove(null)) {
		}
		super.showItems(now);
	}
	
	// update les balles d'Isaac
	protected void updateBalleIsaac()
	{
		this.balleController.collision(listE, this.getScene(), this.freeItemController);
	}
}