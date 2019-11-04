package leagueInvaders;
import javax.swing.JFrame;

public class LeagueInvaders {
	static JFrame frame;
	GamePanel GP;

	public static void main(String[] args) {
		
		LeagueInvaders LI = new LeagueInvaders();
		LI.setup();
		
	}

	void setup() {
		frame.add(GP);
		frame.setSize(500, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(GP);
	}
	
	LeagueInvaders(){
		frame = new JFrame();
		GP = new GamePanel();
	}
	
}
