package com.yangxi.tank;

import java.awt.*;

public class Explode {
    private int x;
    private int y;
    private int width,height;
    private int step = 0;
    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceManager.explodes[0].getWidth();
        this.height = ResourceManager.explodes[0].getHeight();
    }

    public void paint(Graphics g) {
        if(!live) {
            return;
        }
        g.drawImage(ResourceManager.explodes[step],x,y,null);
        step ++;
        if(step >= ResourceManager.explodes.length){
            this.die();
        }
    }

    private void die() {
        live = false;
    }

}
