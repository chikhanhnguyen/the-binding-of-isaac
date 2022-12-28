package bindingofisaac;

import com.intech.bindingofisaac.controller.JeuController;
import com.intech.bindingofisaac.controller.MenuController;
import com.intech.bindingofisaac.controller.Salle8Controller;
import com.intech.bindingofisaac.controller.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/main_font.ttf"), 13);
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/logo.png")));
		stage.setTitle("The Binding of Isaac");
		//
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("menu.fxml"));
		AnchorPane root= (AnchorPane) loader.load();
		MenuController menuController = (MenuController) loader.getController();
		//
		Scene scene = new Scene(root);
		scene.getStylesheets().addAll(getClass().getClassLoader().getResource("style.css").toExternalForm());
		SceneController sceneController = new SceneController(scene);
		menuController.setSceneController(sceneController);
		//
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		stage.requestFocus();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}