package com.infonova.education.pacman;

public abstract class MovableGameObject extends GameObject {

    public MovableGameObject(int x, int y) {
        super(x, y);
    }

    @Override
    public void setX(int x) {
        boolean changed = getX() != x;
        super.setX(x);

        if (changed) {
            this.setChanged();
            this.notifyObservers();
        }
    }

    @Override
    public void setY(int y) {
        boolean changed = getY() != y;
        super.setY(y);

        if (changed) {
            this.setChanged();
            this.notifyObservers();
        }
    }
}
