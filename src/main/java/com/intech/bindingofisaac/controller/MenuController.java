package com.intech.bindingofisaac.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MenuController implements Initializable {

	@FXML
	private Button buttonPlay;
	@FXML
	private Button buttonExit;
	@FXML
	private Media menuMusic = new Media(new File("src/main/resources/music/menu.mp3").toURI().toString());
	@FXML
	private MediaPlayer menuPlayer = new MediaPlayer(menuMusic);
	@FXML
	private Button musicButton1;
	@FXML
	private Button musicButton2;

	private Stage stage;
	private Scene scene;

	private SceneController sceneController;
	public void setSceneController(SceneController sceneController)
	{
		this.sceneController = sceneController;
	}

	public void switchToScreenGame(ActionEvent event) throws IOException {
		sceneController.activate(1);
	}
	
	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Quitter");
		alert.setHeaderText("Vous �tes sur le point de vous d�connecter !");
		alert.setContentText("�tes vous s�r de vouloir quitter le jeu ?");

		if (alert.showAndWait().get() == ButtonType.OK) {

			stage = (Stage) buttonExit.getScene().getWindow();
			System.out.println("Vous vous �tes d�connecter avec succ�s !");
			stage.close();
		}

	}
	
	public void handleMusic(ActionEvent event) {
		// Quand le haut parleur avec ondes est cliqu� :
		if (event.getSource() == musicButton1) {
			// Met la musique en pause
			menuPlayer.pause();
			// Image haut parleur AVEC ondes invisible et d�sactiv�
			musicButton1.setDisable(true);
			musicButton1.setVisible(false);
			// Image haut parleur SANS ondes visible et activ�
			musicButton2.setDisable(false);
			musicButton2.setVisible(true);
			// Quand le haut parleur sans ondes est cliqu� :
		} else if (event.getSource() == musicButton2) {
			// Met la musique en marche
			menuPlayer.play();
			// Image haut parleur AVEC ondes visible et activ�
			musicButton1.setDisable(false);
			musicButton1.setVisible(true);

		}
	}

	public void initialize(URL location, ResourceBundle resources) {
	}

}