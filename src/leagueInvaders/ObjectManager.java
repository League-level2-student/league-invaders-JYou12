package leagueInvaders;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	
	Rocketship rocket;
	ArrayList<Projectile> pro = new ArrayList<Projectile>();
	
	ObjectManager(Rocketship rocket){
		this.rocket = rocket;
	}
	
	void addProjectile(Projectile projectile) {
		
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
		}
		
		for (int i = 0; i < pro.size(); i++) {
			pro.get(i).update();
			if(pro.get(i).y > LeagueInvaders.frame.getHeight()) {
				pro.get(i).isActive = false;
			}
		}
		
	}
	
	void draw(Graphics g) {
		
		rocket.draw(g);
		
		for(int i= 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
			pro.get(i).draw(g);
		}
		
	}
	
	void purgeObjects() {
		
		int x = aliens.size();
		while(aliens.get(x).isActive == true) {
			if(aliens.get(x).isActive == false) {
				aliens.remove(x);
			}
			x--;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		addAlien();
		
	}
	
}
