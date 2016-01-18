import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Car extends ImageView {
	private Image image;
	private double x;
	private double y;
	private int velocityX = 0;
	private int velocityY = 0;
	
	/**
	 * Creates car image.
	 * @param no - car image number
	 * @param posX
	 * @param posY
	 */
	public Car(int no, double posX, double posY){
		//get car image based on number
		this.x = posX;
		this.y = posY;
		this.image = new Image("file:car"+no+".png");
		this.setImage(image);
		this.setLayoutX(x);
		this.setLayoutY(y);
		System.out.println("Car no: " + no + " heigth: " + image.getHeight());
		System.out.println("Car no: " + no + " width: " + image.getWidth());
		
	}
	
	public void moveR(){
		x += 1;
		this.setLayoutX(x);
	}
	
	public void moveL(){
		x -= 1;
		this.setLayoutX(x);
	}
	
	public void moveU(){
		y -= 1;
		this.setLayoutY(y);
	}
	
	public void moveD(){
		y += 1;
		this.setLayoutY(y);
	}
	
}
