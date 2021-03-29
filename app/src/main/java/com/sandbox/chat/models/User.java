package com.sandbox.chat.models;

/**
 * Stores the information of the users
 */
public class User {
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
    /**
     * The users' rating
     */
    private int rating;

    public User(){

    }

    /**
     * Returns a new user
     * @param uid User ID
     * @param email Email
     * @param firebaseToken Firebase token
     */
    public User(String uid, String email, String firebaseToken){
        this.uid = uid;
        this.email = email;
        this.firebaseToken = firebaseToken;
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

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

