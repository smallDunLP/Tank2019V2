package com.yangxi.tank;

import java.util.Random;

/**
 * @author 25182
 */

public enum Dir {
    L,U,R,D;

    private static Random random = new Random();

    public static Dir randomDir(){

        return values()[random.nextInt(values().length)];
    }
}

