package event;

import models.Room;

import java.io.IOException;

public interface EventMainChat {
    public void sendMessage(String text, String roomName, String members) throws IOException;

    void createRoom(Room room) throws IOException;

    public String getCurrentUser();
    void logout() throws IOException;
}
