package com.sandbox.chat.models;

public class Eatery {
    private String eateryName;
    private int xCoordinate;
    private int yCoordinate;

    public Eatery(String eateryName, int xCoordinate, int yCoordinate) {
        this.eateryName = eateryName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getEateryName() {
        return eateryName;
    }

    public void setEateryName(String eateryName) {
        this.eateryName = eateryName;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
