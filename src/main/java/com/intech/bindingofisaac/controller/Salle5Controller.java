package com.intech.bindingofisaac.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Rectangle2D;
import personnages.Ennemis;


public class Salle5Controller extends SalleAvecEnnemyController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.setSalle(5);

        affmurhaut = solid.affmurHautrouge(415, 32);
        affportebas = solid.affporteBasrouge(415, 442);
        affportedroite = solid.affporteDroiterouge(807, 200);
        getScene().getChildren().addAll(List.of(affmurhaut, affportebas, affportedroite));

        imageIsaac = getImageIsaac();

        imageIsaac.setImage(getImage());
        view = new Rectangle2D(getOffsetX(), getOffsetY(), getWidth(), getHeight());
        imageIsaac.setViewport(view);
        imageIsaac.setPickOnBounds(true);
        imageIsaac.toFront();

        listE.add(new Ennemis("ennemi1", 90, 90, 200, 200, scene));
		listE.add(new Ennemis("ennemi2",90,90,300,150, scene));
		listE.add(new Ennemis("ennemi3",90,90,500,200, scene));

        super.initialize(location, resources);
    }
}