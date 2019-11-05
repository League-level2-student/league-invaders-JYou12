package leagueInvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	final int menu = 0;
	final int game = 1;
	final int end = 2;
	
	int currentState = menu;
	
	Timer frameDraw;;
	Font titleFont;
	Font textFont;
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	
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
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.frame.getWidth(), LeagueInvaders.frame.getHeight());
		
		rocket.draw(g);
	}
	
	void drawEndState(Graphics g)  {
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.frame.getWidth(), LeagueInvaders.frame.getHeight());
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 115, 400);
		
	}
	
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 40);
		textFont = new Font("Arial", Font.PLAIN, 24);
		
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		
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
				currentState = menu;
			}else {
				currentState ++;
			}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    rocket.up();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.down();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			rocket.left();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.right();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
