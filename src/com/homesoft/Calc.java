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
    private float DASigma   = 0f;

    private float[] Dg;//  = new float[190];
    private float[] Ra;//  = new float[190];
    private float[] Rna;// = new float[190];

    private float[] SigmaNC;
    private float[] DeltaNC;


    private float DeltaFiMax;


    public void calculateAll() {
        int     i       = 0;
        int     dala    = (int) dal;
        float   a       = 0f;
        float   Max     = 0f;

        Dg              = new float[ (int)Nal + 2 ];
        Ra              = new float[ (int)Nal + 2 ];
        Rna             = new float[ (int)Nal + 2 ];
        SigmaNC         = new float[ (int)Nc + 1 ];
        DeltaNC         = new float[ (int)Nc + 1 ];

        // Delta && Sigma
        for (int iter = 1; iter <= Nc; iter++) {
            SigmaNC[iter] = MathHelper.getRandSigma();
            DeltaNC[iter] = MathHelper.getRandDelta();
        }

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


    private float FiError(int i) { // #1
        return (2f * SigmaNC[i] /*     * (float) i    */ - 1) * DeltaFiMax;
    }

    private float FiSum(float FiErr, float Fi) { // #4
        return Fi + FiErr;
    }

    private float Arasp(float Psi, float PsiFirst) { // #5
        float part = (float) (Math.sin(Psi) / Math.sin(PsiFirst));
        return (float) (Math.abs(Math.cos(Psi)) * Math.exp(-m * (part * part)));
    }

    private float AError(int i) { // #6
        return 1f + (2f * SigmaNC[i] - 1f) * DASigma;
    }

    private float Ralfa(float a) {
        //Формирование сумм, вычисление  R(alfa)
        float sumcos = 0f;
        float sumsin = 0f;
        float R1a    = 0f;
        float eps1   = 0f;
        float Ai     = 0f;
        float Arasp  = 1f;
        float FiErr  = 0f;
        float FiSum  = 0f;
        float PsiFirst = 0f;
        float AError = 0f;

        for (int i = 1; i <= Nc; i++) {
            FiErr = FiError(i);
            Psi  = (2f * i - Nc - 1f) * 3.1415f / N; // #2
            if (PsiFirst == 0f) PsiFirst = Psi;
            Fi   = (float) (KD * (Math.cos(Psi - a) - Math.cos(Psi)) / 2f); // #3
            FiSum = FiSum(FiErr, Fi); // # 4
            Arasp = Arasp(Psi, PsiFirst);
            AError = AError(i); // # 6
            eps1 = (float) ((1f - eps) * Math.cos(a - Psi)) / 2f; // parth of 7
            R1a  = eps1 + (float) Math.sqrt(eps1 * eps1 + eps); // #7
            Ai   = R1a * Arasp * AError; // #8
            sumcos = (float) (sumcos + Ai * Math.cos(FiSum)); // Fi -> FiSum
            sumsin = (float) (sumsin + Ai * Math.sin(FiSum)); // Fi -> FiSum
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

    public void setDeltaFiMax(float deltaFiMax) {
        DeltaFiMax = deltaFiMax;
    }
    public void setM(float sM) {
        m = sM;
    }
    public void setDASigma(float DAS) {
        DASigma = (float) (DAS * Math.PI / 180f);
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
