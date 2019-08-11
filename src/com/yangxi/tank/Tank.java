package com.yangxi.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * @author 25182
 */
public class Tank extends AbstractGameObject{
    public static final int SPEED = 5;
    private int x, y;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private boolean moving = true;
    private Group group;
    private boolean live = true;
    private Random random = new Random();

    private int oldX,oldY;
    private int width,height;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        oldX = x;
        oldY = y;

        this.width = ResourceManager.goodTankU.getWidth();
        this.height = ResourceManager.goodTankU.getHeight();
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!this.isLive()) {
            return;
        }

        switch (dir) {
            case L:
                g.drawImage(ResourceManager.badTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceManager.badTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceManager.badTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceManager.badTankD, x, y, null);
                break;
        }

        move();
    }

    private void move() {
        if (!moving) {
            return;
        }

        oldX = x;
        oldY = y;


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

        boundsCheck();

        randomDir();
        if (random.nextInt(100) > 90) {
            fire();
        }
    }

    private void randomDir() {
        if (random.nextInt(100) > 90) {
            this.dir = Dir.randomDir();
        }
    }

    private void boundsCheck() {
        if( x < 0 || y < 30 || x + width > TankFrame.GAME_WIDTH || y + height> TankFrame.GAME_HEIGHT){
            this.back();
        }
    }

    private void back() {
        this.x = oldX;
        this.y = oldY;

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
        TankFrame.INSTANCE.add(new Bullet(bX, bY, dir, group));
    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x,y));
    }
}

