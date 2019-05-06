package com.germanlizondo.sudokuuf1.Model;

public class User {

    private String nom;
    private int punts;

    public User() {
    }

    public User(String nom) {
        this.nom = nom;
        this.punts = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }


}
