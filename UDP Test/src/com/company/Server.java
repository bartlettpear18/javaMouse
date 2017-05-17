package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Joel.Bartlett18 on 5/15/2017.
 */
public class Server {
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
