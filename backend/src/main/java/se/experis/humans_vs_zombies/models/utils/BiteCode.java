package se.experis.humans_vs_zombies.models.utils;

import java.util.Random;

public class BiteCode {
    private String biteCodeValue;

    public String getBiteCodeValue() {
        return biteCodeValue;
    }

    public void setBiteCodeValue() {
        Random r1= new Random();
        this.biteCodeValue = String.format("%05d", r1.nextInt(100000));
    }
}
