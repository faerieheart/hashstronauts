import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Potato {
	
	public ImageIcon YukoFL = new ImageIcon("yukoFL.png");
	public ImageIcon YukoFR = new ImageIcon("yukoFR.png");
	public ImageIcon YukoSL = new ImageIcon("yukoSL.png");
	public ImageIcon YukoSR = new ImageIcon("yukoSR.png");
	public ImageIcon YukoBL = new ImageIcon("yukoBL.png");
	public ImageIcon YukoBR = new ImageIcon("yukoBR.png");
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Potato() {
		this.x = 0;
		this.y = 400;
		this.width = 400;
		this.height = 400;
	}
	
	public Potato(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setSize(int size) {
		this.width = size;
		this.height = size;
	}
	
	public void drawSL(Graphics g) {
		g.drawImage(YukoSL.getImage(), x, y, width, height, null);
	}
	
	public void drawSR(Graphics g) {
		g.drawImage(YukoSR.getImage(), x, y, width, height, null);
	}
	
	public void drawBL(Graphics g) {
		g.drawImage(YukoBL.getImage(), x, y, width, height, null);
	}
	
	public void drawBR(Graphics g) {
		g.drawImage(YukoBR.getImage(), x, y, width, height, null);
	}
	
	public void drawFL(Graphics g) {
		g.drawImage(YukoFL.getImage(), x, y, width, height, null);
	}
	
	public void drawFR(Graphics g) {
		g.drawImage(YukoFR.getImage(), x, y, width, height, null);
	}
	
	
}