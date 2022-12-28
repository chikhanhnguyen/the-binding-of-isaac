package com.intech.bindingofisaac.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Rectangle2D;

// Salle de marchand
public class Salle4Controller extends JeuController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.setSalle(4);

        affmurhaut = solid.affmurHautrouge(415, 32);
        portail = solid.portailhaut(435, 40);
        affportegauche = solid.affporteGaucherouge(75, 212);
        getScene().getChildren().addAll(List.of(affmurhaut , portail, affportegauche));

        imageIsaac = getImageIsaac();

        imageIsaac.setImage(getImage());
        view = new Rectangle2D(getOffsetX(), getOffsetY(), getWidth(), getHeight());
        imageIsaac.setViewport(view);
        imageIsaac.setPickOnBounds(true);
        imageIsaac.toFront();

        super.initialize(location, resources);
        this.showMarchand = true;
        this.marchandController = new MarchandController(MarchandType.NORMAL);
    }
}