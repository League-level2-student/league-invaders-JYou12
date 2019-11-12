package leagueInvaders;

import java.awt.Rectangle;

public class GameObject {

	int x;
	int y;
	int width;
	int height;
	
	Rectangle collisionBox = new Rectangle();
	
	int speed = 0;
	boolean isActive = true;
	
	GameObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	void update(){
        collisionBox.setBounds(x, y, width, height);
	}
	
}
