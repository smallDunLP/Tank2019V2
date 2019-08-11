package com.yangxi.tank;

import java.awt.*;

public class Bullet {
    public static final int SPEED = 6;
    private int x;
    private int y;
    private Dir dir;
    private Group group;
    private boolean islive = true;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

        boundsCheck();
    }

    public  void collidesWithTank(Tank tank) {
        if(!this.isIslive() || !tank.isLive()){
            return;
        }

        if(this.group == tank.getGroup()){
            return;
        }

        Rectangle rect = new Rectangle(x,y,ResourceManager.bulletU.getWidth(),ResourceManager.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(),tank.getY(),ResourceManager.goodTankU.getWidth(),ResourceManager.goodTankU.getHeight());

        if(rect.intersects(rectTank)){
            this.die();
            tank.die();
        }
//        Rectangle rect = new Rectangle(x,y,ResourceManager.bulletU.getWidth(),ResourceManager.bulletU.getHeight());
//
//        if(!this.isIslive()){
//            return;
//        }
//
//        if(this.group == Group.BAD){
//            Rectangle rectPlayer = new Rectangle(player.getX(),player.getY(),ResourceManager.goodTankU.getWidth(),ResourceManager.goodTankU.getHeight());
//            if(rect.intersects(rectPlayer)){
//                this.die();
//                player.die();
//            }
//        }else{
//            Rectangle rectTank = new Rectangle(tank.getX(),tank.getY(),ResourceManager.goodTankU.getWidth(),ResourceManager.goodTankU.getHeight());
//            if(rect.intersects(rectTank)){
//                this.die();
//                tank.die();
//            }
//        }

    }

    public  void collidesWithPlayer(Player player) {
        if(!this.isIslive() || !player.isLive()){
            return;
        }

        if(this.group == Group.GOOD){
            return;
        }

        Rectangle rect = new Rectangle(x,y,ResourceManager.bulletU.getWidth(),ResourceManager.bulletU.getHeight());
        Rectangle rectPlayer = new Rectangle(player.getX(),player.getY(),ResourceManager.goodTankU.getWidth(),ResourceManager.goodTankU.getHeight());

        if(rect.intersects(rectPlayer)){
            this.die();
            player.die();
        }

    }

    private void boundsCheck() {
        if( x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            setIslive(false);
        }
    }

    public void die(){
        this.setIslive(false);
    }
}
