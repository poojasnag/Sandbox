package com.sandbox.chat.models;

public class User {
    private String uid;
    private String email;
    private String firebaseToken;
    private int rating;

    public User(){

    }

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

