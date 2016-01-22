import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Road extends Pane {
	private double width = 270;
	private double height = 400;
	private double margin = 50;
	private double laneWidth = 40;
	private double laneLength = 100000;
	private double y = 0;

	private Rectangle lwall = new Rectangle(margin, 0, 10, height);
	private Rectangle rwall = new Rectangle(width - margin - 10, 0, 10, height);

	private List<Line> lanes = new ArrayList<Line>();

	public Road() {
		Line line;
		for (int i = 1; i < ((width - margin - 10) / laneWidth) - 1; i++) {
			line = new Line(margin + 5 + laneWidth * i, -laneLength, margin + 5 + laneWidth * i, height);
			line.getStrokeDashArray().addAll(15d);
			lanes.add(line);
		}

		lwall.setFill(Color.BLACK);
		rwall.setFill(Color.BLACK);
		this.getChildren().addAll(lwall, rwall);
		this.getChildren().addAll(lanes);
	}

	public void move() {
		this.y += 2;
		for (Line l : this.lanes) {
			l.setLayoutY(this.y);
		}
	}

	public boolean detectCollisions(Car car) {
		for (Node item : this.getChildren()) {

			//ignore self and lanes
			if (item != car && !this.lanes.contains(item)) {
				if (item.getBoundsInParent().intersects(car.getBoundsInParent())) {
					
					return true;
				}
			}
		}
		return false;
	}

}
