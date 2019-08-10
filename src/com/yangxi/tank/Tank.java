package com.yangxi.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.PrimitiveIterator;

/**
 * @author 25182
 */
public class Tank {
    private int x, y;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private boolean moving = false;
    public static final int SPEED = 5;
    private Group group;

    public Tank(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }


    public void paint(Graphics g) {
        if(this.group == Group.GOOD){
            switch (dir){
                case L:
                    g.drawImage(ResourceManager.goodTankL,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceManager.goodTankU,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceManager.goodTankR,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceManager.goodTankD,x,y,null);
                    break;
            }
        }

        if(this.group == Group.BAD){
            switch (dir){
                case L:
                    g.drawImage(ResourceManager.badTankL,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceManager.badTankU,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceManager.badTankR,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceManager.badTankD,x,y,null);
                    break;
            }
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
        }else{
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
//            if (bL && bU && !bR && !bD) {
//                dir = Dir.LU;
//            }
//            if (bL && !bU && !bR && bD) {
//                dir = Dir.LD;
//            }
//            if (!bL && bU && bR && !bD) {
//                dir = Dir.RU;
//            }
//            if (!bL && !bU && bR && bD) {
//                dir = Dir.RD;
//            }
        }


    }

    private void move() {
        if(!moving) {return;}

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
            case LU:
                y -= SPEED;
                x -= SPEED;
                break;
            case LD:
                x -= SPEED;
                y += SPEED;
                break;
            case RU:
                x += SPEED;
                y -= SPEED;
                break;
            case RD:
                x += SPEED;
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
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        setMainDir();
    }

    private void fire() {
        TankFrame.INSTANCE.addBullet(new Bullet(x+20,y,dir,group));
    }
}

