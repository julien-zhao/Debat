
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InterfaceGraph extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Projet Debat");
		//Pane pane = new CompteurPane();
		
		//Scene scene = new Scene(pane);
		//stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
	public static void main(String []args) {
		launch(args);
	}
}
