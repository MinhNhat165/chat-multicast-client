package event;
import models.ChatMessage;

public interface EventChat {
    void receiveMessage(String text, String user);
    void setRoomChat(String roomName, String id);
    void receiveMessage(ChatMessage chatMessage);


}
