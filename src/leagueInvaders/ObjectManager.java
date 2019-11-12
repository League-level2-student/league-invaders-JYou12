package leagueInvaders;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	
	int score = 0;
	
	Rocketship rocket;
	ArrayList<Projectile> pro = new ArrayList<Projectile>();
	
	ObjectManager(Rocketship rocket){
		this.rocket = rocket;
	}
	
	void addProjectile(Projectile projectile) {
		pro.add(projectile);
	}
	
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.frame.getWidth()),0,50,50));
	}

	void update() {
		
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if(aliens.get(i).y > LeagueInvaders.frame.getHeight()) {
				aliens.get(i).isActive = false;
			}
			if(aliens.get(i).isActive) {
				aliens.get(i).update();
			}
		}
		
		for (int i = 0; i < pro.size(); i++) {
			pro.get(i).update();
			if(pro.get(i).y > LeagueInvaders.frame.getHeight()) {
				pro.get(i).isActive = false;
			}
			if(pro.get(i).isActive) {
				pro.get(i).update();
			}
		}
		
		checkCollision();
		purgeObjects();
		rocket.update();
		
	}
	
	void draw(Graphics g) {
		
		rocket.draw(g);
		
		for(int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		
		for(int i = 0; i < pro.size(); i++) {
			pro.get(i).draw(g);
		}

		
	}
	
	void purgeObjects() {
		
		/*
		 * for(int i = 0; i <= aliens.size() - 1; i++){
			if(aliens.get(i).isActive == false) {
				aliens.remove(i);
				System.out.println("Removed");
			}
		}
		 */
		
		int x = aliens.size() - 1;
		while(x >= 0) {
			if(aliens.get(x).isActive == false) {
				aliens.remove(x);
				System.out.println("Removed");
			}
			x--;
		}
		
		int s = pro.size() - 1;
		while(s >= 0) {
			if(pro.get(s).isActive == false) {
				pro.remove(s);
				System.out.println("Removed");
			}
			s--;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		addAlien();
		
	}
	
	void checkCollision() {
		
		for(int i = aliens.size() - 1; i >= 0; i--) {
			for(int e = pro.size() - 1; e >= 0; e--) {
			if(aliens.get(i).collisionBox.intersects(pro.get(e).collisionBox)) {
				aliens.get(i).isActive = false;
				score++;
				System.out.println(""+score);
				if(!GamePanel.peirce) {
					pro.get(e).isActive = false;
				}
			}
		}
		}
	}
	
	public int getScore() {
		return score;
	}
	
}
