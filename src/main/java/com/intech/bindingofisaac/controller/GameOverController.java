package com.intech.bindingofisaac.controller;

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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameOverController extends JeuController implements Initializable {
	
	protected Image imgReplay = new Image("RestartButton.png");	
	protected Image imgExit = new Image("ExitButton.png");
	protected Image imgBackground = new Image("images/menu_background.png");
	protected Image imgScore = new Image("Full_Death_Note.png");
	
	@FXML
	protected ImageView imageScore;
	@FXML
	protected ImageView imageBackground;
	@FXML
	protected ImageView imageReplay = new ImageView(imgReplay);
	@FXML
	protected ImageView imageExit = new ImageView(imgExit);
	@FXML
	private Button buttonReplay;
	@FXML
	private Button buttonExit;

	private Stage stage;
	private Scene scene;

	

	public void replayGame(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("REPLAY");
		alert.setHeaderText("Vous �tes sur le point de relancer une partie !");
		alert.setContentText("�tes vous s�r de vouloir rejouer ?");
		
		if (alert.showAndWait().get() == ButtonType.OK) {
			this.sceneController.clear();
			this.sceneController.activate(1);
		}
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

	public void initialize(URL location, ResourceBundle resources) {
		imageBackground.setImage(imgBackground);
		imageScore.setImage(imgScore);
		imageScore.setFitHeight(521.0);
		imageScore.setFitWidth(475);
	}

}