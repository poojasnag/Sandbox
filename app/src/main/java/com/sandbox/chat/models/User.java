package com.sandbox.chat.models;

import java.io.Serializable;

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

    public User(){

    }

    /**
     * Returns a new user
     * @param uid User ID
     * @param email Email
     */
    public User(String uid, String email, String firebaseToken){
        this.firebaseToken = firebaseToken;
        this.uid = uid;
        this.email = email;
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
}

