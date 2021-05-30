// Ameera Khan
// Ball.java
// ITCS
// without oof collision sound :(

// imports
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


// class name
public class Ball {

	// declaration of variables

	private double x;			//x-coordinate of the center of the ball
	private double y;			//y-coordinate of the center of the ball
	private double diameter;	//diameter of the ball
	private double radius;		//radius of the ball = diameter/2
	private Color color;		//color of the ball
	private double xSpeed;		//x-speed = change in x-position
	private double ySpeed;		//y-speed = change in y-position
	// used in move() method
	private boolean xRev = false; // both start out
	private boolean yDown = false; // as false

	/**
	 * Default Constructor
	 * Creates a red ball at (0, 0) with a diameter of 25.  
	 * The default speed is 0.
	 */
	@SuppressWarnings("static-access")
	public Ball() {
		this.x = 0.0;
		this.y = 0.0;
		this.diameter = 25;
		this.radius = diameter/2;
		this.xSpeed = 0.0;
		this.ySpeed = 0.0;
		this.color = color.RED;

	}

	/**
	 * Constructor allows the user to input the parameters (x, y, diameter, color)
	 * while setting the xSpeed and ySpeed to 0
	 * @param x is the x-coordinate of the center of the ball
	 * @param y is the y-coordinate of the center of the ball
	 * @param diameter is the diameter of the ball
	 * @param color is the color of the ball
	 */
	public Ball(double x, double y, double diameter, Color color) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.radius = diameter/2;
		this.xSpeed = 0.0;
		this.ySpeed = 0.0;
		this.color = color;
	}

	// getters

	/**
	 * Returns the x-coordinate of the ball
	 * @return the x-coordinate of the ball
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the y-coordinate of the ball
	 * @return the y-coordinate of the ball
	 */
	public double getY() {
		return y;
	}

	/**
	 * Returns the diameter of the ball
	 * @return the diameter of the ball
	 */
	public double getDiameter() {
		return diameter;
	}

	/**
	 * Returns the radius of the ball
	 * @return the radius of the ball
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Returns the color of the ball
	 * @return the color of the ball
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns the XSpeed of the ball
	 * @return the XSpeed of the ball
	 */
	public double getXSpeed() {
		return xSpeed;
	}

	/**
	 * Returns the YSpeed of the ball
	 * @return the YSpeed of the ball
	 */
	public double getYSpeed() {
		return ySpeed;
	}

	// setters of the private variables

	/**
	 * Sets the x-coordinate of the ball
	 * @param x is the x-coordinate of the center of the ball
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the y-coordinate of the ball
	 * @param y is the y-coordinate of the center of the ball
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Sets the diameter of the ball
	 * @param diameter is the diameter of the ball
	 */
	public void setDiameter(double diameter) {
		this.diameter = diameter;
		this.radius = diameter/2;
	}

	/**
	 * Sets the radius of the ball
	 * @param color is the color of the ball
	 */
	public void setRadius(double radius) {
		this.radius = radius;
		this.diameter = radius * 2;
	}

	/**
	 * Sets the color of the ball
	 * @param color is the color of the ball
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Sets the horizontal speed of the ball(change in pixels)
	 * @param xSpeed the horizontal speed of the ball(change in pixels)
	 */
	public void setXSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * Sets the vertical speed of the ball(change in pixels)
	 * @param ySpeed the vertical speed of the ball(change in pixels)
	 */
	public void setYSpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	/**
	 * @param g is the graphics object that fills the circle
	 * using variables like x, y, radius, and diameter
	 * These change based on the given numbers for the constructor in the 
	 * main method (BouncingBallTester.java)
	 */
	public void draw(Graphics g) {

		g.setColor(color);
		g.fillOval((int)(x-radius),(int)(y-radius), (int)diameter, (int)diameter);

	}

	/**
	 * randomizes a color that could be set for the 
	 * bouncing ball
	 * @return 
	 */
	public void randomColor() {
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue = (int)(Math.random()*256);
		Color color = new Color(red, green, blue);
		System.out.println("RGB VALUES: " + red + " " + green + " " + blue);
		setColor(color);
	}

	/**
	 * randomizes a size that could be set for a 
	 * bouncing ball
	 */
	public void randomSize() {
		int n = (int)((Math.random()*50) + 10);
		setRadius(n);
		System.out.println("RADIUS VALUE: " + n);
	}

	/**
	 * creates and picks a file and plays it using PlaySound(Sound)
	 */
	//	public void ping() {
	//		// download pidgey.wav onto your laptop for this to work and
	//		// specify the location in quotation marks to hear the sound
	//		// if you don't want to, comment it out here and in move() :(
	//		// oof
	//		File sound = new File("C:\\Users\\797439\\ITCS\\Unit 2 - Graphics\\ROBLOX death sound.wav");
	//
	//		PlaySound(sound);
	//	}

	/**
	 * plays a sound using File sound 
	 * @param Sound is the .wav clip that is called as a clip and will be started
	 * when it is played
	 */
	private void PlaySound(File Sound) 
	{
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();

			// Thread.sleep(clip.getMicrosecondLength()/1000);
		}catch(Exception e)
		{

		}

	}


	/**
	 * Sets the center location of the ball
	 * @param x is the x-coordinate of the center of the ball
	 * @param y is the y-coordinate of the center of the ball
	 */
	public void setLocation(double x, double y) {
		setX(x); 
		setY(y); 

	}

	/**
	 * Generates a speed between -<code>maxSpeed<code>
	 * and <code>maxSpeed<code>
	 * @param maxSpeed is the randomized x and y speeds which
	 * are set using Math.random()
	 */
	public void setRandomSpeed(double maxSpeed) {
		setXSpeed(Math.random()*((maxSpeed * 2) + 1) - maxSpeed);
		setYSpeed(Math.random()*((maxSpeed * 2) + 1) - maxSpeed);
	}

	/**
	 * @param rightEdge is the right side of the screen, the maximum x value the 
	 * ball can reach
	 * @param bottomEdge is the bottom side of the screen, the maximum y value the 
	 * ball can reach
	 */
	public void move(int rightEdge, int bottomEdge) {

		// whenever the ball hits the edge, the ball
		// will change color and size randomly using 
		// randomColor() and randomSize()
		// also can play a sound using method ping()
		// but it won't work on anyone's laptop but mine unless
		// file and location is the same and specified correctly (ROBLOX death sound.wav in ITCS folder...)
		// also the speed is displayed as negative whenever the ball goes to the 
		// left and positive when it goes to the right

		// of course, because the radius changes, the size does as well
		// meaning that if the ball becomes smaller when it hits the 
		// edge, the size and color may be changed once more as well
		// as the sound being played again

		// x-direction
		// if the speed is positive, the ball will go to the right
		// if the speed is negative, the ball will go to the left
		// by using right edge, the program checks if the ball
		// is touching the edge and if it is, it goes in the 
		// REVERSE direction (left-right)

		if(this.getXSpeed() > 0){ // when speed starts out positive
			if(xRev==false) { 
				setX(getX() + getXSpeed()); 
				if (x + radius > rightEdge) { // as long as it is not at the edge keep moving right
					xRev = true; // now it will be true to go backwards
					setXSpeed(getXSpeed() * -1); // then make the speed negative so it can go the other way
					//randomColor();
					//randomSize();
					//					ping();
				}
			}
			else {
				setX(getX() - getXSpeed());
				if (x - radius < 0) {
					xRev = false; // now it will be false to go foward
					//randomColor();
					//					ping();
					//randomSize();
				}
			}
		}

		else { // when speed starts out negative
			if(xRev==false) {
				setX(getX() + getXSpeed());
				if (x - radius < 0) { 
					xRev = true; // now it will be true to go backwards
					setXSpeed(getXSpeed() * -1); // then make the speed negative
					//randomColor();
					//randomSize();
					//					ping();
				}
			}
			else {
				setX(getX() - getXSpeed());
				if (x + radius > rightEdge) {
					xRev = false; // now it will be false to go foward
					//randomColor();
					//randomSize();
					//					ping();
				}
			}
		}

		// y-direction
		// if the speed is positive, the ball will go to up
		// if the speed is negative, the ball will go to down
		// by using bottom edge, the program checks if the ball
		// is touching the edge and if it is, it goes in the 
		// REVERSE direction (up-down)

		if(this.getYSpeed() > 0) { // when speed starts out positive
			if(yDown==false) { // if the ball is going up
				setY(getY() + getYSpeed());
				if (y + radius > bottomEdge) {
					yDown = true; // then the ball will go down next
					//randomColor();
					//randomSize();
					//					ping();
				}
			}
			else { // if the ball is going down
				setY(getY() - getYSpeed());
				if (y - radius < 0) {
					yDown = false; // then the ball will go up next
					setYSpeed(getYSpeed() * -1);
					//randomColor();
					//randomSize();
					//					ping();
				}
			}
		}

		else { // when speed starts out negative
			if(yDown==false) { // if the ball is going up
				setY(getY() + getYSpeed());
				if (y - radius < 0) {
					yDown = true; // then the ball will go down next
					//randomColor();
					//randomSize();
					//					ping();
				}
			}
			else { // if the ball is going down
				setY(getY() - getYSpeed());
				if (y + radius > bottomEdge) {
					yDown = false; // then the ball will go up next
					setYSpeed(getYSpeed() * -1);
					//randomColor();
					//randomSize();
					//					ping();
				}
			}	
		}


	}


}