
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
		
		
		//TODO: collision detection
		//TODO: random car generation
		//TODO: random car movement
		
		
		double speed = 30;
		Car myCar = new Car(1,140,300);
		Car car2 = new Car(2,100,200);
		Car car3 = new Car(3,180,320);
		
		Road road = new Road();
		
		road.getChildren().addAll(myCar, car2, car3);
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(road,270,400);
		primaryStage.setTitle("MotorWay"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(speed), e -> road.move()));
		animation.getKeyFrames().add(new KeyFrame(Duration.millis(speed), e -> myCar.move()));
		animation.getKeyFrames().add(new KeyFrame(Duration.millis(speed), e -> road.detectCollisions(myCar)));
		
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
		
		
		//car control
		myCar.requestFocus();
		myCar.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				myCar.moveU();
			} else if (e.getCode() == KeyCode.DOWN) {
				myCar.moveD();
			} else if (e.getCode() == KeyCode.RIGHT){
				myCar.moveR();
			} else if (e.getCode() == KeyCode.LEFT){
				myCar.moveL();
			}
		});
		myCar.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
				myCar.stopV();
			} else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT){
				myCar.stopH();
			}
		});
				
		
	}
	

}
