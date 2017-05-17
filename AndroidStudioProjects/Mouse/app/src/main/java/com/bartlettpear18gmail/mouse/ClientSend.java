package com.bartlettpear18gmail.mouse;

import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Joel.Bartlett18 on 5/14/2017.
 */
public class ClientSend implements Runnable {
    private int port = 3000;
    private String ip = "localhost";

    @Override
    public void run() {
        try {
            DatagramSocket udpSocket = new DatagramSocket(port);
            InetAddress serverAddr = InetAddress.getByName(ip);
            byte[] buf = ("The String to Send").getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length,serverAddr, port);
            udpSocket.send(packet);
        } catch (SocketException e) {
            Log.e("Udp:", "Socket Error:", e);
        } catch (IOException e) {
            Log.e("Udp Send:", "IO Error:", e);
        }
    }


    //Datagram Stuff
    public static void runUDPClient()  {
        System.out.println("Datagram Run started");
        String clientMessage = "android test from UdatagramPacket client laptop";
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName("localhost");
            System.out.println(serverAddr);;
            DatagramPacket datagramPacket;
            datagramPacket = new DatagramPacket(clientMessage.getBytes(), clientMessage.length(), serverAddr, 3000);
            datagramSocket.send(datagramPacket);
            System.out.println("Packet sent");
        } catch (SocketException e) {
            e.printStackTrace();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    public static void sendMessage(final String message) {
        System.out.println("Datagram test");
        Thread thread = new Thread(new Runnable() {

            String stringData;

            @Override
            public void run() {
                System.out.println("Datagram run start");
                DatagramSocket ds = null;
                try {
                    //may want to change ip to get that of host, not device
                    InetAddress host = InetAddress.getLocalHost();
                    ds = new DatagramSocket(3000);

                    byte[] buffer = new byte[2048];
                    buffer = message.getBytes();

                    System.out.println("Server Address is " + host);

                    DatagramPacket dp = new DatagramPacket(buffer, buffer.length, host, 3000);
                    ds.send(dp);
                    System.out.println("Packet sent");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Failed here");
                } finally {
                    if (ds != null) {
                        ds.close();
                    }
                }
            }
        });

        thread.start();
    }


}

//Thread udpConnect = new Thread(new ClientSend());
//udpConnect.start();
