import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

public class Map {
	
	public Collider walls[];
	public Collider backEnter;
	public Collider loungeEnter;
	public Collider potatoEnter;
	public Collider kitchenEnter;
	public Collider keyEnter;
	public Collider bathroomEnter;
	public Collider xenaEnter;
	public Collider stacyEnter;
	public Collider chickenEnter;
	public Collider frontEnter;
	
	public Map(int width, int height, int lineWeight) {		
		walls = new Collider[35];
		
		// back section
		walls[0] = new Collider(400, 100, 800, lineWeight);
		walls[1] = new Collider(400, 100, lineWeight, 100);
		walls[2] = new Collider(600, 100, lineWeight, 100);
		walls[3] = new Collider(1200, 100, lineWeight, 100);
		backEnter = new Collider(655, 200, 345, lineWeight, Color.white);
		
		// row 2
		walls[4] = new Collider(100, 200, 550, lineWeight);
		walls[5] = new Collider(1000, 200, 500, lineWeight);
		walls[6] = new Collider(100, 200, lineWeight, 200);
		walls[7] = new Collider(650, 200, lineWeight, 200);
		walls[8] = new Collider(1000, 200, lineWeight, 200);
		walls[9] = new Collider(1500, 200, lineWeight, 200);
		loungeEnter = new Collider(650, 225, lineWeight, 150, Color.white);
		potatoEnter = new Collider(1000, 225, lineWeight, 150, Color.white);
		
		// row 3
		walls[10] = new Collider(100, 400, 550, lineWeight);
		walls[11] = new Collider(1000, 400, 500, lineWeight);
		walls[12] = new Collider(1000, 475, 500, lineWeight);
		walls[13] = new Collider(1000, 525, 325, lineWeight);
		walls[34] = new Collider(1475, 525, 25, lineWeight);
		walls[14] = new Collider(100, 400, lineWeight, 200);
		walls[15] = new Collider(650, 400, lineWeight, 200);
		walls[16] = new Collider(1000, 475, lineWeight, 50);
		walls[17] = new Collider(1050, 475, lineWeight, 50);
		walls[18] = new Collider(1300, 475, lineWeight, 50);
		walls[19] = new Collider(1500, 400, lineWeight, 200);
		kitchenEnter = new Collider(650, 425, lineWeight, 150, Color.white);
		keyEnter = new Collider(1000, 485, lineWeight, 30, Color.white);
		bathroomEnter = new Collider(1325, 525, 150, lineWeight, Color.white);
		
		// row 4
		walls[20] = new Collider(100, 600, 550, lineWeight);
		walls[21] = new Collider(1000, 600, 25, lineWeight);
		walls[32] = new Collider(1225, 600, 50, lineWeight);
		walls[33] = new Collider(1475, 600, 25, lineWeight);
		walls[22] = new Collider(100, 600, lineWeight, 200);
		walls[23] = new Collider(650, 600, lineWeight, 200);
		walls[24] = new Collider(1000, 600, lineWeight, 200);
		walls[25] = new Collider(1250, 600, lineWeight, 200);
		walls[26] = new Collider(1500, 600, lineWeight, 205);
		chickenEnter = new Collider(650, 625, lineWeight, 150, Color.white);
		xenaEnter = new Collider(1025, 600, 200, lineWeight, Color.white);
		stacyEnter = new Collider(1275, 600, 200, lineWeight, Color.white);

		// front area
		walls[27] = new Collider(100, 800, 555, lineWeight);
		walls[28] = new Collider(1000, 800, 500, lineWeight);
		walls[29] = new Collider(400, 875, 801, lineWeight);
		walls[30] = new Collider(400, 800, lineWeight, 75);
		walls[31] = new Collider(1200, 800, lineWeight, 80);
		frontEnter = new Collider(655, 800, 345, lineWeight, Color.white);
		
	}
	
	public Collider[] getWalls() {
		return walls;
	}

	public void setWalls(Collider[] walls) {
		this.walls = walls;
	}

	public Collider getBackEnter() {
		return backEnter;
	}

	public void setBackEnter(Collider backEnter) {
		this.backEnter = backEnter;
	}

	public Collider getLoungeEnter() {
		return loungeEnter;
	}

	public void setLoungeEnter(Collider loungeEnter) {
		this.loungeEnter = loungeEnter;
	}

	public Collider getPotatoEnter() {
		return potatoEnter;
	}

	public void setPotatoEnter(Collider potatoEnter) {
		this.potatoEnter = potatoEnter;
	}

	public Collider getKitchenEnter() {
		return kitchenEnter;
	}

	public void setKitchenEnter(Collider kitchenEnter) {
		this.kitchenEnter = kitchenEnter;
	}

	public Collider getKeyEnter() {
		return keyEnter;
	}

	public void setKeyEnter(Collider keyEnter) {
		this.keyEnter = keyEnter;
	}

	public Collider getBathroomEnter() {
		return bathroomEnter;
	}

	public void setBathroomEnter(Collider bathroomEnter) {
		this.bathroomEnter = bathroomEnter;
	}

	public Collider getXenaEnter() {
		return xenaEnter;
	}

	public void setXenaEnter(Collider xenaEnter) {
		this.xenaEnter = xenaEnter;
	}

	public Collider getStacyEnter() {
		return stacyEnter;
	}

	public void setStacyEnter(Collider stacyEnter) {
		this.stacyEnter = stacyEnter;
	}

	public Collider getChickenEnter() {
		return chickenEnter;
	}

	public void setChickenEnter(Collider chickenEnter) {
		this.chickenEnter = chickenEnter;
	}

	public Collider getFrontEnter() {
		return frontEnter;
	}

	public void setFrontEnter(Collider frontEnter) {
		this.frontEnter = frontEnter;
	}

	public void draw(Graphics g) {
		GraphicsUtilities.drawBackground(g, Color.lightGray, 1600, 900);
		
		g.setColor(Color.black);
		
		g.setFont(new Font("Poor Richard", Font.PLAIN, 25));
		g.drawString("Trash Chute", 425, 150);
		g.drawString("Kitchen", 125, 450);
		g.drawString("Bathroom", 1325, 510);
		g.setFont(new Font("Poor Richard", Font.PLAIN, 20));
		g.drawString("Keys", 1005, 510);
		g.setFont(new Font("Poor Richard", Font.PLAIN, 25));
		g.drawString("Lounging Area", 125, 250);
		g.drawString("Potato Room", 1025, 250);
		g.drawString("Chicken Enclosure", 125, 650);
		g.drawString("Xena's Room", 1025, 650);
		g.drawString("Stacy's Room", 1275, 650);
		
		for (int i = 0; i < walls.length; i++) {
			getWalls()[i].draw(g);
		}
		
		getBackEnter().draw(g);
		getLoungeEnter().draw(g);
		getPotatoEnter().draw(g);
		getKitchenEnter().draw(g);
		getKeyEnter().draw(g);
		getBathroomEnter().draw(g);
		getXenaEnter().draw(g);
		getStacyEnter().draw(g);
		getChickenEnter().draw(g);
		getFrontEnter().draw(g);
	}

}
