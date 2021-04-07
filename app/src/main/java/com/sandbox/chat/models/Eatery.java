package com.sandbox.chat.models;

import java.io.Serializable;

/**
 * Contains information about an eatery
 */

public class Eatery implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8309082032117512178L;


    private String operatingTime;
    private String eateryID;
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

    public String getEateryAddress() {
        return eateryAddress;
    }

    private String eateryAddress;

    private String eateryStreet;

    public Eatery(String eateryID, String eateryName, String eateryAddress ,String eateryStreet, String operatingTime) {
//        string;
        this.eateryName = eateryName;

        this.eateryStreet = eateryStreet;
        this.eateryAddress = eateryAddress;
        this.operatingTime = operatingTime;
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
    public String getEateryID() {
        return eateryID;
    }

    public String getEateryStreet() {
        return eateryStreet;
    }
    public String getOperatingTime(){
        return operatingTime;
    }

}

