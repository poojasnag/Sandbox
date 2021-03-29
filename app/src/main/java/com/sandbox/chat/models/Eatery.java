package com.sandbox.chat.models;

/**
 * Contains information about an eatery
 */

public class Eatery {
    /**
     * The eatery's name
     */
    private String eateryName;
    /**
     * The eatery's longitude
     */
    private int xCoordinate;
    /**
     * The eatery's latitude
     */
    private int yCoordinate;

    /**
     * Contructor for the Eatery class
     * @param eateryName The name of the eatery
     * @param xCoordinate The x coordinate of the eatery
     * @param yCoordinate The y coordinate of the eatery
     */
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
