package com.yangxi.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 25182
 */
public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();

    private Player mytank;


    private List<Explode> explodes;
    private List<Tank> tanks;
    private List<Bullet> bullets;

    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    private TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);

        //设计模式--->Observer模式:观察者模式
        this.addKeyListener(new TankKeyListener());

        initGameObject();
    }

    private void initGameObject() {
        mytank = new Player(100, 100, Dir.R, Group.GOOD);
        tanks = new ArrayList<>();
        bullets = new ArrayList<>();
        explodes = new ArrayList<>();

        int tankCount = Integer.parseInt(PropertyManager.get("initTankCount"));

        for (int i = 0; i < tankCount; i++) {
            tanks.add(new Tank(100 + 50 * i, 200, Dir.D, Group.BAD));
        }

    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    @Override
    public void paint(Graphics g) {
        //需要重新绘制的时候 就会自动调用这个方法
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + bullets.size(), 10, 50);
        g.drawString("enemys:" + tanks.size(), 10, 70);
        g.drawString("explodes:" + explodes.size(), 10, 90);
        g.setColor(c);

        mytank.paint(g);

        for (int i = 0; i < tanks.size(); i++) {
            if (!tanks.get(i).isLive()) {
                tanks.remove(i);
            } else {
                tanks.get(i).paint(g);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {

            for( int j = 0 ; j < tanks.size();j++){
                bullets.get(i).collidesWithTank(tanks.get(j));
//                bullets.get(i).collidesWithPlayer(mytank);
            }

            if (!bullets.get(i).isIslive()) {
                bullets.remove(i);
            } else {
                bullets.get(i).paint(g);
            }

        }

        for (int i = 0; i < explodes.size(); i++) {
            if (!explodes.get(i).isLive()) {
                explodes.remove(i);
            } else {
                explodes.get(i).paint(g);
            }
        }

    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void addExplode(Explode explode) {
        this.explodes.add(explode);
    }

    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            mytank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            mytank.keyReleased(e);
        }
    }
}
