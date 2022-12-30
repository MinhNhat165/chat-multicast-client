package models;

import java.util.ArrayList;
import java.util.UUID;

public class Room {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private ArrayList<String> member;
    private ArrayList<ChatMessage> chatMessages;

    public Room(String name, ArrayList<String> member) {
        this.name = name;
        this.member = member;
        this.chatMessages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMember() {
        return member;
    }

    public void setMember(ArrayList<String> member) {
        this.member = member;
    }

    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public Room(String id, String name, ArrayList<String> member) {
        this.id = id;
        this.name = name;
        this.member = member;
        this.chatMessages = new ArrayList<>();
    }
}
