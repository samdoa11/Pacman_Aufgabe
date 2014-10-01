package com.infonova.education.pacman.observer;

import com.infonova.education.pacman.GamePanel;
import com.infonova.education.pacman.Hero;
import com.infonova.education.pacman.Level;

import java.util.Observable;
import java.util.Observer;

public class EndGameObserver implements Observer {

    private final Level level;
    private final GamePanel gamePanel;

    public EndGameObserver(Level level, GamePanel gamePanel) {
        this.level = level;
        this.gamePanel = gamePanel;
    }

    @Override
    public void update(Observable o, Object arg) {

        Hero hero = level.getHero();

        if (level.getPoints() == hero.getPoints()) {
            gamePanel.endGame();
            gamePanel.update(gamePanel.getGraphics());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            gamePanel.nextLevel();
        }




    }
}
