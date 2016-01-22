package com.homesoft;

import sun.rmi.runtime.Log;

import java.util.logging.Logger;

public class Calc {
    private float N     = 24f;
    private float Nc    = 8f;
    private float KD    = 25.9f;
    private float eps   = 0.15f;
    private float m     = 0f;
    private float dal   = 1f;
    private float Nal   = 180f / dal + 1f;
    private float Pi180 = 3.1415f / 180f;
    private float Psi   = 0f;
    private float Fi    = 0f;
    private float Ral   = 0f;

    private float[] Dg;//  = new float[190];
    private float[] Ra;//  = new float[190];
    private float[] Rna;// = new float[190];

    private static Logger log = Logger.getLogger(Calc.class.getName());

    private float firstRandom;
    private float secondRandom;


    public void calculateAll() {
        int     i       = 0;
        int     dala    = (int) dal;
        float   a       = 0f;
        float   Max     = 0f;

        Dg              = new float[ (int)Nal + 2 ];
        Ra              = new float[ (int)Nal + 2 ];
        Rna             = new float[ (int)Nal + 2 ];

        // Цикл по углу al
        for (int al = 0; al <= 180; al+=dala) { // тут шаг +dal
            i++;
            a = (float)al * Pi180;
            Ralfa(a);
            Dg[i] = al;
            Ra[i] = Ral;
        }

        // Поиск максимума
        for (int k = 1; k<= Nal; k++) {
            if (Ra[1] >= Ra[k + 1]) {
                Max = Ra[1];
            } else {
                Max = Ra[k + 1];
            }
        }

        // Нормировка
        for (int k = 1; k<= Nal; k++) {
            Rna[k] = Ra[k] / Max;
        }
    }


    private float Ralfa(float a) {
        //Формирование сумм, вычисление  R(alfa)
        float sumcos = 0f;
        float sumsin = 0f;
        float R1a    = 0f;
        float eps1   = 0f;
        float Ai     = 0f;
        float Arasp  = 1f;

        for (int m = 1; m <= Nc; m++) {
            Psi  = (2f * m - Nc - 1f) * 3.1415f / N; // * firstRandom * secondRandom;
            Fi   = (float) (KD * (Math.cos(Psi - a) - Math.cos(Psi)) / 2f);
            eps1 = (float) ((1f - eps) * Math.cos(a - Psi)) / 2f;
            R1a  = eps1 + (float) Math.sqrt(eps1 * eps1 + eps);
            Ai   = R1a * Arasp;
            sumcos = (float) (sumcos + Ai * Math.cos(Fi));
            sumsin = (float) (sumsin + Ai * Math.sin(Fi));
        }
        Ral = (float) Math.sqrt(sumcos * sumcos + sumsin * sumsin);
        return Ral;
    }

    // Setters
    public void setN(float set_n) {
        this.N = set_n;
    }
    public void setNc(float nc) {
        this.Nc = nc;
    }
    public void setKD(float KD) {
        this.KD = KD;
    }
    public void setEps(float eps) {
        this.eps = eps;
    }

    public void setFirstRandom(float fRandom) {
        firstRandom = fRandom;
    }

    public void setSecondRandom(float sRandom) {
        secondRandom = sRandom;
    }

    // Getters
    public float[] getRa() {
        return Ra;
    }
    public float[] getRna() {
        return Rna;
    }
    public float[] getDg() {
        return Dg;
    }
}
