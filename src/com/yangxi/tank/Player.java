package com.yangxi.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author 25182
 */
public class Player {
    public static final int SPEED = 5;
    private int x, y;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private boolean moving = false;
    private Group group;
    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Player(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }


    public void paint(Graphics g) {

        if (!this.isLive()) {
            return;
        }

        switch (dir) {
            case L:
                g.drawImage(ResourceManager.goodTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceManager.goodTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceManager.goodTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceManager.goodTankD, x, y, null);
                break;
        }
        move();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        setMainDir();
    }

    private void setMainDir() {
        // 四个按键都没有按下去,就停止
        if (!bL && !bU && !bR && !bD) {
            moving = false;
        } else {
            moving = true;
            if (bL && !bU && !bR && !bD) {
                dir = Dir.L;
            }
            if (!bL && bU && !bR && !bD) {
                dir = Dir.U;
            }
            if (!bL && !bU && bR && !bD) {
                dir = Dir.R;
            }
            if (!bL && !bU && !bR && bD) {
                dir = Dir.D;
            }
        }

    }

    private void move() {
        if (!moving) {
            return;
        }

        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_Z:
                fire();
                break;
        }
        setMainDir();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void fire() {
        int bX = x + ResourceManager.goodTankU.getWidth() / 2 - ResourceManager.bulletU.getWidth() / 2;
        int bY = y + ResourceManager.goodTankU.getHeight() / 2 - ResourceManager.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.addBullet(new Bullet(bX, bY, dir, group));
    }

    public void die() {
        setLive(false);
        TankFrame.INSTANCE.addExplode(new Explode(x,y));
    }
}

