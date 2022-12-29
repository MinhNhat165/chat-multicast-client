/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ntrungduc
 */
public class Server extends Thread {

    public static final String MULTICAST_ADDRESS = "230.0.0.1";
    public static final int MULTICAST_PORT = 4000;
    public static final int DATAGRAM_BUFFTER_LENGTH = 2048;
    private ArrayList<String> users;

    public void run() {
        users = new ArrayList();
        String message = "";
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
                message = new String(data);
                System.out.println("Data from client: " + message);

                if (message.contains("<join>")) { // new user join
                    message = message.substring(("<join>").length());
                    String name = "";
                    int i = 0;
                    while (Character.isLetter(message.charAt(i))) {
                        name = name + message.charAt(i);
                        i++;
                    }

                    users.add(name);
                    String noti = "<newUser>" + users.toString();
                    System.out.println("noti: " + noti);
                    DatagramPacket packet = new DatagramPacket(noti.getBytes(), noti.length(), group, MULTICAST_PORT);
                    socket.send(packet);
                    socket.close();
                } else if (message.contains("Client<message>")) { // new message from any client
                    
                    message = message.substring(("Client<message>").length());
                    message = "Server<message>" + message;
                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), group, MULTICAST_PORT);
                    System.out.println("<noti>: " + message.toString() + "  con un TTL= " + socket.getTimeToLive());
                    socket.send(packet);
                    socket.close();
                    
                } else if (message.contains("<leave>")) { // user out group
                    String name = "";
                    int i = "<leave>".length();
                    while (Character.isLetter(message.charAt(i))) {
                        name = name + message.charAt(i);
                        i++;
                    }

                    users.remove(name);
                    String noti = "<userLeave>" + users.toString();
                    System.out.println("noti: " + noti);
                    DatagramPacket packet = new DatagramPacket(noti.getBytes(), noti.length(), group, MULTICAST_PORT);
                    socket.send(packet);
                    socket.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(2);
            }

            // sleep 5 second
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException ie) {
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
