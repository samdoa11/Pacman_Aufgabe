package com.infonova.education.pacman;

import com.infonova.education.pacman.strategy.EnemyMovable;
import com.infonova.education.pacman.strategy.Movable;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Enemy extends MovableGameObject {

    private Movable movable = new EnemyMovable();

    private final ImageIcon image;

    {
        URL url = ClassLoader.getSystemResource("images/enemy.png");
        image = new ImageIcon(url);
    }

    private boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Enemy(int x, int y) {
        super(x, y);
    }

    public Image getImage() {
        return image.getImage();
    }

    public void move(Level level) {
        movable.move(null, level, this);
    }

    public void setMovable(Movable movable) {
        this.movable = movable;
    }

}
