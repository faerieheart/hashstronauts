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

public class Game extends JPanel {

	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;
	private static final int LINE_WEIGHT = 5;
	public int churching = 0;

	private double time = 0;
	private double longTime = 0;
	// advancing
	private double advance = 0;
	private double advance2 = 0;

	private double goodEnd = 0;
	private int badEnd = 0;
	public static boolean endB = false;
	private int whichFace = 4;
	private int yukoStep = 0;
	public boolean outsideAgain = false;

	private Potato yukoSprite;
	private Ball yukoCheck;
	
	private TalkChar yuko;
	private TalkChar vi;
	private TalkChar xena;
	private TalkChar stacy;
	private Map map = new Map(WIDTH, HEIGHT, LINE_WEIGHT);

	// emotion signals
	public boolean jewelry = false;
	public boolean pool;
	public boolean church;
	public boolean outside = false;
	public boolean showPoolPicture;
	
	//rooms
	public boolean lounge = false;
	public boolean kitchen = false;
	public boolean bathroom = false;
	public boolean potatoroom = false;
	public boolean front = false;
	public boolean back = false;
	public boolean chicken = false;
	public boolean chickenCheck = false;
	public boolean girlRoom = false;
	public boolean keys = false;
	public int roomInc = 0;
	
	public int ending = 0;
	public boolean canEnter = true;
	public boolean canMove = false;
	public boolean isText = true;
	public boolean action1 = false;
	public boolean action2 = false;
	public boolean articles = false;
	public boolean mapShow = false;
	public boolean mapShow2 = false;
	public boolean find2 = false;
	public boolean stacyQs = false;
	public boolean xenaQs = false;
	
	//when to draw characters/where
	public boolean yukoL = false;
	public boolean yukoR = false;
	public boolean viL = false;
	public boolean viR = false;
	public boolean xenaDraw = false;
	public int xenaFace = 0;
	public boolean stacyDraw = false;
	public int stacyFace = 0;

	private BufferedImage image;
	private Graphics g;
	public Timer timer;
	private Ball clicker;
	public TextBox text;
	
	private Collider frier;
	private Collider offFrier;
	private Collider masterkey;
	private Collider clip;
	private Collider lounges[];
	private Collider bathroomObj;

	static File theme1 = new File("music1.wav");
	static File theme2 = new File("music2.wav");
	static File song = theme1;
	static Clip sound;

	public Game() {
		// buffered image for animation
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

		timer = new Timer(0, new TimerListener());
		timer.start();
		addKeyListener(new Keyboard());
		setFocusable(true);
		addMouseListener(new Mouse());

		clicker = new Ball(200, 350, 5, Color.white);
		yukoCheck = new Ball(820, 470, 40, Color.white);
		yukoSprite = new Potato(800, 450, 40, 40);
		
		yuko = new TalkChar(400);
		vi = new TalkChar(400);
		xena = new TalkChar();
		stacy = new TalkChar();
		//map = new Map(WIDTH, HEIGHT, LINE_WEIGHT);
	}

	static void ChangeSong(File newSong) {
		if (song != newSong) {
			sound.stop();
			song = newSong;
			PlaySound();
		}

	}

	static void PlaySound() {
		try { // file sound is viewed as a clip
			sound = AudioSystem.getClip(); // gets the clip
			sound.open(AudioSystem.getAudioInputStream(song)); // opens the clip
			sound.start(); // starts the clip
			sound.loop(999);
		} catch (Exception e) { // catches exceptions
			System.out.println("fail");
		}
	}

	public static void run(File sound) {
		while (true) // continuously plays the audio file
		{
			PlaySound();
		}
	}
	
