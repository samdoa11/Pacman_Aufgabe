package com.infonova.education.pacman.event;

import com.infonova.education.pacman.Hero;
import com.infonova.education.pacman.Level;
import com.infonova.education.pacman.UserAction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyListener extends KeyAdapter {

    private final Hero hero;
    private final Level level;

    public GameKeyListener(Level level) {
        this.hero = level.getHero();
        this.level = level;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.move(level, UserAction.UP);
                break;
            case KeyEvent.VK_DOWN:
                hero.move(level, UserAction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                hero.move(level, UserAction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                hero.move(level, UserAction.RIGHT);
                break;
        }

    }

}
