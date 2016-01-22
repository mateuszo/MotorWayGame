

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// TODO: random car movement

		double speed = 30;
		List<Car> cars = new ArrayList<Car>();
		Car myCar = new Car(3, 61, 300, 0, 0);
		Road road = new Road();
		road.getChildren().add(myCar);
		Random rand= new Random();

		// generate enemy cars
		for (int i = 0; i < 50; i++) {
			Car car = new Car(i % 2 + 1, 61 + 40 * rand.nextInt(4),
					- rand.nextInt(10) * 10 * i - i * 45, 0,  1); //rand.nextInt(2)+1);
			cars.add(car);
			road.getChildren().add(car);
		}

		// Create a scene and place it in the stage
		Scene scene = new Scene(road, 270, 400);
		primaryStage.setTitle("MotorWay"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		Timeline animation = new Timeline();

		animation.getKeyFrames().add(new KeyFrame(Duration.millis(speed), e -> {
			//move road
			road.move();
			//move player car
			myCar.move();
			// move enemy cars
			for (Car car : cars) {
				car.move();
			}
			//detect collision
			if(road.detectCollisions(myCar)){
				Text t = new Text(70, 200, "Game over!");
				t.setFont(new Font(25));
				t.setFill(Color.RED);
				road.getChildren().add(t);
				myCar.explode();
				animation.stop();
			}
			
		}));

		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

		// car control
		myCar.requestFocus();
		myCar.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				myCar.moveU();
			} else if (e.getCode() == KeyCode.DOWN) {
				myCar.moveD();
			} else if (e.getCode() == KeyCode.RIGHT) {
				myCar.moveR();
			} else if (e.getCode() == KeyCode.LEFT) {
				myCar.moveL();
			}
		});
		myCar.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
				myCar.stopV();
			} else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT) {
				myCar.stopH();
			}
		});

	}

}
