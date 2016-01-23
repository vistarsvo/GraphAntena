package com.homesoft;


import java.util.Random;

public class MathHelper {

    private static Random fRandom = new Random();

    public static float getRandDelta() {
        float ret =  Math.round(Math.random() * 1000f) / 1000f;
        return ret;
    }

    public static float getRandSigma() {
        return (float) Math.round(fRandom.nextDouble() * 1000f) / 1000f;
    }

    /*public float round(double a, int c) {
        c = c * 10;
        return Math.round(a * (float)c) / (float)c;
    }*/

}
