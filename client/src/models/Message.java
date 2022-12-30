package models;

public class Message {  //type<form>sender<text>receiver
    private String type;
    private String from;
    private String sender;
    private String text;
    private String roomName;
    private String members;


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

    public void setRoomName(String roomId) {
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

    public Message(String from, String type, String sender, String roomName, String text) {
        this.type = type;
        this.sender = sender;
        this.roomName = roomName;
        this.text = text;
    }

    public Message(String fullMessage) {
        String[] messageSplit = fullMessage.split("[\\<||\\>]");
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

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String makeLeaveMessage(String sender) {
        return "leave<client>" + sender+ "<>"+ "General"+ "<><>";
    }

    public String makeJoinMessage(String sender) {
        return "join<client>" + sender+ "<>"+ "General"+ "<><>";
    }

    public  String makeTextMessage(String sender, String text) {
        return "message<client>" + sender + "<" + text +">" +"<><>";
    }

    public  String makeTextMessageFull(String sender, String text, String roomName) {
        return "message<client>" + sender + "<" + text +">"+ roomName +"<><>";
    }

    public  String makeTextMessageWithMembers(String sender, String text, String roomName, String members) {
        return "message<client>" + sender + "<" + text +">"+ roomName +"<"+members+">" +"<>";
    }

    public String makeTextCreateRoom(String roomId, String roomName, String members) {
        return "room<client>" + roomId + "<>"+ roomName +"<"+members+">" +"<>";
    }

    public  String makeTextMessageRoom(String sender, String text, String roomId) {
        return "message<client>" + sender + "<" + text +">"+ roomId +"<>" +"<>";
    }
}
