package com.yangxi.tank;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private Dir dir;
    private Group group;
    public static final int SPEED = 6;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public void paint(Graphics g) {

        switch (dir) {
            case L:
                g.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceManager.bulletD, x, y, null);
                break;
        }

        move();
    }

    private void move() {

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
}
