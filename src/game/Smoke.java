package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Smoke {
	public int x, y;
	public static BufferedImage[] sprite;
	
	public int curFrames, maxFrames = 10, index, mIndex = 2, loop;
	
	public Smoke(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = new BufferedImage[3];
		sprite[0] = Game.spritesheet.getSprite(32, 0);
		sprite[1] = Game.spritesheet.getSprite(48, 0);
		sprite[1] = Game.spritesheet.getSprite(64, 0);
	}
	
	public void tick() {
		curFrames++;
		if(curFrames == maxFrames) {
			curFrames = 0;
			index++;
			if(index > mIndex) {
				index = 0;
				Game.smokes.remove(this);
				return;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite[index], (int) x, (int) y, 40, 40, null);
	}
}
