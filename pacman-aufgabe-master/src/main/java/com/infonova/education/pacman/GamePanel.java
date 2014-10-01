package com.infonova.education.pacman;

import com.infonova.education.pacman.thread.CreateGhostThread;
import com.infonova.education.pacman.thread.MoveGhostThread;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Level level;
    private GameFrame frame;

    private final Image ENEMY_SUPERMODE_IMAGE;

    {
        URL url = ClassLoader.getSystemResource("images/enemy_vulnerable.png");
        ENEMY_SUPERMODE_IMAGE = new ImageIcon(url).getImage();
    }

    private boolean isGameRunning = true;

    private final int step = 50;

    private final int borderX = 10;

    private Thread moveGhostThread;

    private final int borderY = 10;

    public void endGame() {
        this.isGameRunning = false;
        moveGhostThread.interrupt();

        Hero hero = level.getHero();
        hero.deleteObservers();

        for (Enemy enemy : level.getEnemies()) {
            enemy.deleteObservers();
        }

    }

    public void nextLevel() {
        frame.nextLevel();
    }

    public void startLevel() {
        frame.startLevel();
    }

    public GamePanel(Level level, GameFrame frame) {

        this.setLayout(new BorderLayout());

        this.level = level;
        this.frame = frame;

        new CreateGhostThread(level, this).start();

        int width = borderX * 2 + step * level.getMaxX() + 1;
        int height = borderY * 2 + step * level.getMaxY() + 1;

        this.setPreferredSize(new Dimension(width, height));

        moveGhostThread = new MoveGhostThread(level);
        moveGhostThread.start();

    }

    @Override
    public void paint(Graphics g) {

        for (int x = 0; x < level.getMaxX(); x++) {
            for (int y = 0; y < level.getMaxY(); y++) {
                paintElement(x, y, g);
                g.drawString(level.getScore() + "", 100, 20);
                this.updateUI();
            }
        }

    }

    private int toPixel(int coordinate) {
        return coordinate * step;
    }

    private void paintElement(int x, int y, Graphics g) {

        int pixelX = borderX + toPixel(x);
        int pixelY = borderY + toPixel(y);

        BackgroundElement bg = level.getBg(x, y);
        Hero h = level.getHero();

        Image image = selectImageToDraw(h, level.getEnemies(), bg, x, y);
        g.drawImage(image, pixelX, pixelY, null);

        if (!this.isGameRunning) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 100, 50);
        }

    }

    private Image selectImageToDraw(Hero h, List<Enemy> enemies, BackgroundElement bg, int x, int y) {

        for (Enemy e : enemies) {

            if (e.getX() == x && e.getY() == y && e.isAlive()) {
                if (h.isSuperMode()) {
                    return ENEMY_SUPERMODE_IMAGE;
                }
                return e.getImage();
            }

        }

        if (h.getX() == x && h.getY() == y) {
            return h.getImage();
        }

        return bg.getImage();
    }

}
