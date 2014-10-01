package com.infonova.education.pacman.strategy;

import com.infonova.education.pacman.*;

public class EnemyMovable implements Movable {
    private int oldXEnemy;
    private int oldYEnemy;

    @Override
    public void move(UserAction userAction, Level level, GameObject gameObject) {

        final int maxX = level.getMaxX();
        final int maxY = level.getMaxY();

        int newX = gameObject.getX();
        int newY = gameObject.getY();

        int[] dotPoints = level.getNextDot(newX, newY);

        if (dotPoints[0] < newX) {
            newX = movePosition(--newX, maxX);
        } else if (dotPoints[0] > newX) {
            newX = movePosition(++newX, maxX);
        } else if (dotPoints[1] < newY) {
            newY = movePosition(--newY, maxY);
        } else if (dotPoints[1] > newY) {

            newY = movePosition(++newY, maxY);

        }

        BackgroundElement backgroundElement = level.getBg(newX, newY);
        BackgroundType backgroundType = backgroundElement.getType();

        if (backgroundType == BackgroundType.WALL) {
            newX = this.oldXEnemy;
            newY = this.oldYEnemy;
        } else {
            this.oldXEnemy = newX;
            this.oldYEnemy = newY;
        }
        if (backgroundType == BackgroundType.DOT) {
            BackgroundElement bg = level.getBg(newX, newY);
            bg.setType(BackgroundType.FREE);
            level.addBg(bg);
        }


        gameObject.setX(newX);
        gameObject.setY(newY);

    }

    private int movePosition(int position, int maxPosition) {
        position = Math.max(0, position);
        position = Math.min(position, maxPosition - 1);
        return position;
    }

}
