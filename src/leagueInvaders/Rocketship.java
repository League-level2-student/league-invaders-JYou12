package leagueInvaders;

import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject{

	Graphics g;
	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void draw() {

		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	
	}
	
}
