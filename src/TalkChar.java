import java.awt.Graphics;

import javax.swing.ImageIcon;

public class TalkChar {
	
	public ImageIcon XenaReg = new ImageIcon("XenaReg.png");
	public ImageIcon XenaShock = new ImageIcon("XenaShock.png");
	public ImageIcon XenaSad = new ImageIcon("XenaSad.png");
	public ImageIcon XenaEvil = new ImageIcon("XenaEvil.png");
	
	public ImageIcon StacyReg = new ImageIcon("StacyReg.png");
	public ImageIcon StacyShock = new ImageIcon("StacyShock.png");
	public ImageIcon StacySmile = new ImageIcon("StacySmile.png");
	
	public ImageIcon YukoL = new ImageIcon("yukoL.png");
	public ImageIcon YukoR = new ImageIcon("yukoR.png");
	
	public ImageIcon ViL = new ImageIcon("viL.png");
	public ImageIcon ViR = new ImageIcon("viR.png");
	public ImageIcon ViS = new ImageIcon("viSleep.png");
	
	private int width;
	private int height;
	private int XENAX = 0;
	private int GIRLY = 100;
	private int STELLAX = 1250;
	
	private int POTATOLX = 0;
	private int POTATOY = 400;
	private int POTATORX = 1200;
	
	public TalkChar() {
		this.width = 350;
		this.height = 700;
	}
	
	public TalkChar(int size) {
		this.width = size;
		this.height = size;
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
	
	public void changePotX(int amnt) {
		this.POTATORX += amnt;
	}
	
	public void changeXenaX(int amnt) {
		this.XENAX += amnt;
	}
	
	public void changeStacyX(int amnt) {
		this.STELLAX += amnt;
	}
	
	public void drawXReg(Graphics g) {
		g.drawImage(XenaReg.getImage(), XENAX, GIRLY, width, height, null);
	}
	
	public void drawXShock(Graphics g) {
		g.drawImage(XenaShock.getImage(), XENAX, GIRLY, width, height, null);
	}
	
	public void drawXSad(Graphics g) {
		g.drawImage(XenaSad.getImage(), XENAX, GIRLY, width, height, null);
	}
	
	public void drawXEvil(Graphics g) {
		g.drawImage(XenaEvil.getImage(), XENAX, GIRLY, width, height, null);
	}
	
	
	
	public void drawSReg(Graphics g) {
		g.drawImage(StacyReg.getImage(), STELLAX, GIRLY, width, height, null);
	}
	
	public void drawSShock(Graphics g) {
		g.drawImage(StacyShock.getImage(), STELLAX, GIRLY, width, height, null);
	}
	
	public void drawSSmile(Graphics g) {
		g.drawImage(StacySmile.getImage(), STELLAX, GIRLY, width, height, null);
	}
	
	
	public void drawYL(Graphics g) {
		g.drawImage(YukoL.getImage(), POTATOLX, POTATOY, width, height, null);
	}
	
	public void drawYR(Graphics g) {
		g.drawImage(YukoR.getImage(), POTATORX, POTATOY, width, height, null);
	}

	
	public void drawVL(Graphics g) {
		g.drawImage(ViL.getImage(), POTATOLX, POTATOY, width, height, null);
	}
	
	public void drawVR(Graphics g) {
		g.drawImage(ViR.getImage(), POTATORX, POTATOY, width, height, null);
	}
	
	public void drawVS(Graphics g) {
		g.drawImage(ViS.getImage(), 800, 450, width, height, null);
	}
	
	
}