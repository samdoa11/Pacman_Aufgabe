package com.infonova.education.pacman;

import com.infonova.education.pacman.event.GameKeyListener;
import com.infonova.education.pacman.observer.EndGameObserver;
import com.infonova.education.pacman.observer.GameObjectCollisionObserver;
import com.infonova.education.pacman.observer.PointObserver;
import com.infonova.education.pacman.support.LevelFactory;

import javax.swing.*;


public class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private String[] levels = {"level1.txt", "level2.txt", "level3.txt", "level4.txt"};

    private int currentLevel = 0;
    private GamePanel panel = null;

    public GameFrame() {
        startLevel();
    }

    public void nextLevel() {
        if (currentLevel + 1 < levels.length) {
            currentLevel++;
            startLevel();
        }
    }

    public void startLevel() {

        if (panel != null) {
            this.getContentPane().remove(panel);
        }

        Level level = LevelFactory.createLevel(levels[this.currentLevel]);

        panel = new GamePanel(level, this);

        Hero hero = level.getHero();
        hero.addObserver(new EndGameObserver(level, panel));
        hero.addObserver(new GameObjectCollisionObserver(level, panel));
        hero.addObserver(new PointObserver(level, panel));

        this.addKeyListener(new GameKeyListener(level));
        this.setFocusable(true);

        this.getContentPane().add(panel);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


}
