package event;

public interface EventChat {

    public void sendMessage(String text);

    void receiveMessage(String text, String user);

    void userJoin(String user);

    void joinRoom(String user);
}
