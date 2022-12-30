package event;

import models.Room;

import java.util.ArrayList;

public interface EventUser {
    void  removeUserConnected(String user);

    void addUserConnected(String users);

    void setUserListConnected(ArrayList<String> arrayList);

    int getTabActive();

    void addNewRoom(Room room);
}
