package com.infonova.education.pacman.thread;

import com.infonova.education.pacman.Enemy;
import com.infonova.education.pacman.Level;

public class MoveGhostThread extends Thread {

    private Level level;

	public MoveGhostThread(Level level) {
		this.level = level;
	}

	@Override
	public void run() {

        while (this.isAlive()) {

            for (Enemy enemy : level.getEnemies()) {
                if (enemy.isAlive()) {
                    enemy.move(level);
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // do nothing
            }

        }


	}

}
