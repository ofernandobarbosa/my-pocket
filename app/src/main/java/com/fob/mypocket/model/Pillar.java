package com.fob.mypocket.model;

public class Pillar {

    private String title;
    private String expendValue;
    private String availableValue;

    private int representativeImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpendValue() {
        return expendValue;
    }

    public void setExpendValue(String expendValue) {
        this.expendValue = expendValue;
    }

    public String getAvailableValue() {
        return availableValue;
    }

    public void setAvailableValue(String availableValue) {
        this.availableValue = availableValue;
    }

    public int getRepresentativeImage() {
        return representativeImage;
    }

    public void setRepresentativeImage(int representativeImage) {
        this.representativeImage = representativeImage;
    }

    public Pillar() {

    }
    public Pillar(String title, String expendValue, String availableValue, int representativeImage) {
        this.title = title;
        this.expendValue = expendValue;
        this.availableValue = availableValue;
        this.representativeImage = representativeImage;
    }
}
