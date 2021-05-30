// Ameera Khan
// ITCS
// GraphicsUtilities.java but slightly modified with new method

// imports
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

// class
public class GraphicsUtilities {

	/**
	 * Draws a rectangle of the given color from (0, 0) with the given width and height
	 * @param g the Graphics object
	 * @param color the Color of the background
	 * @param width the width of the rectangle
	 * @param height the height of the background
	 */
	public static void drawBackground(Graphics g, Color color, int width, int height) {
		g.setColor(color);
		g.fillRect(0, 0, width, height);
	}
	
	/**
	 * draws a picture (used for characters and backgrounds...)
	 * @param g is the graphics object
	 * @param location is the file name of the image being drawn
	 * @param x is the x location of where the drawing should start
	 * @param y is the y location of where the drawing should start
	 * @param width is the width of the image
	 * @param height is the height of the image
	 * modified at will so can be stretched or even compressed
	 */
	public static void drawPicture(Graphics g, String location, int x, int y, int width, int height) {
		ImageIcon image = new ImageIcon(location);
		g.drawImage(image.getImage(), x, y, width, height, null);
	}
	
	
	
	/**
	 * Creates a grid of vertical and horizontal lines starting at (0,0) to the given 
	 * width and height at the given interval.
	 * @param g the Graphics object
	 * @param color the color of the lines
	 * @param width the width of the grid
	 * @param height the height of the grid
	 * @param increment the distance between each line
	 */
	public static void drawGrid(Graphics g, Color color, int width, int height, int increment) {
		// unused default from class
	}
}