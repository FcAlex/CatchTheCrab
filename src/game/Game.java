package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener{
	
	public static int WIDTH = 480; 
	public static int HEIGHT = 480;
	public static List<Crab> crabs;
	public static List<Smoke> smokes;
	public static Spawner spawner;
	public static Spritesheet spritesheet;
	public static int mx, my, score;
	public static boolean isPressed;
	public static Rectangle maskBuraco;
	
	
	public BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Game() {
		addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		spritesheet = new Spritesheet("/spritesheet.png");
		crabs = new ArrayList<Crab>();
		smokes = new ArrayList<Smoke>();
		spawner = new Spawner();
		maskBuraco = new Rectangle(WIDTH/2-25, HEIGHT/2-25, 50, 50);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle("Catch the Crab");
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		g.setColor(Color.decode("#fff566"));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillOval(WIDTH/2-25, HEIGHT/2-25, 50, 50);
		
		for (int i = 0; i < crabs.size(); i++) {
			crabs.get(i).render(g);
		}
		for (int i = 0; i < smokes.size(); i++) {
			smokes.get(i).render(g);
		}

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString("Score:"+score, 10, 20);
		
		bs.show();
	}
	
	public void tick() {
		spawner.tick();
		for (int i = 0; i < crabs.size(); i++) {
			crabs.get(i).tick();
		}
		for (int i = 0; i < smokes.size(); i++) {
			smokes.get(i).tick();
		}
	}

	@Override
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep((int) (1000/60.0));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isPressed = true;
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
