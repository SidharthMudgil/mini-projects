package com.example.martialarts.model;

import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;

public class MartialArt implements Serializable {

    private int martialArtID;
    private String martialArtName;
    private String martialArtColor;
    private double martialArtPrice;

    public MartialArt(int martialArtID, String martialArtName, String martialArtColor, double martialArtPrice) {
        setMartialArtID(martialArtID);
        setMartialArtName(martialArtName);
        setMartialArtColor(martialArtColor);
        setMartialArtPrice(martialArtPrice);
    }

    public int getMartialArtID() {
        return martialArtID;
    }

    public void setMartialArtID(int martialArtID) {
        this.martialArtID = martialArtID;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }
}
