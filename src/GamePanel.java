import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    final int INST = 3;
    int currentState = MENU;
    Font titleFont;
    Font titleFont1;
    Font titleFont2;
    Timer frameDraw;
    Timer alienSpawn;
    Rocketship rocket = new Rocketship(250,700,50,50);
    ObjectManager manage = new ObjectManager(rocket);
    public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
    GamePanel() {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	titleFont1 = new Font("Arial", Font.PLAIN, 26);
    	titleFont2 = new Font("Arial", Font.PLAIN, 26);
    	frameDraw = new Timer(1000/60,this);
        frameDraw.start();
        if (needImage) {
            loadImage ("space.png");
        }
    }
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		} else if(currentState == INST) {
			drawInstructionState(g);
		}
	}
	void startGame() {
		 alienSpawn = new Timer(1000 , manage);
		 alienSpawn.start();
	}
	void updateMenuState() {
    }
    void updateGameState() {
    	manage.update();
    	if (rocket.isActive==false) {
			currentState = END;
		}
    }
    void updateEndState() {
    	manage.getScore();
    }
    void drawMenuState(Graphics g) {
    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("League Invaders", 50, 100);
    	g.setFont(titleFont1);
    	g.setColor(Color.YELLOW);
    	g.drawString("Press ENTER to start", 100, 400);
    	g.setFont(titleFont2);
    	g.setColor(Color.YELLOW);
    	g.drawString("Press SPACE for instructions", 75, 600);
    }
    void drawGameState(Graphics g) {
    	if (gotImage) {
    		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
    	} else {
    		g.setColor(Color.BLACK);
        	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	}
    	manage.draw(g);
    }
    void drawEndState(Graphics g)  {
    	g.setColor(Color.RED);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("Game Over", 50, 100);
    	g.setFont(titleFont1);
    	g.setColor(Color.YELLOW);
    	g.drawString("You killed enemies", 100, 400);
    	g.setFont(titleFont2);
    	g.setColor(Color.YELLOW);
    	g.drawString("Press ENTER to restart", 75, 600);
    	g.drawString("Score:" + manage.getScore(), 75, 650);
    }
    void drawInstructionState(Graphics g) {
    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("League Invaders", 50, 100);
    	g.setFont(titleFont1);
    	g.setColor(Color.YELLOW);
    	g.drawString("Press WASD to move", 100, 400);
    	g.drawString("and Space to shoot", 100, 450);
    	g.setFont(titleFont2);
    	g.setColor(Color.RED);
    	g.drawString("Click SPACE for the menu", 75, 600);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				currentState = INST;
			}
		} else if (currentState == INST) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				System.out.println("Space");
				currentState = MENU;
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (currentState == END) {
		    	alienSpawn.stop();
		        currentState = MENU;
		        rocket = new Rocketship(250,700,50,50);
				manage = new ObjectManager(rocket);
		    } else if(currentState == MENU) {
		    	startGame();
		    	currentState++;
		    } else {
		        currentState++;
		    }
		}
		if (currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    rocket.up();
			    if (rocket.y <= 0) {
					rocket.y = 0;
				}
			} else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			    rocket.down();
			    if (rocket.y >= LeagueInvaders.HEIGHT-rocket.height-25) {
					rocket.y = LeagueInvaders.HEIGHT-rocket.height-25;
				}
			} else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    rocket.left();
			    if (rocket.x <= 0) {
					rocket.x = 0;
				}
			} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			    rocket.right();
			    if (rocket.x >= LeagueInvaders.WIDTH-rocket.width) {
					rocket.x = LeagueInvaders.WIDTH-rocket.width;
				}			
			} else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				manage.addProjectile(rocket.getProjectile());
			}
		}
	}
	@Override
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
