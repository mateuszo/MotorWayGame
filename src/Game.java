
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		double speed = 30;
		Car car = new Car(1,140,300);
		Car car2 = new Car(2,100,200);
		Road road = new Road();
		
		road.getChildren().addAll(car, car2);
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(road,270,400);
		primaryStage.setTitle("Ex04"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(speed), e -> road.move()));
		animation.getKeyFrames().add(new KeyFrame(Duration.millis(speed), e -> car.move()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

		
		
		
		//car control
		car.requestFocus();
		car.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				car.moveU();
			} else if (e.getCode() == KeyCode.DOWN) {
				car.moveD();
			} else if (e.getCode() == KeyCode.RIGHT){
				car.moveR();
			} else if (e.getCode() == KeyCode.LEFT){
				car.moveL();
			}
		});
		car.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
				car.stopV();
			} else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT){
				car.stopH();
			}
		});
		
		
		
	}

}
