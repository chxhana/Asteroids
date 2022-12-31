import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Circle {
	
	protected double rotation; 
	final static int RADIUS = 5;
	
	
	public Bullet(Point center, double rotation) {
		super(center, RADIUS); // define RADIUS in Bullet class
		this.rotation = rotation;
		this.center = center; 
		}


	@Override
	public void paint(Graphics brush, Color color) {
		// TODO Auto-generated method stub
		brush.setColor(color);
		brush.fillOval((int)center.x, (int)center.y, radius, radius);

	}

	@Override
	public void move() {
		center.x += 5 * Math.cos(Math.toRadians(rotation));
		center.y += 5* Math.sin(Math.toRadians(rotation));
		}
	
	public boolean outOfBounds() {
		if ((center.y > Asteroids.SCREEN_HEIGHT)||(center.x > Asteroids.SCREEN_WIDTH) || (center.x < 0)||(center.y <0 )){
			return true;
			
		}
			return false;
		}
	
	public Point getCenter(){
		return  center;
	}

}
