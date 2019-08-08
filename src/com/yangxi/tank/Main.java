package com.yangxi.tank;

import java.util.concurrent.TimeUnit;

/**
 * @author 25182
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = TankFrame.INSTANCE;
        tf.setVisible(true);

        for(;;){
            TimeUnit.MILLISECONDS.sleep(25);
            tf.repaint();
        }

    }
}

