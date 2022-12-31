/**
 * A representation of a ship.
 */
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Ship extends Polygon implements java.awt.event.KeyListener {
	public static final int SHIP_WIDTH = 40;
	public static final int SHIP_HEIGHT = 25;
	boolean up = false;
	boolean right = false;
	boolean left = false;
	boolean shoot = false; 
	boolean fire = false; 
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public Ship(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}

	public void  keyTyped (KeyEvent e) {
		return;
	}


	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
			System.out.println("works");
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {

			right = true;

		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {

			left = true;

		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE) {

			shoot = true;
			
			
			

		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {

			right = false;

		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {

			left = false;

		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE) {

			shoot = false;
			fire = false;

		}
	}


	public ArrayList<Bullet> getBullets() {
		return bullets; 

	}


	// Create paint method to paint a ship
	public void paint(Graphics brush, Color color) {
		Point[] points = getPoints();
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];

		int nPoints = points.length;

		for(int i = 0; i < nPoints; ++i) {
			xPoints[i] = (int) points[i].x;
			yPoints[i] = (int) points[i].y;
		}

		brush.setColor(color);
		brush.fillPolygon(xPoints, yPoints, nPoints);
	}

	public void move() {

		// we'll just demonstrate the ship moving across the x axis.


		/**
		 * If the ship moves off of the screen either along the
		 * x or y axis, have the ship re-appear coming from the other side.
		 */


		if (up) {
			position.x += 1 * Math.cos(Math.toRadians(rotation));
			position.y += 1 * Math.sin(Math.toRadians(rotation));

		}

		if (right) {
			rotate(3);
		}

		if (left) {
			rotate(-3);
		}

		if(position.x > Asteroids.SCREEN_WIDTH) {
			position.x -= Asteroids.SCREEN_WIDTH;
		} else if(position.x < 0) {
			position.x += Asteroids.SCREEN_WIDTH;
		}

		if(position.y > Asteroids.SCREEN_HEIGHT) {
			position.y -= Asteroids.SCREEN_HEIGHT;
		} else if(position.y < 0) {
			position.y += Asteroids.SCREEN_HEIGHT;
		}
			
		if (shoot && !fire) {
			Point  Array [] = getPoints(); 
			Bullet a = new Bullet (Array[1], rotation) ;
			bullets.add(a);	
			fire = true;
			
		}
		

	}
}
