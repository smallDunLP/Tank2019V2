package com.yangxi.tank.strategy;

import com.yangxi.tank.*;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        int bX = player.getX() + ResourceManager.goodTankU.getWidth() / 2 - ResourceManager.bulletU.getWidth() / 2;
        int bY = player.getY() + ResourceManager.goodTankU.getHeight() / 2 - ResourceManager.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.addBullet(new Bullet(bX, bY,player.getDir(),player.getGroup()));

    }
}
