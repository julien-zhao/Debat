
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

public class Interface extends FlowPane{
	private Label lab;
	private Button button;
	
	public Interface() {
		lab = new Label("--> 0");
		button = new Button("OK");

		button.setOnAction((even) -> {

			lab.setText("--> ");
		});
		this.getChildren().addAll(button,lab);
	}
}
