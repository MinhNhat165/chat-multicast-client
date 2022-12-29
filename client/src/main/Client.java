/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

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

            DatagramPacket user = new DatagramPacket(("<join>" + main.getName()).getBytes(), ("<join>" + main.getName()).length() ,group, MULTICAST_PORT);
            socket.send(user);

            while(isRunning){
                if(main.getStatus() == 0){ // READ -> status from main is setted when trigger event button send message click from UI
                     try {
                        byte[] buf = new byte[DATAGRAM_BUFFTER_LENGTH];
                        DatagramPacket recv = new DatagramPacket(buf, buf.length);
                        socket.receive(recv);
                        byte[] data = recv.getData();
                        String message = new String(data);
                        System.out.println("Data from server: " + message);
                    } catch (Exception e) { System.out.println("error " + e.getMessage());
                    }
                } else if(main.getStatus() == 1) { // WRITE
                    String message = "";
                    if(main.getOut() == 1) { // has any user out
                        message = "<leave>" + main.getName();
                    }else {
                        // trong code mau doan nay phai xem chat private hay chat group
                        // vi lien quan toi UI nen m xem lai
                        message = "Client<message><private>"; // xem de lay message input UI
                        message = "Client<message>"; // xem de lay message tu input UI
                    }
                    
                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), group, MULTICAST_PORT);
                    System.out.println("noti: " + message + "  TTL= " + socket.getTimeToLive());
                    socket.send(packet);
                    main.setStatus(0);
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
 