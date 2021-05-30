import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class testStuff extends JPanel {

	private BufferedImage image;
	private Graphics g;
	//private Map map;
	private Collider walls[];

	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;
	private static final int LINE_WEIGHT = 5;

	public testStuff() {

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		
		walls = new Collider[33];

		GraphicsUtilities.drawBackground(g, Color.lightGray, WIDTH, HEIGHT);

		// borders on map
		g.setColor(Color.black);
		
		// back section
		walls[0] = new Collider(400, 100, 800, LINE_WEIGHT);
		walls[1] = new Collider(400, 100, LINE_WEIGHT, 100);
		walls[2] = new Collider(600, 100, LINE_WEIGHT, 100);
		walls[3] = new Collider(1200, 100, LINE_WEIGHT, 100);
		
		// row 2
		walls[4] = new Collider(100, 200, 550, LINE_WEIGHT);
		walls[5] = new Collider(1000, 200, 500, LINE_WEIGHT);
		walls[6] = new Collider(100, 200, LINE_WEIGHT, 200);
		walls[7] = new Collider(650, 200, LINE_WEIGHT, 200);
		walls[8] = new Collider(1000, 200, LINE_WEIGHT, 200);
		walls[9] = new Collider(1500, 200, LINE_WEIGHT, 200);
		
		// row 3
		walls[10] = new Collider(100, 400, 550, LINE_WEIGHT);
		walls[11] = new Collider(1000, 400, 500, LINE_WEIGHT);
		walls[12] = new Collider(1000, 475, 500, LINE_WEIGHT);
		walls[13] = new Collider(1000, 525, 500, LINE_WEIGHT);
		walls[14] = new Collider(100, 400, LINE_WEIGHT, 200);
		walls[15] = new Collider(650, 400, LINE_WEIGHT, 200);
		walls[16] = new Collider(1000, 475, LINE_WEIGHT, 50);
		walls[17] = new Collider(1050, 475, LINE_WEIGHT, 50);
		walls[18] = new Collider(1400, 475, LINE_WEIGHT, 50);
		walls[19] = new Collider(1500, 400, LINE_WEIGHT, 200);
		
		// row 4
		walls[20] = new Collider(100, 600, 550, LINE_WEIGHT);
		walls[21] = new Collider(1000, 600, 505, LINE_WEIGHT);
		walls[22] = new Collider(100, 600, LINE_WEIGHT, 200);
		walls[23] = new Collider(550, 600, LINE_WEIGHT, 200);
		walls[24] = new Collider(650, 600, LINE_WEIGHT, 200);
		walls[25] = new Collider(1000, 600, LINE_WEIGHT, 200);
		walls[26] = new Collider(1250, 600, LINE_WEIGHT, 200);
		walls[27] = new Collider(1500, 600, LINE_WEIGHT, 205);

		// front area
		walls[28] = new Collider(100, 800, 555, LINE_WEIGHT);
		walls[29] = new Collider(1000, 800, 500, LINE_WEIGHT);
		walls[30] = new Collider(400, 875, 801, LINE_WEIGHT);
		walls[31] = new Collider(400, 800, LINE_WEIGHT, 75);
		walls[32] = new Collider(1200, 800, LINE_WEIGHT, 80);
		
		for (int i = 0; i < walls.length; i++) {
			walls[i].draw(g);
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args) {

		// frame settings
		JFrame frame = new JFrame("test");
		frame.setSize(WIDTH + 18, HEIGHT + 47);
		frame.setLocation(150, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new testStuff());
		frame.setVisible(true);
	}

}
