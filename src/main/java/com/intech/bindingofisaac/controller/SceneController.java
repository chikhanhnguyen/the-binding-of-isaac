package com.intech.bindingofisaac.controller;
import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneController {
	private HashMap<Integer, AnchorPane> screenMaps = new HashMap<>();
	private HashMap<Integer, JeuController> screenControllers = new HashMap<>();
	private Scene main;

	public SceneController(Scene main) {
		this.main = main;
	}

	public void clear()
	{
		screenMaps.clear();
		screenControllers.clear();
	}

	protected JeuController activate(Integer salleNumber) throws IOException {
		if (!screenMaps.containsKey(salleNumber))
		{
			switch (salleNumber)
			{
				case 1:
					FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("jeu.fxml"));
					AnchorPane root1 = (AnchorPane) loader.load();
					JeuController controller1 = (Salle1Controller) loader.getController();
					screenMaps.put(salleNumber, root1);
					controller1.setSceneController(this);
					screenControllers.put(salleNumber, controller1);
					break;
				case 2:
					FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("salle2.fxml"));
					AnchorPane root2 = (AnchorPane) loader2.load();
					JeuController controller2 = (Salle2Controller) loader2.getController();
					screenMaps.put(salleNumber, root2);
					controller2.setSceneController(this);
					screenControllers.put(salleNumber, controller2);
					break;
				case 3:
					FXMLLoader loader3 = new FXMLLoader(getClass().getClassLoader().getResource("salle3.fxml"));
					AnchorPane root3 = (AnchorPane) loader3.load();
					JeuController controller3 = (Salle3Controller) loader3.getController();
					screenMaps.put(salleNumber, root3);
					controller3.setSceneController(this);
					screenControllers.put(salleNumber, controller3);
					break;
				case 4:
					FXMLLoader loader4 = new FXMLLoader(getClass().getClassLoader().getResource("salle4.fxml"));
					AnchorPane root4 = (AnchorPane) loader4.load();
					JeuController controller4 = (Salle4Controller) loader4.getController();
					screenMaps.put(salleNumber, root4);
					controller4.setSceneController(this);
					screenControllers.put(salleNumber, controller4);
					break;
				case 5:
					FXMLLoader loader5 = new FXMLLoader(getClass().getClassLoader().getResource("salle5.fxml"));
					AnchorPane root5 = (AnchorPane) loader5.load();
					JeuController controller5 = (Salle5Controller) loader5.getController();
					screenMaps.put(salleNumber, root5);
					controller5.setSceneController(this);
					screenControllers.put(salleNumber, controller5);
					break;
				case 6:
					FXMLLoader loader6 = new FXMLLoader(getClass().getClassLoader().getResource("salle6.fxml"));
					AnchorPane root6 = (AnchorPane) loader6.load();
					JeuController controller6 = (Salle6Controller) loader6.getController();
					screenMaps.put(salleNumber, root6);
					controller6.setSceneController(this);
					screenControllers.put(salleNumber, controller6);
					break;
				case 7:
					FXMLLoader loader7 = new FXMLLoader(getClass().getClassLoader().getResource("salle7.fxml"));
					AnchorPane root7 = (AnchorPane) loader7.load();
					JeuController controller7 = (Salle7Controller) loader7.getController();
					screenMaps.put(salleNumber, root7);
					controller7.setSceneController(this);
					screenControllers.put(salleNumber, controller7);
					break;
				case 8:
					FXMLLoader loader8 = new FXMLLoader(getClass().getClassLoader().getResource("salle8.fxml"));
					AnchorPane root8 = (AnchorPane) loader8.load();
					JeuController controller8 = (Salle8Controller) loader8.getController();
					screenMaps.put(salleNumber, root8);
					controller8.setSceneController(this);
					screenControllers.put(salleNumber, controller8);
					break;
				case 9: // gameover
					FXMLLoader loader9 = new FXMLLoader(getClass().getClassLoader().getResource("GameOver.fxml"));
					AnchorPane root9 = (AnchorPane) loader9.load();
					JeuController controller9 = (GameOverController) loader9.getController();
					screenMaps.put(salleNumber, root9);
					controller9.setSceneController(this);
					screenControllers.put(salleNumber, controller9);
					break;
				default:
					break;
			}
		}
		// reset current salle
		for (Integer key : this.screenControllers.keySet()) {
			if (this.screenControllers.get(key) != null)
			{
				this.screenControllers.get(key).isCurrentSalle = false;
			}
		}
		//
		JeuController controller = screenControllers.get(salleNumber);
		AnchorPane pane = screenMaps.get(salleNumber);
		if (controller != null)
		{
			controller.isCurrentSalle = true;
			controller.setScene(pane);
		}
		main.setRoot(pane);
		return controller;
	}
}
