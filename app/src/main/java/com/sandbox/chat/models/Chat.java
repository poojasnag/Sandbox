package com.sandbox.chat.models;

/**
 * Contains information of a chat message
 */
public class Chat {
    /**
     * The email of the sender
     */
    public String sender;
    /**
     * The email of the receiver
     */
    public String receiver;
    /**
     * The user ID of the sender
     */
    public String senderUid;
    /**
     * The user ID of the receiver
     */
    public String receiverUid;
    /**
     * The content of the message
     */
    public String message;
    /**
     * The time at which the message was sent
     */
    public long timestamp;

    public Chat(){

    }

    /**
     * Constructor for a chat message
     */
    public Chat(String sender, String receiver, String senderUid, String receiverUid, String message, long timestamp){
        this.sender = sender;
        this.receiver = receiver;
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.message = message;
        this.timestamp = timestamp;

    }
}
