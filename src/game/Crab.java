package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crab {
	
	public double x, y, dx, dy, speed = 4;
	public static BufferedImage[] sprite;
	
	public int curFrames, maxFrames = 10, index, mIndex = 1;
	
	public Crab(int x, int y) {
		this.x = x;
		this.y = y;
		double radius = Math.atan2((Game.HEIGHT/2-20) - y, (Game.WIDTH/2 - 20) - x);
		dx = Math.cos(radius);
		dy = Math.sin(radius);
		sprite = new BufferedImage[2];
		sprite[0] = Game.spritesheet.getSprite(0, 0);
		sprite[1] = Game.spritesheet.getSprite(16, 0);
	}
	
	public void tick() {
		x+=dx*speed;
		y+=dy*speed;
		
		if(new Rectangle((int) x, (int) y).intersects(Game.maskBuraco)) {
			Game.crabs.remove(this);
			return;
		}
		
		curFrames++;
		if(curFrames == maxFrames) {
			curFrames = 0;
			index++;
			if(index > mIndex) {
				index = 0;
			}
		}
		
		verificaColisao();
	}
	
	private void verificaColisao() {
		if(Game.isPressed) {
			Game.isPressed = false;
			if(Game.mx >= x && Game.mx <= x+40) {
				if(Game.my >= y && Game.my <= y+40) {
					Game.score++;
					Game.smokes.add(new Smoke((int) x, (int) y));
					Game.crabs.remove(this);
					return;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(sprite[index], (int) x, (int) y, 40, 40, null);
//		g.setColor(Color.red);
//		g.fillRect((int) x, (int) y, 16, 16);
	}
}
