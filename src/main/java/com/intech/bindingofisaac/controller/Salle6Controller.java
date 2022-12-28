package com.intech.bindingofisaac.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;


public class Salle6Controller extends JeuController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.setSalle(6);

        rocher3 = solid.rocher(375, 330);
        rocher1 = solid.rocher(375, 380);
        rocher2 = solid.rocher(530, 330);
        rocher4 = solid.rocher(530, 380);
        trap = solid.trap(443, 350);
        getScene().getChildren().addAll(List.of(trap, rocher1, rocher2, rocher3, rocher4));

        imageIsaac = getImageIsaac();



        imageIsaac.setImage(getImage());
        view = new Rectangle2D(getOffsetX(), getOffsetY(), getWidth(), getHeight());
        imageIsaac.setViewport(view);
        imageIsaac.setPickOnBounds(true);
        imageIsaac.toFront();

      
        super.initialize(location, resources);
    }


}