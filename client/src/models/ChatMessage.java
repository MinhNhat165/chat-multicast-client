package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage {
    String sender;
    String text;

    String createdAt;

    String position;

    String roomId;

    public ChatMessage() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        Date date = new Date();
        this.createdAt = formatter.format(date);
    }

    public ChatMessage(String sender, String text, String position, String roomId) {
        this.sender = sender;
        this.text = text;
        this.position = position;
        this.roomId = roomId;

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        Date date = new Date();
        this.createdAt = formatter.format(date);
    }

    public ChatMessage(String sender, String text ,String position) {
        this.sender = sender;
        this.text = text;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        Date date = new Date();
        this.createdAt = formatter.format(date);
        this.position = position;
    }

    public ChatMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        Date date = new Date();
        this.createdAt = formatter.format(date);
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomName) {
        this.roomId = roomName;
    }
}
