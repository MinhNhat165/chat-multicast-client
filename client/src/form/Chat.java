package form;
import component.Chat_Body;
import component.Chat_Bottom;
import component.Chat_Title;
import event.EventChatBody;
import event.PublicEvent;
import models.ChatMessage;
import models.Room;
import net.miginfocom.swing.MigLayout;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

public class Chat extends javax.swing.JPanel {
    ArrayList<Room> rooms ;
    Room currentRoom;

    public Chat() {
        initComponents();
        init();
    }
    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, bottom]0[shrink 0]0"));
        Chat_Title chatTitle = new Chat_Title();
        Chat_Body chatBody = new Chat_Body();
        Chat_Bottom chatBottom = new Chat_Bottom();
        rooms = new ArrayList<>();
        Room room = new Room("General", new ArrayList<>());
        room.setId("111111");
        currentRoom = room;
        rooms.add(room);
        chatTitle.setUserName(currentRoom.getName());
        chatBody.setItems(currentRoom.getChatMessages());
        chatBottom.setCurrentRoom(currentRoom);
        PublicEvent.getInstance().addEventChatBody(new EventChatBody() {

            @Override
            public void setRoomChat(String roomName, String id) {
                int index = IntStream.range(0, rooms.size())
                        .filter(i -> Objects.equals(rooms.get(i).getId(), id))
                        .findFirst()
                        .orElse(-1); // kiểm tra room đã tồn tại chưa
                if(index >=0) {
                    currentRoom = rooms.get(index);

                } else {
                    currentRoom = rooms.get(0);

                }
                System.out.println(currentRoom.getId());
                chatBody.removeAll();
                chatTitle.setUserName(currentRoom.getName());
                chatBody.setItems(currentRoom.getChatMessages());
                chatBottom.setCurrentRoom(currentRoom);

            }

            @Override
            public void receiveMessage(ChatMessage chatMessage) {

                if(currentRoom.getId().equals(chatMessage.getRoomId())) {
                    chatBody.addItem(chatMessage); // nếu đang đứng tại room đó thì add tinh nhắn vô luôn;
                }
                int index = IntStream.range(0, rooms.size())
                        .filter(i -> Objects.equals(rooms.get(i).getId(), chatMessage.getRoomId()))
                        .findFirst()
                        .orElse(-1); // kiểm tra room đã tồn tại chưa
                if(index >=0) {
                    rooms.get(index).getChatMessages().add(chatMessage); // nếu đã tồn tại thêm mess và room
                }

            }

            @Override
            public void addRoomChat(Room room) {
                rooms.add(room);
            }
        });


        add(chatTitle, "wrap");
        add(chatBody, "wrap");
        add(chatBottom, "h ::50%");
    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
