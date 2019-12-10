package leagueInvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.soap.Text;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = true;
	
	public static boolean peirce = false;
	
	final int menu = 0;
	final int game = 1;
	final int end = 2;
	
	int currentState = menu;
	
	Timer frameDraw;
	Timer alienSpawn;
	Font titleFont;
	Font textFont;
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	ObjectManager objManager = new ObjectManager(rocket);
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState == menu) {
			drawMenuState(g);
		}else if(currentState == game) {
			drawGameState(g);
		}else if(currentState == end) {
			drawEndState(g);
		}
	}
	
	void updateMenuState(){
		
	}
	
	void updateGameState() {
	
		objManager.update();
		if(!rocket.isActive) {
			currentState++;
		}
	}
	
	void updateEndState() {
		
	}
	
	void drawMenuState(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.frame.getWidth(), LeagueInvaders.frame.getHeight());
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 55, 100);
		
		g.setFont(textFont);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to start", 125, 400);

		g.drawString("Press SPACE for instructions", 90, 700);

	}
	
	void drawGameState(Graphics g) {
		
		
		if(gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.frame.getWidth(), LeagueInvaders.frame.getHeight(), null);
		}else {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.frame.getWidth(), LeagueInvaders.frame.getHeight());
		}
		objManager.draw(g);
		
		g.setFont(textFont);
		g.setColor(Color.WHITE);
		String str = "Points: " + objManager.score;
		g.drawString(str, 50, 50);
		
		
	}
	
	void drawEndState(Graphics g)  {
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.frame.getWidth(), LeagueInvaders.frame.getHeight());
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 115, 400);
		endGame();
		
	}
	
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 40);
		textFont = new Font("Arial", Font.PLAIN, 24);
		
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		
		if(needImage) {
			loadImage("space.png");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(currentState == menu) {
			updateMenuState();
		}else if(currentState == game) {
			updateGameState();
		}else if(currentState == end) {
			updateEndState();
		}

		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(currentState == end) {
				rocket = new Rocketship(250, 750, 50, 50);
				objManager = new ObjectManager(rocket);
				alienSpawn.stop();
				currentState = menu;
			}else {
				currentState ++;
				startGame();
			}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_UP&&rocket.y > 0) {
		    rocket.up();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN&&rocket.y<720) {
			rocket.down();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT&&rocket.x>WIDTH) {
			rocket.left();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT&&rocket.x<450) {
			rocket.right();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			objManager.addProjectile(rocket.getProjectile());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int keyCode = arg0.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_UP: 
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			rocket.xSpeed = 0;
			rocket.ySpeed = 0;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}

	void startGame(){
		
		rocket.isActive = true;
		alienSpawn = new Timer(1000, objManager);
		alienSpawn.start();
		
	}
	
	void endGame() {
		
		objManager.purgeObjects();
		alienSpawn.stop();
	}
	
}
