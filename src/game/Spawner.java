package game;

import java.util.Random;

public class Spawner {
	
	public int curTime, targetTime = 60*1;
	public Random random = new Random();
	
	public void tick() {
		curTime++;
		if(curTime == targetTime) {
			curTime = 0;
			if(random.nextInt(100) < 50) {
				Game.crabs.add(new Crab(random.nextInt(Game.WIDTH - 16), 0));
			} else {
				Game.crabs.add(new Crab(random.nextInt(Game.WIDTH - 16), Game.HEIGHT-16));
			}
		}
	}
}
