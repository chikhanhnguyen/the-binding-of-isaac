package com.intech.bindingofisaac.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class JeuControllerTest extends ApplicationTest {
	
    private AnchorPane root;
    private JeuController jeuController;
    

	@BeforeEach
    public void init() throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("jeu.fxml"));
			this.root = (AnchorPane) loader.load();
			this.jeuController = (Salle1Controller) loader.getController();
    }
	
	@Test
    public void testSwitchSalle() throws IOException {
        int salle = 1;
	    assertEquals(salle, this.jeuController.getSalle());
//	    this.jeuController.switchSalle(salle, "droite");
//	    assertEquals(2, this.jeuController.getSalle());
//	        assertEquals(salleDroite, this.jeuController.getSalle());
    }

}
