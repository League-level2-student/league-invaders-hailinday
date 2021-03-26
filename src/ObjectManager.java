import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	ArrayList<Integer> projectiles = new ArrayList<Integer>();
	ArrayList<Integer> aliens = new ArrayList<Integer>();
	Random random = new Random();
	ObjectManager(Rocketship rocket){
		this.rocket = rocket;
	}
	void addProjectile(Integer x) {
		projectiles.add(x);
	}
	void addAliens() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	void update() {
		for (int i = 0; i < aliens.size(); i++) {
			aliens[i].update();
			if (aliens[i].getY >= LeagueInvaders.HEIGHT) {
				aliens[i].isActive(false);
			}
		}
	}
}
