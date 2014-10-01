package com.infonova.education.pacman.thread;

import com.infonova.education.pacman.Enemy;
import com.infonova.education.pacman.GamePanel;
import com.infonova.education.pacman.Level;
import com.infonova.education.pacman.observer.EndGameObserver;
import com.infonova.education.pacman.observer.GameObjectCollisionObserver;
import com.infonova.education.pacman.observer.PointObserver;

import java.util.List;

public class CreateGhostThread extends Thread {
	private Level level;
    private final GamePanel panel;
	private static final int MAX_ENEMIES = 4;

	public CreateGhostThread(Level level, GamePanel panel) {
		this.level = level;
        this.panel = panel;
	}

	@Override
	public void run() {
		List<Enemy> enemies = level.getEnemies();
		while (enemies.size() < MAX_ENEMIES) {
            Enemy enemy = new Enemy(level.getMaxX() - 1, level.getMaxY() - 1);

            enemy.addObserver(new EndGameObserver(level, panel));
            enemy.addObserver(new GameObjectCollisionObserver(level, panel));
            enemy.addObserver(new PointObserver(level, panel));

			enemies.add(enemy);
			level.setEnemies(enemies);

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
		}
	}
}
