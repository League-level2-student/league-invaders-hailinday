
public class Rocketship extends GameObject {
	super(x,y,width,height);
	void draw(g) {
		g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
	}
}
