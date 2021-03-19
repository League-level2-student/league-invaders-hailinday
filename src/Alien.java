import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {
	Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
	}
	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
	}
	void update() {
		y+=speed;
	}
	
}
