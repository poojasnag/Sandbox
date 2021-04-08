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
    private ArrayList<Integer> rating;

    public User(){

    }

    /**
     * Returns a new user
     * @param uid User ID
     * @param email Email
     */
    public User(String uid, String email, String firebaseToken, ArrayList<Integer> rating){
        this.firebaseToken = firebaseToken;
        this.uid = uid;
        this.email = email;
        this.rating = rating;
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

    public ArrayList<Integer> getRating() {
        return rating;
    }

    public void setRating(ArrayList<Integer> rating) {
        this.rating = rating;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}

