package leagueInvaders;

import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
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
	
}
