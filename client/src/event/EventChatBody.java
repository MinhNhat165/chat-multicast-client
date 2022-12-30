package event;

import models.ChatMessage;
import models.Room;

public interface EventChatBody {
    void setRoomChat(String roomName, String id);
    void receiveMessage(ChatMessage chatMessage);

    void addRoomChat(Room room);
}
