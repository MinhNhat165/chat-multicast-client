package models;

import java.util.ArrayList;
import java.util.Arrays;

public class Message {  //type<from>sender<text>receiver
    private String type;
    private String from;
    private String sender;
    private String text;
    private String roomName;
    private String members;

    private String roomId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setReceiver(String roomName) {
        this.roomName = roomName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message() {
    }

    public Message(String from, String type, String sender, String receiver, String text) {
        this.type = type;
        this.sender = sender;
        this.roomName = receiver;
        this.text = text;
    }

    public Message(String fullMessage) {
        String[] messageSplit = fullMessage.split("[\\<||\\>]");
        System.out.println(Arrays.toString(messageSplit));
        int size = messageSplit.length;
        this.type = messageSplit[0];
        this.from = messageSplit[1];
        this.sender = messageSplit[2];

        if(size > 3) {
            this.text = messageSplit[3];
            if(size > 4) {
                this.roomName = messageSplit[4];
                if(size > 5) {
                    this.members = messageSplit[5];

                    if(size > 6) {
                        this.roomId = messageSplit[6];
                    }
                }
            }
        }
    }

    public Message(String from, String type, String sender, String text) {
        this.type = type;
        this.sender = sender;
        this.text = text;
    }

    public Message(String from, String type, String sender) {
        this.type = type;
        this.sender = sender;
    }

    public String makeLeaveMessage(String sender) {
        return "leave<server>" + sender;
    }

    public String makeJoinMessage(String sender) {
        return "join<server>" + sender;
    }

    public  String makeTextMessage(String sender, String text) {
        return "message<server>" + sender + "<" + text +">"+"<><>";
    }

    public  String makeTextMessageFull(String sender, String text, String roomName) {
        return "message<server>" + sender + "<" + text +">"+ roomName +"<><>";
    }


    public String makeUserList(ArrayList<String> users) {
        return "userList<server>" + users.toString()+"<><>";
    }
    public String makeTextCreateRoom(String sender,String roomId, String roomName, String members) {
        return "room<server>"+ sender +"<>"+ roomName+ "<"+ members + ">"+roomId+ "<><>";
    }

    public  String makeTextMessageRoom(String sender, String text, String roomId) {
        return "message<server>"+ sender +"<"+text+">"+"<>"+roomId+"<><>";
    }



}
