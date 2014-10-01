package com.infonova.education.pacman.observer;

import com.infonova.education.pacman.*;

import java.util.Observable;
import java.util.Observer;

public class PointObserver implements Observer {

    private final Level level;
    private final GamePanel gamePanel;
    private int oldX, oldY;

    public PointObserver(Level level, GamePanel gamePanel) {
        this.level = level;
        this.gamePanel = gamePanel;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!(o instanceof Hero)) {
            return;
        }

        GameObject gameObject = (GameObject) o;

        int newX = gameObject.getX();
        int newY = gameObject.getY();

        Hero hero = level.getHero();

        BackgroundElement backgroundElement = level.getBg(newX, newY);
        BackgroundType backgroundType = backgroundElement.getType();

        if (backgroundType == BackgroundType.DOT || backgroundType == BackgroundType.SUPERDOT) {
            hero.setPoints(hero.getPoints() + 1);
            level.increaseScore(1);
            BackgroundElement bg = level.getBg(newX, newY);
            bg.setType(BackgroundType.FREE);
            level.addBg(bg);

            if (backgroundType == BackgroundType.SUPERDOT) {
                hero.startSupermode();
            }
        }
        if (backgroundType == BackgroundType.WALL) {
            hero.setX(this.oldX);
            hero.setY(this.oldY);
        } else {
            this.oldX = newX;
            this.oldY = newY;
        }
      }



}