	private class Mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!e.isShiftDown() && !e.isMetaDown()) {
				clicker.setX(e.getX());
				clicker.setY(e.getY());
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	// keyboard input
	private class Keyboard implements KeyListener {

		@Override
		/**
		 * keyPressed checks what key is pressed and allows the bumper to move to that
		 * position
		 */
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_W) {
				yukoSprite.setY(yukoSprite.getY() - 10);
				yukoCheck.setY(yukoSprite.getY() + 20);
				whichFace = 1;
				yukoStep++;
				collision(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				yukoSprite.setY(yukoSprite.getY() + 10);
				yukoCheck.setY(yukoSprite.getY() + 20);
				whichFace = 4;
				yukoStep++;
				collision(1);
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				yukoSprite.setX(yukoSprite.getX() - 10);
				yukoCheck.setX(yukoSprite.getX() + 20);
				whichFace = 3;
				yukoStep++;
				collision(2);
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				yukoSprite.setX(yukoSprite.getX() + 10);
				yukoCheck.setX(yukoSprite.getX() + 20);
				whichFace = 2;
				yukoStep++;
				collision(3);
			}
			
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE && advance >= 54) {
				lounge = false;
				kitchen = false;
				bathroom = false;
				potatoroom = false;
				front = false;
				back = false;
				girlRoom = false;
				keys = false;
				
				if (find2) {
					chicken = false;
				}
				
				mapShow = true;
				roomInc = 0;
				
				if (articles) {
					articles = false;
					lounge = true;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_P) {
				pool = true;

			}

			if (e.getKeyCode() == KeyEvent.VK_C) {
				church = true;

			}
			
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				advance2 = 23;
				advance = 55;
				ending = 1;
				canEnter = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			}

			if (canEnter && advance < 54) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					advance++;
				}
			} else if (canEnter && advance == 54 && e.getKeyCode() == KeyEvent.VK_ENTER) {
				mapShow = true;
				advance = 55;
			}
			
			if (chickenCheck) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					chicken = true;
					chickenCheck = false;
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					chickenCheck = false;
					mapShow = true;
				}
			} else if (chicken) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					chicken = false;
					advance2 = 1;
				}
			}
			
			if (canEnter && advance2 >= 1 && advance2 < 22) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					advance2++;
				}
			} else if (canEnter && advance2 == 22 && e.getKeyCode() == KeyEvent.VK_ENTER) {
				lounge = false;
				kitchen = false;
				bathroom = false;
				potatoroom = false;
				front = false;
				back = false;
				girlRoom = false;
				keys = false;
				chicken = false;
				
				mapShow2 = true;
				find2 = true;
				advance2 = 23;
			}
			
			if ((stacyQs && roomInc <= 10) || (xenaQs && roomInc <= 8)) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					roomInc++;
				}
			} else if (stacyQs && roomInc > 10) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					roomInc = 0;
					stacyQs = false;
					lounge = true;
				}
			} else if (xenaQs && roomInc > 8) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					roomInc = 0;
					xenaQs = false;
					back = true;
				}
			}
			
			if (front && find2) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ending = 1;
					front = false;
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					front = false;
					mapShow2 = true;
				}
			}
			
			if (canEnter && ending >= 1 && ending <= 3) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ending++;
				}
			} else if (canEnter && goodEnd >= 1 && goodEnd <= 23) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					goodEnd++;
				}
			} else if (canEnter && badEnd >= 1) {
				System.out.println(badEnd);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(badEnd);
					badEnd++;
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	public void drawMain() {
		GraphicsUtilities.drawPicture(g, "intro.png", 0, 0, WIDTH, HEIGHT);
	}

	public void drawMain2() {
		GraphicsUtilities.drawPicture(g, "space.png", 0, 0, WIDTH, HEIGHT);
	}

	public void drawHallway() {
		GraphicsUtilities.drawPicture(g, "hallway.png", 0, 0, WIDTH, HEIGHT);
	}

	public void drawKitchen() {
		GraphicsUtilities.drawPicture(g, "kitchen.png", 0, 0, WIDTH, HEIGHT);
		drawKitchenObjects();
	}
	
	public void drawKitchenObjects() {
		//deep frier
		frier = new Collider (425, 400, 400, 375);
		
		//frier off switch
		offFrier = new Collider(465, 380, 57, 25);
		g.setColor(Color.black);
		g.fillRect(465, 380, 57, 25);
		g.setColor(Color.white);
		g.setFont(new Font("Poor Richard", Font.BOLD, 20));
		g.drawString("OFF", 475, 400);
		
		//master key
		GraphicsUtilities.drawPicture(g, "masterkey.png", 1400, 750, 75, 75);
		masterkey = new Collider(1400, 750, 75, 75);
		
		//hair clip
		GraphicsUtilities.drawPicture(g, "hairclip.png", 30, 700, 75, 75);
		clip = new Collider(30, 700, 75, 75);
	}
	
	public void drawFrenchFries() {
		GraphicsUtilities.drawPicture(g, "murder.jpg", 0, 0, WIDTH, HEIGHT);
	}

	public void drawPotatoRoom() {
		GraphicsUtilities.drawPicture(g, "potatoroom.png", 0, 0, WIDTH, HEIGHT);
	}

	public void drawLounge() {
		GraphicsUtilities.drawPicture(g, "lounge.png", 0, 0, WIDTH, HEIGHT);
		loungeObjects();
	}
	
	public void loungeObjects() {
		GraphicsUtilities.drawPicture(g, "computer.png", 500, 125, 100, 100);
		GraphicsUtilities.drawPicture(g, "computer.png", 600, 125, 100, 100);
		GraphicsUtilities.drawPicture(g, "computer.png", 700, 125, 100, 100);
		lounges = new Collider[5];
		lounges[0] = new Collider(500, 125, 100, 100);
		lounges[1] = new Collider(600, 125, 100, 100);
		lounges[2] = new Collider(700, 125, 100, 100);
		
		GraphicsUtilities.drawPicture(g, "chair.png", 1275, 325, 300, 300);
		lounges[3] = new Collider(1275, 325, 300, 300);
		
		lounges[4] = new Collider(1250, 100, 350, 700);
	}
	
	public void drawArticles() {
		GraphicsUtilities.drawPicture(g, "articles.png", 0, 0, WIDTH, HEIGHT);
	}

	public void drawBathroom() {
		GraphicsUtilities.drawPicture(g, "bathroom.png", 0, 0, WIDTH, HEIGHT);
	}
	
	public void bathroomObjects() {
		GraphicsUtilities.drawPicture(g, "razor.png", 1300, 700, 200, 100);
		GraphicsUtilities.drawPicture(g, "bow.png", 1350, 650, 100, 100);
		bathroomObj = new Collider(1300, 650, 200, 150);
	}
	
	public void drawFront() {
		GraphicsUtilities.drawPicture(g, "front.png", 0, 0, WIDTH, HEIGHT);
	}
	
	public void drawBack() {
		GraphicsUtilities.drawPicture(g, "back.png", 0, 0, WIDTH, HEIGHT);
	}
	
	public void drawChickenRoom() {
		GraphicsUtilities.drawPicture(g, "chicken.png", 0, 0, WIDTH, HEIGHT);
	}
	
	public void drawMap() {
		map.draw(g);
	}
	
	public void drawBadEnd() {
		GraphicsUtilities.drawBackground(g, Color.red, WIDTH, HEIGHT);
		g.setColor(Color.white);
		g.fillRect(625, 275, 425, 175);
		
		g.setColor(Color.black);
		g.setFont(new Font("Poor Richard", Font.BOLD, 100));
		g.drawString("Bad End", 650, 400);
	}
	
	public void drawGoodEnd() {
		//GraphicsUtilities.drawBackground(g, Color.green, WIDTH, HEIGHT);
		g.setColor(Color.white);
		g.fillRect(625, 275, 450, 175);
		
		g.setColor(Color.black);
		g.setFont(new Font("Poor Richard", Font.BOLD, 100));
		g.drawString("Good End", 650, 400);
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			//ChangeSong(theme1);

			drawMain2();
			g.setColor(Color.white);
			
			
			if (advance == 0) {
				drawMain();
				g.setColor(Color.white);
				g.fillRect(545, 720, 550, 75);
				
				g.setColor(Color.black);
				g.setFont(new Font("Poor Richard", Font.BOLD, 50));
				g.drawString("Press Enter To Advance", 575, 775);
			} else {
				g.setFont(new Font("Poor Richard", Font.BOLD, 30));
				if (advance == 1) {
					text = new TextBox("On a recent cultural exchange trip, three potatoes, Yuko N. Gold, Vi T. Lotte, and Newton,", 30);
				}
				if (advance == 2) {
					text = new TextBox("Went to the human world to learn about humans and to teach the humans about potatoes.", 30);
				}
				if (advance == 3) {
					text = new TextBox("Two humans have agreed to go to the potato planet to do the same", 30);
				}
				if (advance == 4) {
					text = new TextBox("and now all five astronauts are flying back to the potato planet.", 30);
				}
				if (advance == 5) {
					text = new TextBox("Now half-way into the trip, Yuko, the lead potato astronaut, is on night-duty.", 30);
				}
				if (advance == 6) {
					drawHallway();
					yukoL = true;
					text = new TextBox("Before the big exchange trip, I was a bit worried.", 30);
				}
				if (advance == 7) {
					drawHallway();
					text = new TextBox("Three potatoes like us going to the human world sounds crazy but it went well.", 30);
				}
				if (advance == 8) {
					drawHallway();
					text = new TextBox("What’s there to be worried about?", 30);
				}
				if (advance == 9) {
					drawHallway();
					text = new TextBox("I thought that some humans would still be mad", 30);
				}
				if (advance == 10) {
					drawHallway();
					text = new TextBox("About the potatoes leaving to colonize a new planet but everyone was really nice to us.", 30);
				}
				if (advance == 11) {
					drawHallway();
					text = new TextBox("We brought back two humans so that we can teach the other potatoes about humans firsthand.", 30);
				}
				if (advance == 12) {
					drawHallway();
					text = new TextBox("Xena’s a bit bubbly but very smart", 30);
				}
				if (advance == 13) {
					drawHallway();
					text = new TextBox("She let us try a lot of new human foods but made it in a way that us potatoes can eat.", 30);
				}
				if (advance == 14) {
					drawHallway();
					text = new TextBox("Stacy’s more quiet.", 30);
				}
				if (advance == 15) {
					drawHallway();
					text = new TextBox("She’s very kind though and showed us many human games.", 30);
				}
				if (advance == 16) {
					drawHallway();
					text = new TextBox("...", 30);
				}
				if (advance == 17) {
					drawHallway();
					text = new TextBox("Night patroll’s boring and it’s only three A.M.", 30);
				}
				if (advance == 18) {
					drawHallway();
					text = new TextBox("Time for a snack", 30);
				}
				if (advance == 19) {
					drawKitchen();
					yukoL = false;
					isText = false;
				}
				if (advance == 20) {
					drawKitchen();
					yukoL = true;
					isText = true;
					text = new TextBox("It opened without using the night duty key…", 30);
					clicker.setX(0);
					clicker.setY(0);
				}
				if (advance == 21) {
					drawKitchen();
					yukoL = false;
					canEnter = false;
					text = new TextBox("Explore the kitchen to discover what is wrong", 0, 0, WIDTH, 100, 30, Color.white);
					if (frier.clicked(clicker) == true) {
						advance++;
					} else if (clicker.getX() != 0 && clicker.getY() != 0){
						text = new TextBox("Odd...", 30);
					}
				}

				if (advance == 22) {
					drawKitchen();
					canEnter = true;
					text = new TextBox("The deep fryer’s on!! That’s dangerous!", 30);
					clicker.setX(0);
					clicker.setY(0);
				}

				if (advance == 23) {
					drawKitchen();
					canEnter = false;
					text = new TextBox("Turn off the deep fryer", 0, 0, WIDTH, 100, 30, Color.white);
					if (offFrier.clicked(clicker) == true) {
						advance++;
					}
					
				}
				if (advance == 24) {
					drawFrenchFries();
					isText = true;
					canEnter = true;
					text = new TextBox("!!!!!! FRENCH FRIES !!!!!!", 30);
				}
				if (advance == 25) {
					drawFrenchFries();
					text = new TextBox("It’s Newton!", 30);
				}
				if (advance == 26) {
					drawFrenchFries();
					text = new TextBox("This can’t be happening!", 30);
				}
				if (advance == 27) {
					drawFrenchFries();
					text = new TextBox("You were only four months old! What happened!?!", 30);
				}
				if (advance == 28) {
					drawFrenchFries();
					text = new TextBox("I need to get to the bottom of this...", 30);
				}
				if (advance == 29) {
					drawKitchen();
					text = new TextBox("I guess I should start investigating here.", 30);
					clicker.setX(0);
					clicker.setY(0);
				}

				if (advance == 30) {
					drawKitchen();
					canEnter = false;
					text = new TextBox("Start your investigation: click on things to learn more about them", 0, 0, WIDTH, 100, 30, Color.white);
					if (frier.clicked(clicker) == true) {
						text = new TextBox("I've already looked at the frier.", 30);
					} else if (clip.clicked(clicker) == true){
						text = new TextBox("A hair clip? Only the humans wear hair accessories. Only the humans have hair...", 30);
						action1 = true;
					} else if (masterkey.clicked(clicker) == true){
						text = new TextBox("This is… THE MASTER KEY!! What’s this doing here? Don’t you need two crew keys to get this?", 30);
						action2 = true;
					} else if (offFrier.clicked(clicker) == true){
						text = new TextBox("I already turned the frier off.", 30);
					}
					if (action1 == true && action2 == true) {
						canEnter = true;
						//text = new TextBox("You have found all needed items. Hit ENTER to move on.", 0, 0, WIDTH, 100, 30, Color.white);
					}
				}

				if (advance == 31) {
					drawHallway();
					yukoL = true;
					text = new TextBox("So it had to be a murder from the way Newton was cut up.", 30);
				}

				if (advance == 32) {
					drawHallway();
					text = new TextBox("And what’s with this hair clip?", 30);
				}
				
				if (advance == 33) {
					drawHallway();
					text = new TextBox("Was it one of the humans…", 30);
				}
				
				if (advance == 34) {
					drawHallway();
					isText = false;
					stacyDraw = true;
					stacyFace = 0;
				}
				
				if (advance == 35) {
					drawHallway();
					isText = true;
					stacyDraw = false;
					yukoL = true;
					text = new TextBox("“Oh! Stacy! What are you doing out here?”", 30);
				}
				
				if (advance == 36) {
					drawHallway();
					stacyDraw = true;
					yukoL = false;
					text = new TextBox("“I was going back to my room.", 30);
				}
				
				if (advance == 37) {
					drawHallway();
					text = new TextBox("Did something happen? You don’t look well.”", 30);
				}
				
				if (advance == 38) {
					drawHallway();
					stacyDraw = false;
					yukoL = true;
					text = new TextBox("“Oh! Um, it’s nothing.", 30);
				}
				
				if (advance == 39) {
					drawHallway();
					text = new TextBox("Just go to bed. I’ll see you in the morning.”", 30);
				}
				
				if (advance == 39) {
					drawHallway();
					stacyDraw = true;
					yukoL = false;
					text = new TextBox("“Right…”", 30);
				}
				
				if (advance == 40) {
					drawHallway();
					stacyDraw = false;
					isText = false;
				}
				
				if (advance == 41) {
					drawHallway();
					isText = true;
					yukoL = true;
					text = new TextBox("That was a close one.", 30);
				}
				
				if (advance == 42) {
					drawHallway();
					text = new TextBox("I’ll tell Stacy and Xena about what happened when everyone wakes up", 30);
				}
				
				if (advance == 43) {
					drawHallway();
					text = new TextBox("So I can investigate the two of them.", 30);
				}
				
				if (advance == 44) {
					drawHallway();
					text = new TextBox("Is Vi still in the room?", 30);
				}
				
				if (advance == 45) {
					drawHallway();
					text = new TextBox("I’ll go tell them what happened and see if they can help me investigate.", 30);
				}
				
				if (advance == 46) {
					drawPotatoRoom();
					yukoL = false;
					vi.drawVS(g);
					text = new TextBox("Yuko arrives in the potato bedroom, where Vi is sleeping", 0, 0, WIDTH, 100, 30, Color.white);
				}
				
				if (advance == 47) {
					drawPotatoRoom();
					vi.drawVS(g);
					canEnter = false;
					text = new TextBox("Shake Vi awake", 0, 0, 750, 100, 30, Color.white);
					Collider option1 = new Collider(0, 0, 750, 100);
					TextBox text2 = new TextBox("Say something", 850, 0, 750, 100, 30, Color.white);
					text2.draw(g);
					Collider option2 = new Collider(850, 0, 750, 100);
					if (option1.clicked(clicker) == true || option2.clicked(clicker) == true) {
						advance++;
					}
				}
				
				if (advance == 48) {
					drawPotatoRoom();
					viR = true;
					canEnter = true;
					text = new TextBox("“What do you want? It’s still sleeping time.”", 30);
				}
				
				if (advance == 49) {
					drawPotatoRoom();
					viR = false;
					yukoL = true;
					text = new TextBox("“There’s something you have to know, Vi.”", 30);
				}
				
				if (advance == 50) {
					drawPotatoRoom();
					viR = true;
					text = new TextBox("I saw the look on Vi’s face slowly change to one filled with horror as I told them about what happened.", 30);
				}
				
				if (advance == 51) {
					drawPotatoRoom();
					yukoL = false;
					text = new TextBox("...", 30);
				}
				
				if (advance == 52) {
					drawPotatoRoom();
					text = new TextBox(".....", 30);
				}
				
				if (advance == 53) {
					drawPotatoRoom();
					viR = false;
					yukoL = true;
					text = new TextBox("“Let’s go investigate now before the other two wake up.", 30);
				}
				
				if (advance == 54) {
					drawPotatoRoom();
					text = new TextBox("We need to find out what happened before another one of us gets killed.”", 30);
				}
				
				if (mapShow) {
					drawMap();
					isText = false;
					yukoL = false;
					canMove = true;
					canEnter = false;
					whichToDraw();
					viR = false;
				}
				
				if (lounge && !find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					canEnter = false;
					roomInc = 0;
					drawLounge();
					if (lounges[0].clicked(clicker) == true) {
						text = new TextBox("Just some computer games from the human world.", 30);
					} else if (lounges[1].clicked(clicker) == true) {
						text = new TextBox("Nothing seems off about this one to me.", 30);
					} else if (lounges[2].clicked(clicker) == true) {
						//text = new TextBox("Whoever was using this one left a lot of stuff open.", 30);
						articles = true;
					} else if (lounges[3].clicked(clicker) == true) {
						text = new TextBox("Hasn’t been sat in since bedtime.", 30);
					}
				}
				
				if (articles) {
					lounge = false;
					mapShow = false;
					canEnter = true;
					drawArticles();
					text = new TextBox("Whoever was using this one left a lot of stuff open. This article ends suddenly.", 30);
					clicker.setX(0);
					clicker.setY(0);
				}
				
				if (kitchen && !find2) {
					mapShow = false;
					canMove = false;
					drawKitchen();
					text = new TextBox("Already checked here today.", 30);
					viR = true;
					yukoL = true;
				}
				
				if (keys && !find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawHallway();
					GraphicsUtilities.drawPicture(g, "keyHolder.png", 1400, 450, 100, 150);
					text = new TextBox("The master key is missing.", 30);
				}
				
				if (bathroom && !find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawBathroom();
					bathroomObjects();
					if (bathroomObj.clicked(clicker) == true) {
						text = new TextBox("Is this Xena’s? Did she forget both of these out here?", 30);
					}
				}
				
				if (back && !find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawBack();
					text = new TextBox("Just the trash chute. Not that we’d find anything here if it was used.", 30);
					viR = true;
					yukoL = true;
				}
				
				if (girlRoom && !find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawHallway();
					text = new TextBox("“Let’s go look in other places. It’ll be too suspicious if they wake up and see us.”", 30);
					yukoL = true;
					viR = true;
				}
				
				if (potatoroom && !find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawPotatoRoom();
					text = new TextBox("“Let’s keep searching for as long as we can.”", 30);
					viR = true;
				}
				
				if (front && !find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawFront();
					text = new TextBox("I shouldn’t touch anything while the ship’s in night mode.", 30);
					viR = true;
					yukoL = true;
				}
				
				if (chickenCheck) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawMap();
					text = new TextBox("“Should we really go in? It’s after hours.”", 30);
				}
				
				if (chicken && !find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawChickenRoom();
					text = new TextBox("“Shoot! We woke up the chickens. Let’s get back to the room now, Vi. Can’t have anyone seeing us.”", 30);
				}
				
				if (advance2 == 1) {
					drawKitchen();
					canEnter = true;
					isText = false;
					yukoL = true;
				}
				
				if (advance2 == 2) {
					drawKitchen();
					canEnter = true;
					yukoL = true;
					isText = true;
					text = new TextBox("“I really don’t want to do this but I don’t know what else to do with the body…..”", 30);
				}
				
				if (advance2 == 3) {
					drawKitchen();
					canEnter = true;
					yukoL = true;
					isText = true;
					text = new TextBox("The french fries were fed to the chickens.", 30);
				}
				
				if (advance2 == 4) {
					drawKitchen();
					canEnter = true;
					yukoL = true;
					isText = true;
					text = new TextBox("Ring ring ring...", 30);
				}
				
				if (advance2 == 5) {
					drawKitchen();
					canEnter = true;
					yukoL = true;
					isText = true;
					text = new TextBox("Time for the morning meeting then.", 30);
				}
				
				if (advance2 == 6) {
					drawHallway();
					canEnter = true;
					isText = false;
					yukoL = true;
					stacyDraw = true;
				}
				
				if (advance2 == 7) {
					drawHallway();
					canEnter = true;
					isText = true;
					yukoL = true;
					stacyDraw = true;
					text = new TextBox("“So, as you noticed, Newton isn’t here…”", 30);
				}
				
				if (advance2 == 8) {
					drawHallway();
					canEnter = true;
					isText = true;
					yukoL = true;
					stacyDraw = true;
					text = new TextBox("“Last night, Newton was turned into french fries.", 30);
				}
				
				if (advance2 == 9) {
					drawHallway();
					canEnter = true;
					isText = true;
					yukoL = true;
					stacyDraw = true;
					text = new TextBox("I’m investigating the situation right now and still need to ask Xena and Stacy questions.”", 30);
				}
				
				if (advance2 == 10) {
					drawHallway();
					canEnter = true;
					isText = true;
					yukoL = false;
					stacyDraw = true;
					stacyFace = 1;
					xenaDraw = true;
					xenaFace = 3;
					text = new TextBox("", 30);
				}
				
				if (advance2 == 11) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = true;
					stacyDraw = false;
					text = new TextBox("“What do you mean… Newton was turned into french fries.", 30);
				}
				
				if (advance2 == 12) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = true;
					stacyDraw = false;
					text = new TextBox("Who would do something so horrible to such a young potato!?”", 30);
				}
				
				if (advance2 == 13) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = false;
					stacyDraw = true;
					stacyFace = 0;
					text = new TextBox("“Is this what yesterday was about?", 30);
				}
				
				if (advance2 == 14) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = false;
					stacyDraw = true;
					text = new TextBox("“Whoever it was who did it… we need to eject them.”", 30);
				}
				
				if (advance2 == 15) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = true;
					xenaFace = 2;
					stacyDraw = false;
					text = new TextBox(" “Hold on! Let’s not jump to conclusions.", 30);
				}
				
				if (advance2 == 16) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = true;
					text = new TextBox("Whoever did this might’ve had good reason to.", 30);
				}
				
				if (advance2 == 17) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = true;
					text = new TextBox("We should try to find out why they did it also.", 30);
				}
				
				if (advance2 == 18) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = true;
					text = new TextBox("But if you need anything from us, just let us know ok.", 30);
				}
				
				if (advance2 == 19) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = true;
					text = new TextBox("I really want to help out.", 30);
				}
				
				if (advance2 == 20) {
					drawHallway();
					canEnter = true;
					isText = true;
					xenaDraw = true;
					text = new TextBox("*sniffle* I just need some time to myself for a little bit.”", 30);
				}
				
				if (advance2 == 21) {
					drawHallway();
					canEnter = true;
					xenaDraw = false;
					isText = false;
				}
				
				if (advance2 == 22) {
					drawHallway();
					canEnter = true;
					isText = true;
					viR = true;
					yukoL = true;
					text = new TextBox("Vi: “You handle questioning and I’ll take care of the ship for today.”", 30);
				}
				
				if (mapShow2) {
					drawMap();
					isText = false;
					yukoL = false;
					canMove = true;
					canEnter = false;
					whichToDraw();
					viR = false;
					stacyDraw = false;
					xenaDraw = false;
				}
				
				if (lounge && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					canEnter = false;
					int q = 0;
					roomInc = 0;
					drawLounge();
					stacyDraw = true;
					if (lounges[4].clicked(clicker) == true) {
						stacyQs = true;
					} else if (lounges[0].clicked(clicker) == true || lounges[1].clicked(clicker) == true || lounges[2].clicked(clicker) == true) {
						text = new TextBox("Nothing here.", 30);
					} else {
						text = new TextBox("I'm in the lounge room.", 30);
					}
				}
				
				if (stacyQs) {
					lounge = false;
					isText = true;
					mapShow = false;
					canEnter = true;
					drawLounge();
					stacyDraw = true;
					if (roomInc == 1) {
						yukoL = true;
						isText = true;
						text = new TextBox("Was there anything suspicious last night?", 30);
					} else if (roomInc == 2) {
						yukoL = false;
						isText = true;
						text = new TextBox("“I went to sleep at the same time as everyone else, but there was one oddity.", 30);
					} else if (roomInc == 3) {
						yukoL = false;
						text = new TextBox("I forgot to close my bedroom door last night before getting ready for bed.", 30);
					} else if (roomInc == 4) {
						yukoL = false;
						text = new TextBox("When I came back, my key card was on the floor by the door. I’m sure I put it on the desk, though.”", 30);
					} else if (roomInc == 5) {
						yukoL = true;
						text = new TextBox("When did you last see Newton?", 30);
					} else if (roomInc == 6) {
						yukoL = false;
						text = new TextBox("“I saw them going into the potato room before I entered my room.”", 30);
					} else if (roomInc == 7) {
						yukoL = true;
						text = new TextBox("Were you on any of the computers today?", 30);
					} else if (roomInc == 8) {
						yukoL = true;
						text = new TextBox("“Yes. I was playing one of my computer games until you came in.", 30);
					} else if (roomInc == 9) {
						yukoL = true;
						text = new TextBox("I only logged onto that one, if that was your next question.", 30);
					} else if (roomInc == 10) {
						yukoL = true;
						text = new TextBox("I did see Xena coming out of here before our morning meeting today.”", 30);
					} else if (roomInc >= 10) {
						stacyQs = false;
						lounge = true;
					} else {
						text = new TextBox(" ", 30);
					}
					clicker.setX(0);
					clicker.setY(0);
				}
				
				if (kitchen && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawKitchen();
					text = new TextBox("I cleaned everything earlier, so nothing new to take note of.", 30);
				}
				
				if (keys && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawHallway();
					GraphicsUtilities.drawPicture(g, "keyHolder.png", 1400, 450, 100, 150);
					text = new TextBox("The master key is still missing.", 30);
				}
				
				if (bathroom && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawBathroom();
					text = new TextBox("Is something missing from here?", 30);
				}
				
				if (back && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					canEnter = false;
					roomInc = 0;
					drawBack();
					xenaDraw = true;
					Collider xenaCollide = new Collider(0, 100, 350, 700);
					if (xenaCollide.clicked(clicker) == true) {
						xenaQs = true;
					} else {
						text = new TextBox("I'm in the back of the spaceship.", 30);
					}
				}
				
				if (xenaQs) {
					back = false;
					isText = true;
					mapShow = false;
					canEnter = true;
					drawBack();
					xenaDraw = true;
					xenaFace = 2;
					if (roomInc == 1) {
						yukoR = true;
						isText = true;
						text = new TextBox("When did you last see Newton?", 30);
					} else if (roomInc == 2) {
						yukoR = false;
						isText = true;
						text = new TextBox("“Oh me? Probably last night.", 30);
					} else if (roomInc == 3) {
						yukoR = false;
						text = new TextBox("I saw Stacy go into her room, then I saw Newton go into theirs.”", 30);
					} else if (roomInc == 4) {
						yukoR = true;
						text = new TextBox("When did you wake up?", 30);
					} else if (roomInc == 5) {
						yukoR = false;
						text = new TextBox("“I woke up at around the same time as everyone else then went straight to the morning meeting.", 30);
					} else if (roomInc == 6) {
						yukoR = false;
						text = new TextBox("Still kinda shocked that such a thing would happen.”", 30);
					} else if (roomInc == 7) {
						yukoR = true;
						text = new TextBox("Did you see anything suspicious?", 30);
					} else if (roomInc == 8) {
						yukoR = false;
						text = new TextBox("“Nothing too out of the ordinary…”", 30);
					} else if (roomInc >= 10) {
						xenaQs = false;
						back = true;
					} else {
						text = new TextBox(" ", 30);
					}
					clicker.setX(0);
					clicker.setY(0);
				}
				
				if (girlRoom && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawHallway();
					text = new TextBox("No, I should go look somewhere else.", 30);
					yukoL = true;
				}
				
				if (potatoroom && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawPotatoRoom();
					text = new TextBox("Now isn’t the time to rest and let my guard down.", 30);
					yukoL = true;
				}
				
				if (front && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					canEnter = true;
					drawFront();
					text = new TextBox("Am I ready to call everyone? Do I have enough evidence to make a decision?", 30);
					yukoL = true;
				}
				
				if (chicken && find2) {
					mapShow = false;
					isText = true;
					canMove = false;
					drawChickenRoom();
					viL = true;
					text = new TextBox("“Nothing to see here. I was just taking care of the chickens.”", 30);
				}
				
				if (ending == 1) {
					drawHallway();
					canEnter = true;
					isText = true;
					yukoL = true;
					stacyDraw = true;
					text = new TextBox(" ", 30);
				}
				
				if (ending == 2) {
					drawHallway();
					canEnter = true;
					isText = true;
					yukoL = true;
					stacyDraw = true;
					text = new TextBox("“I’ve found it! I know who murdered Newton!”", 30);
				}
				
				if (ending == 3) {
					drawHallway();
					canEnter = false;
					text = new TextBox("It's Stacy!", 0, 0, 750, 100, 30, Color.white);
					Collider option1 = new Collider(0, 0, 750, 100);
					TextBox text2 = new TextBox("It's Xena!", 850, 0, 750, 100, 30, Color.white);
					text2.draw(g);
					Collider option2 = new Collider(850, 0, 750, 100);
					if (option1.clicked(clicker) == true) {
						badEnd = 1;
						endB = true;
					} else if (option2.clicked(clicker) == true) {
						goodEnd = 1;
						endB = true;
					}
					ChangeSong(theme2);
				}
				
				if (goodEnd == 1) {
					drawHallway();
					canEnter = true;
					isText = true;
					yukoR = true;
					stacyDraw = false;
					text = new TextBox("“It's Xena!”", 30);
				}
				
				if (goodEnd == 2) {
					drawHallway();
					isText = true;
					canEnter = true;
					yukoL = false;
					xenaDraw = true;
					text = new TextBox("“Wait! Wait! Don’t you think you’re jumping to conclusions kind of quickly here?", 30);
				}
				
				if (goodEnd == 3) {
					drawHallway();
					isText = true;
					canEnter = true;
					text = new TextBox("It hasn’t even been a full day of investigating!”", 30);
				}
				
				if (goodEnd == 4) {
					drawHallway();
					canEnter = true;
					xenaDraw = false;
					yukoR = true;
					text = new TextBox("“No. It has to be you.", 30);
				}
				
				if (goodEnd == 5) {
					drawHallway();
					text = new TextBox("This hair clip I found by the deep fryer is yours, isn’t it?”", 30);
				}
				
				if (goodEnd == 6) {
					drawHallway();
					xenaDraw = true;
					yukoR = false;
					text = new TextBox("“How do you even know that it’s a human in the first place?”", 30);
				}
				
				if (goodEnd == 7) {
					drawHallway();
					xenaDraw = false;
					yukoR = true;
					text = new TextBox("“Only humans have hair…”", 30);
				}
				
				if (goodEnd == 8) {
					drawHallway();
					text = new TextBox("“There’s no denying it. All of the evidence points to you.”", 30);
				}
				
				if (goodEnd == 9) {
					drawHallway();
					xenaFace = 1;
					xenaDraw = true;
					yukoR = false;
					text = new TextBox("...", 30);
				}
				
				if (goodEnd == 10) {
					drawHallway();
					text = new TextBox("“So you’ve caught me...", 30);
				}
				
				if (goodEnd == 11) {
					drawHallway();
					text = new TextBox("What are you going to do about it? Eject me?”", 30);
				}
				
				if (goodEnd == 12) {
					drawHallway();
					text = new TextBox("“I only got one of you but there are more potato farmers…", 30);
				}
				
				if (goodEnd == 13) {
					drawHallway();
					text = new TextBox("they’re coming for all of you”", 30);
				}
				
				if (goodEnd == 14) {
					drawHallway();
					xenaDraw = false;
					yukoR = true;
					text = new TextBox("“You betrayed all of us… we trusted you”", 30);
				}
				
				if (goodEnd == 15) {
					drawHallway();
					xenaDraw = true;
					yukoR = false;
					text = new TextBox("“And the potato farmers trusted all of you.”", 30);
				}
				
				if (goodEnd == 16) {
					drawBack();
					xenaDraw = true;
					yukoR = true;
					text = new TextBox("With that, she refused to speak anymore as we ejected her through the trash chute.", 30);
				}
				
				if (goodEnd == 17) {
					drawBack();
					xenaDraw = false;
					text = new TextBox("There were two less than our trip had originally planned for, but we eventually made it to the potato planet safely.", 30);
				}
				
				if (goodEnd >= 18) {
					yukoR = false;
					drawGoodEnd();
					isText = false;
					xenaDraw = false;
				}
				
				if (badEnd == 1) {
					drawHallway();
					canEnter = true;
					isText = true;
					yukoL = true;
					stacyDraw = true;
					text = new TextBox("“It's Stacy!”", 30);
				}
				
				if (badEnd == 2) {
					drawHallway();
					isText = true;
					canEnter = true;
					stacyFace = 1;
					text = new TextBox(" ", 30);
				}
				
				if (badEnd == 3) {
					drawHallway();
					isText = true;
					canEnter = true;
					text = new TextBox("...", 30);
				}
				
				if (badEnd == 4) {
					drawHallway();
					canEnter = true;
					stacyFace = 0;
					text = new TextBox("“You’ve got the wrong suspect, Yuko.”", 30);
				}
				
				if (badEnd == 5) {
					drawHallway();
					stacyDraw = false;
					viR = true;
					text = new TextBox("“Wait, Yuko! Are you sure about this?", 30);
				}
				
				if (badEnd == 6) {
					drawHallway();
					viR = true;
					text = new TextBox("Where’s your evidence?”", 30);
				}
				
				if (badEnd == 7) {
					drawHallway();
					viR = false;
					text = new TextBox("“Stacy’s been quiet since the beginning and there were lots of things out of place!”", 30);
				}
				
				if (badEnd == 8) {
					drawHallway();
					yukoL = false;
					xenaDraw = true;
					xenaFace = 2;
					text = new TextBox(" ", 30);
				}
				
				if (badEnd == 9) {
					drawHallway();
					text = new TextBox("“Stacy, you’ve been lying to us this whole time?", 30);
				}
				
				if (badEnd == 10) {
					drawHallway();
					text = new TextBox("But why would you do it?”", 30);
				}
				
				if (badEnd == 11) {
					drawHallway();
					xenaDraw = false;
					viL = true;
					text = new TextBox("“Stacy, if you have nothing to say then maybe it was you…”", 30);
				}
				
				if (badEnd == 12) {
					drawHallway();
					stacyDraw = true;
					text = new TextBox("...", 30);
				}
				
				if (badEnd == 13) {
					drawHallway();
					stacyDraw = false;
					viL = false;
					yukoL = true;
					text = new TextBox("As much as it hurt all of us comrades, we did the only thing we knew we could do.", 30);
				}
				
				if (badEnd == 14) {
					drawBack();
					yukoL = false;
					stacyDraw = true;
					text = new TextBox("We took her to the trash chute, pushed her in, and said goodbye forever.", 30);
				}
				
				if (badEnd == 15) {
					drawBack();
					stacyDraw = false;
					text = new TextBox("We hoped that we’d finally avenged our potato friend but perhaps, we were gravely mistaken.", 30);
				}
				
				if (badEnd == 16) {
					drawBack();
					xenaDraw = true;
					xenaFace = 1;
					text = new TextBox(" ", 30);
				}
				
				if (badEnd >= 17) {
					drawBadEnd();
					isText = false;
					xenaDraw = false;
				}


				if (text != null && isText == true) {
					text.draw(g);
				}
				
				if (yukoR == true) yuko.drawYR(g); else if (yukoL == true) yuko.drawYL(g);
				if (viR == true) vi.drawVR(g); else if (viL == true) vi.drawVL(g);
				if (xenaDraw == true) {
					if (xenaFace == 0) {
						xena.drawXReg(g);
					} else if (xenaFace == 1) {
						xena.drawXEvil(g);
					} else if (xenaFace == 2) {
						xena.drawXSad(g);
					} else if (xenaFace == 3) {
						xena.drawXShock(g);
					}
				}
				if (stacyDraw == true) {
					if (stacyFace == 0) {
						stacy.drawSReg(g);
					} else if (stacyFace == 1) {
						stacy.drawSShock(g);
					} else if (stacyFace == 2) {
						stacy.drawSSmile(g);
					}
				}
				
				// System.out.println("" + endingAdvance);
				// System.out.println(advance);
				time++;
				if (time % 20 == 0) {
					longTime++;
				}
				repaint();
				
				clicker.draw(g);

			}
		}

	}

	public void collision(int dir) {
		boolean collided = false;
		
		for (int i = 0; i < map.getWalls().length; i++) {
			if (map.getWalls()[i].inBumper(yukoCheck) == true) {
				collided = true;
			}
		}
		
		if (map.getLoungeEnter().inBumper(yukoCheck) == true) {
			lounge = true;
			mapShow = false;
		} else if (map.getKitchenEnter().inBumper(yukoCheck) == true) {
			kitchen = true;
			mapShow = false;
		} else if (map.getChickenEnter().inBumper(yukoCheck) == true) {
			chickenCheck = true;
			mapShow = false;
		} else if (map.getXenaEnter().inBumper(yukoCheck) == true) {
			girlRoom = true;
			yukoSprite.setY(yukoSprite.getY() - 20);
			mapShow = false;
		} else if (map.getStacyEnter().inBumper(yukoCheck) == true) {
			girlRoom = true;
			yukoSprite.setY(yukoSprite.getY() - 20);
			mapShow = false;
		} else if (map.getKeyEnter().inBumper(yukoCheck) == true) {
			keys = true;
			mapShow = false;
		} else if (map.getBathroomEnter().inBumper(yukoCheck) == true) {
			bathroom = true;
			yukoSprite.setY(yukoSprite.getY() + 20);
			mapShow = false;
		} else if (map.getPotatoEnter().inBumper(yukoCheck) == true) {
			potatoroom = true;
			mapShow = false;
		} else if (map.getFrontEnter().inBumper(yukoCheck) == true) {
			front = true;
			yukoSprite.setY(yukoSprite.getY() - 20);
			mapShow = false;
		} else if (map.getBackEnter().inBumper(yukoCheck) == true) {
			back = true;
			yukoSprite.setY(yukoSprite.getY() + 20);
			mapShow = false;
		}
		
		roomInc = 0;
		
		if (collided) {
			if (dir == 0) {
				yukoSprite.setY(yukoSprite.getY() + 20);
			} else if (dir == 1) {
				yukoSprite.setY(yukoSprite.getY() - 20);
			} else if (dir == 2) {
				yukoSprite.setX(yukoSprite.getX() + 10);
			} else {
				yukoSprite.setX(yukoSprite.getX() - 10);
			}
			yukoCheck.setY(yukoSprite.getY() + 20);
			yukoCheck.setX(yukoSprite.getX() + 20);
			collided = false;
		}
		
		yukoCheck.setY(yukoSprite.getY() + 20);
		yukoCheck.setX(yukoSprite.getX() + 20);
		
	}

	public void whichToDraw() {
		yukoCheck.draw(g);
		if (whichFace == 3 && yukoStep % 2 == 1) {
			yukoSprite.drawSL(g);
		} else if (whichFace == 3 && yukoStep % 2 == 0) {
			yukoSprite.drawSR(g);
		} else if (whichFace == 2 && yukoStep % 2 == 1) {
			yukoSprite.drawSL(g);
		} else if (whichFace == 2 && yukoStep % 2 == 0) {
			yukoSprite.drawSR(g);
		} else if (whichFace == 1 && yukoStep % 2 == 1) {
			yukoSprite.drawBL(g);
		} else if (whichFace == 1 && yukoStep % 2 == 0) {
			yukoSprite.drawBR(g);
		} else if (whichFace == 4 && yukoStep % 2 == 1) {
			yukoSprite.drawFR(g);
		} else {
			yukoSprite.drawFL(g);
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args) {

		// frame settings
		JFrame frame = new JFrame("marbles...");
		frame.setSize(WIDTH + 18, HEIGHT + 47);
		frame.setLocation(150, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Game());
		frame.setVisible(true);

		ImageIcon img = new ImageIcon("C:\\Users\\813697\\ITCS\\ACLHackathon\\icon.png");
		frame.setIconImage(img.getImage());

		PlaySound();
	}

}
