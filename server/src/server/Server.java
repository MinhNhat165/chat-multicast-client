package server;

import models.Message;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ntrungduc
 */
public class Server extends Thread {

    public static final String MULTICAST_ADDRESS = "239.255.255.250";
    public static final int MULTICAST_PORT = 4411;
    public static final int DATAGRAM_BUFFTER_LENGTH = 2048;
    private ArrayList<String> users;

    public void run() {
        users = new ArrayList<>();
        InetAddress group = null;

        try {
            group = InetAddress.getByName(MULTICAST_ADDRESS);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (true) {
            try {
                MulticastSocket socket = new MulticastSocket(MULTICAST_PORT);
                socket.joinGroup(group);

                // if receive package from client
                byte buffer[] = new byte[DATAGRAM_BUFFTER_LENGTH];
                DatagramPacket recv = new DatagramPacket(buffer, buffer.length);
                socket.receive(recv);
                byte[] data = recv.getData();
                Message message1 = new Message(new String(data));
                String messageSend = "";

                System.out.println(message1.getSender());

                switch (message1.getType()) {
                    case "join" -> {
                        users.add(message1.getSender());
                        messageSend = message1.makeUserList(users);
                    }
                    case "message" -> {
                        if(message1.getRoomName().equals("111111")) {
                            messageSend = message1.makeTextMessageFull(message1.getSender(), message1.getText(), message1.getRoomName());
                        } else messageSend = message1.makeTextMessageRoom(message1.getSender(), message1.getText(), message1.getRoomName());


                    }
                    case "leave" -> {
                        users.remove(message1.getSender());
                        messageSend = message1.makeUserList(users);
                    }

                    case "room" -> {
                        messageSend = message1.makeTextCreateRoom(message1.getSender(),message1.getRoomName(), message1.getMembers());
                    }

                    default -> {
                    }
                    // code block
                }

                DatagramPacket packet = new DatagramPacket(messageSend.getBytes(), messageSend.length(), group, MULTICAST_PORT);
                socket.send(packet);
                socket.close();

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(2);
            }

            // sleep 5 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public static void main(String[] args) {
        try {
            Server MulticastServer = new Server();
            MulticastServer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
