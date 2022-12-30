/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import event.EventMainChat;
import event.PublicEvent;
import models.Message;
import models.Room;

import java.io.*;
import java.net.*;

/**
 *
 * @author ntrungduc
 */
public class Client extends Thread {

    public static final String MULTICAST_ADDRESS = "230.0.0.1";
    public static final int MULTICAST_PORT = 4000;
    public static final int DATAGRAM_BUFFTER_LENGTH = 2048;
    Main main = new Main(0);
    Message messageSocket = new Message();
    
    @Override
    public void run(){
        InetAddress group = null;
        try {
            group = InetAddress.getByName(MULTICAST_ADDRESS);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Boolean isRunning = true;

        try {
            // tao ra 1 multicast socket o port theo bien MULTICAST_PORT
            MulticastSocket socket = new MulticastSocket(MULTICAST_PORT);
            // tham gia vao nhom dia chi theo bien MULTICAST_ADDRESS
            socket.joinGroup(group);
            // nhan goi phuc vumain.getName().getBytes(), main.getName().length() ,group, MULTICAST_ADDRESS);
            DatagramPacket user = new DatagramPacket(messageSocket.makeJoinMessage(main.getName()).getBytes(), messageSocket.makeJoinMessage(main.getName()).length() ,group, MULTICAST_PORT);
            socket.send(user);

            InetAddress finalGroup = group;
            PublicEvent.getInstance().addEventMainChat(new EventMainChat() {
                @Override
                public void sendMessage(String text, String roomId, String members) throws IOException {
                    String message = messageSocket.makeTextMessageFull(main.getName(), text,roomId);
                    if(!roomId.isEmpty()) {
                        message = messageSocket.makeTextMessageRoom(main.getName(), text,roomId);
                    }
                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), finalGroup, MULTICAST_PORT);
                    socket.send(packet);
                }

                @Override
                public void createRoom(Room room) throws IOException {
                    String message = messageSocket.makeTextCreateRoom(room.getId(), room.getName(), room.getMember().toString());
                    System.out.println(message);
                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), finalGroup, MULTICAST_PORT);
                    socket.send(packet);
                }

                @Override
                public String getCurrentUser() {
                    return main.getName();
                }
                @Override
                public void logout() throws IOException {
                    String message = messageSocket.makeLeaveMessage(main.getName());
                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), finalGroup, MULTICAST_PORT);
                    socket.send(packet);
                }
            });

            while(isRunning){
                if(main.getStatus() == 0){ // READ -> status from main is setted when trigger event button send message click from UI
                     try {
                        byte[] buf = new byte[DATAGRAM_BUFFTER_LENGTH];
                        DatagramPacket recv = new DatagramPacket(buf, buf.length);
                        socket.receive(recv);
                        byte[] data = recv.getData();
                        String message = new String(data);
                        System.out.println("Data from server: " + message);
                    } catch (Exception e) {
                         System.out.println("error " + e.getMessage());
                    }
                } else if(main.getStatus() == 1) {
                }
               
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
    
    public static void main(String[] args) throws IOException {

        try {
            Client multicastClient = new Client();
            multicastClient.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
 