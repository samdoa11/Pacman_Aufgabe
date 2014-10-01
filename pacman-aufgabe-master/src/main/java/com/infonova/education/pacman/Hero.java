package com.infonova.education.pacman;

import com.infonova.education.pacman.strategy.HeroMovable;
import com.infonova.education.pacman.strategy.Movable;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Hero extends MovableGameObject {

    private Movable movable = new HeroMovable();

    private boolean superMode = false;
    private int points;

    public Hero(int x, int y) {
        super(x, y);
    }

    private final ImageIcon heroImage;

    {
        URL url = ClassLoader.getSystemResource("images/hero.png");
        heroImage = new ImageIcon(url);
    }

    public Image getImage() {
        return heroImage.getImage();
    }

    public boolean isSuperMode() {
        return superMode;
    }

    public void startSupermode() {
        this.superMode = true;
    }

    public void endSupermode() {
        this.superMode = false;
    }

    public void move(Level level, UserAction userAction) {
        movable.move(userAction, level, this);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
