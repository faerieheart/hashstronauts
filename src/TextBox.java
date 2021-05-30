import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class TextBox {

	// private variables for text box
	private int x;
	private int y;
	private int width;
	private int height;
	private int size;
	private Color color;
	private String message;
	
	// title screen, customized boxes
	public TextBox(String message, int size) {
		this.x = 0;
		this.y = 800;
		this.width = 1600;
		this.height = 100;
		this.color = Color.WHITE;
		this.message = message;
		this.size = size;
	}
	
	public TextBox(String message, int x, int y, int width, int height, int size, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.message = message;
		this.size = size;
	}
	
	/**
	 * gets the x position
	 * @return the x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the x position
	 * @param x is the x position of the text box
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * gets the y position
	 * @return the y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the y position
	 * @param y is the y position of the beeper
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * gets the width
	 * @return the width 
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * sets the width
	 * @param width is the width of the text box
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * gets the height
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * sets the height
	 * @param height is the height of the text box
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setFont(new Font("Serif", Font.BOLD, size));
		g.drawString(message, x + 25, y + 55);
	}
	
}