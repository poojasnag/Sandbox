package com.sandbox.chat.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores the information of the users
 */
public class User implements Serializable {
    private static final long SerialVersionUID = 4354654243546509854L;
    /**
     * The users' ID
     */
    private String uid;
    /**
     * The users' email
     */
    private String email;
    /**
     * Token necessary for Firebase API
     */

    private String firebaseToken;
    private int rating;
    private int ratingCount;

    public User(){

    }

    /**
     * Returns a new user
     * @param uid User ID
     * @param email Email
     */
    public User(String uid, String email, String firebaseToken, int rating, int ratingCount){
        this.firebaseToken = firebaseToken;
        this.uid = uid;
        this.email = email;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public boolean equals(Object o)
    {
        if(o instanceof User)
        {
            return uid == ((User) o).getUid();
        }
        return false;
    }
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}

