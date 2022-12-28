package com.intech.bindingofisaac.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit5.ApplicationTest;

public class BombeControllerTest extends ApplicationTest {
    private Pane root;
    private JeuController jeuController;
    private ItemController itemController;
    private BombeController bombeController;

    @BeforeEach
    public void init() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("jeu.fxml"));
		this.root = (AnchorPane) loader.load();
		this.jeuController = (Salle1Controller) loader.getController();
        this.itemController = this.jeuController.itemController;
        this.bombeController = this.jeuController.bombeController;
    }

    @Test
    public void testSetBombe() {
        int nbBombeInit = 5;
        assertEquals(nbBombeInit, this.itemController.getNbBomb());
        this.bombeController.setBombe(this.jeuController);
        assertEquals(nbBombeInit - 1, this.itemController.getNbBomb());
        assertEquals(1, this.bombeController.bombes.size());
    }

    @Test
    public void testCollision() throws Exception {
        this.bombeController.setBombe(this.jeuController);
        // clone Isaac
        ImageView cloneIsaac = new ImageView(this.jeuController.getImage());
        cloneIsaac.setLayoutX(jeuController.getImageIsaac().getLayoutX());
        cloneIsaac.setLayoutY(jeuController.getImageIsaac().getLayoutY());
        //
        assertEquals(0, this.bombeController.collision(this.jeuController.getImageIsaac()));
        // wait 3.5s (le temps pour que la bombe explose)
        Thread.sleep(3500);
        assertEquals(1, this.bombeController.collision(cloneIsaac));
    }

    @Test
    public void testCollisionLife() throws Exception {
        this.bombeController.setBombe(this.jeuController);
        assertEquals(0, this.bombeController.collision(this.jeuController.getImageIsaac()));
        assertEquals(3, this.jeuController.getLife());
        // wait 3.5s (le temps pour que la bombe explose)
        Thread.sleep(3500);
        assertEquals(2, this.jeuController.getLife());
    }
}