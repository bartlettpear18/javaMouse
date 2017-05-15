package com.bartlettpear18gmail.mouse;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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
}

//Thread udpConnect = new Thread(new ClientSend());
//udpConnect.start();
