
/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */
import java.awt.*;
import java.util.*;

public class Asteroids extends Game {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	static int counter = 0;
	static int counter2 = -80;
	boolean collison = false;
	static int lives = 3;
	private java.util.List<Asteroid> randomAsteroids = new ArrayList<Asteroid>();
	

	private Star[] randomStars;
	private java.util.List<Bullet> bullets = new ArrayList<Bullet>();
	

	private Ship ship;

	public Asteroids() {
		super("Asteroids!",SCREEN_WIDTH,SCREEN_HEIGHT);
		this.setFocusable(true);
		this.requestFocus();

		// create a number of random asteroid objects
		randomAsteroids = createRandomAsteroids(10,60,30);
		randomStars = createStars(900,4);



		// create the ship
		ship = createShip();
		this.addKeyListener(ship);
	}

	// private helper method to create the Ship
	private Ship createShip() {
		// Look of ship
		Point[] shipShape = {
				new Point(0, 0),
				new Point(Ship.SHIP_WIDTH/3.5, Ship.SHIP_HEIGHT/2),
				new Point(0, Ship.SHIP_HEIGHT),
				new Point(Ship.SHIP_WIDTH, Ship.SHIP_HEIGHT/2)
		};

		// Set ship at the middle of the screen
		Point startingPosition = new Point((width -Ship.SHIP_WIDTH)/2, (height - Ship.SHIP_HEIGHT)/2);
		int startingRotation = 0; // Start facing to the right

		return new Ship(shipShape, startingPosition, startingRotation);
	}

	// Create a certain number of stars with a given max radius
	public Star[] createStars(int numberOfStars, int maxRadius) {
		Star[] stars = new Star[numberOfStars];
		for(int i = 0; i < numberOfStars; ++i) {
			Point center = new Point
					(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);


			int radius = (int) (Math.random() * maxRadius);
			if(radius < 1) {
				radius = 1;
			}
			stars[i] = new Star(center, radius);

		}


		return stars;
	}



	//  Create an array of random asteroids
	private java.util.List<Asteroid> createRandomAsteroids(int numberOfAsteroids, int maxAsteroidWidth,
			int minAsteroidWidth) {
		java.util.List<Asteroid> asteroids = new ArrayList<>(numberOfAsteroids);

		for(int i = 0; i < numberOfAsteroids; ++i) {
			// Create random asteroids by sampling points on a circle
			// Find the radius first.
			int radius = (int) (Math.random() * maxAsteroidWidth);
			if(radius < minAsteroidWidth) {
				radius += minAsteroidWidth;
			}
			// Find the circles angle
			double angle = (Math.random() * Math.PI * 1.0/2.0);
			if(angle < Math.PI * 1.0/5.0) {
				angle += Math.PI * 1.0/5.0;
			}
			// Sample and store points around that circle
			ArrayList<Point> asteroidSides = new ArrayList<Point>();
			double originalAngle = angle;
			while(angle < 2*Math.PI) {
				double x = Math.cos(angle) * radius;
				double y = Math.sin(angle) * radius;
				asteroidSides.add(new Point(x, y));
				angle += originalAngle;
			}
			// Set everything up to create the asteroid
			Point[] inSides = asteroidSides.toArray(new Point[asteroidSides.size()]);
			Point inPosition = new Point(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
			double inRotation = Math.random() * 360;
			asteroids.add(new Asteroid(inSides, inPosition, inRotation));
		}
		return asteroids;
	}

	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);


		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter,10,10);
		brush.drawString(lives + "/3 lives remaining ",650,550);

		// display the random asteroids
		for (Asteroid asteroid : randomAsteroids) {
			asteroid.paint(brush,Color.white);
			asteroid.move();
			if(	asteroid.collision(ship) ) {
				counter2 = counter;
				lives--;

				
			}
				
			if (counter - counter2 < 10) {
				ship.paint(brush, Color.red);
			}

			else 
				ship.paint(brush, Color.magenta);
				ship.move();
		}
		
		
		if ( randomAsteroids.isEmpty()&&lives>0) {
			brush.setColor(Color.black);
			brush.fillRect(0, 0, width, height);
			brush.setColor(Color.yellow);
			brush.drawString("You won!", 300,300);
			super.on = false;
		}
		else if (lives <= 0){
				brush.setColor(Color.black);
				brush.fillRect(0, 0, width, height);
				brush.setColor(Color.yellow);
				brush.drawString("You lost!", 300,300);
				super.on = false;
		}

			for(Star star :randomStars  ) 
			{
				star.paint(brush, Color.white);
			}
			
			if(counter%10<5) {
				for(int i =0; i< randomStars.length; i++ ) {
					if (i%2==0) {
						randomStars[i].paint(brush, Color.orange);
						
					}
					else
						randomStars[i].paint(brush, Color.gray);
				}
			}
				if(counter%10>=5) {
					for(int i =0; i< randomStars.length; i++ ) {
						if (i%2==0) {
							randomStars[i].paint(brush, Color.pink);
							
						}
						else
							randomStars[i].paint(brush, Color.white);
					}
					
					
				
				

			Iterator <Bullet> c = ship.getBullets(). iterator();

			    while(c.hasNext()) {
			    	Bullet a = c.next();
			    	a.paint(brush, Color.red);
					a.move();
			    
				
				
				if(a.outOfBounds()) {
					c.remove();
				
				}
			
						for ( int j =0; j< randomAsteroids.size(); j++ ) {
							if (randomAsteroids.get(j).contains(a.getCenter())) {
							randomAsteroids.remove(randomAsteroids.get(j));
							c.remove();
						}
							

					
							

						
					}
				
					
				}
		
				}
		}
	
				
		
			
		
				
		
		
				
			
		

	







	/**
	 * The above for loop (known as a "for each" loop)
	 * is equivalent to what is shown below.
	 */

	/**
		for (int i = 0; i < randomAsteroids.size(); i++) {
			randomAsteroids.get(i).paint(brush, Color.white);
			randomAsteroids.get(i).move();

		}
	 */

	// have the ship appear on the screen


	public static void main (String[] args) {
		Asteroids a = new Asteroids();
		a.repaint();
	}
}