import java.awt.Color;
import java.awt.Graphics;

public class Collider {
	
	// Declare variables
	private int x;	// x-coordinate of the center of the bumper
	private int y;	// y-coordinate of the center of the bumper
	private int width;	// width of the bumper
	private int height;	// height of the bumper
	private Color color;	// color of the bumper
	private static final int LINE_WEIGHT = 5;
	
	/**
	 * Default constructor with x, y, width, height input
	 * black
	 */
	public Collider(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = Color.black;
	}
	
	/**
	 * Constructor with x, y, width, height, and color input
	 */
	public Collider(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	/**
	 * returns the x-coordinate of the corner of the bumper
	 * @return x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the x-coordinate of the corner of the bumper
	 * @param x the x-coordinate of the corner of the bumper
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * returns the y-coordinate of the corner of the bumper
	 * @return the y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the y-coordinate of the corner of the bumper
	 * @param y the y-coordinate of the corner of the bumper
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * returns the width of the bumper
	 * @return width of the width of the bumper
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * sets the width of the bumper
	 * @param width the width of the bumper
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * returns the height of the bumper
	 * @return height the height of the bumper
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * sets the height of the bumper
	 * @param height the height of the bumper
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * returns the color of the bumper
	 * @return color the color of the bumper
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * sets the color of the bumper
	 * @param color the color of the bumper
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * draws bumper with center (x,y) with given width, height, and color
	 * @param g the graphics objects
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	/**
	 * Returns true if any part of the Ball is inside the bumper
	 * @param ball the Ball
	 * @return true if any part of the Ball is inside the bumper, false otherwise
	 */
	public boolean inBumper(Ball ball) {
		for (int x = getX(); x <= getX() + getWidth(); x++) {
			for (int y = getY(); y <= getY() + getHeight(); y++) {
				if (getDistance(x, y, ball.getX(), ball.getY()) <= ball.getRadius()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean clicked(Ball ball) {
		if ((ball.getX() >= getX() && ball.getX() <= getX() + getWidth()) && (ball.getY() >= getY() && ball.getY() <= getY() + getHeight())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Calculates the distance between (x1, y1) and (x2, y2)
	 * @param x1 the x-coordinate of the first point
	 * @param y1 the y-coordinate of the first point
	 * @param x2 the x-coordinate of the second point
	 * @param y2 the y-coordinate of the second point
	 * @return the distance between (x1, y1) and (x2, y2)
	 */
	private double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	
}