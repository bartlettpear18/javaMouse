package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        while(true) {
            runUDPServer();
        }
    }

    private static void runUDPServer() {
        String text;
        byte[] message = new byte[2048];
        DatagramPacket datagramPacket = new DatagramPacket(message, message.length);
        DatagramSocket datagramSocket = null;
        try {
            //InetAddress serverAddr = InetAddress.getByName("localhost");
            datagramSocket = new DatagramSocket(3000);
            datagramSocket.receive(datagramPacket);
            //System.out.println(serverAddr);;
            text = new String(message, 0, datagramPacket.getLength());
            System.out.println("UDP packet received " + text);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}
