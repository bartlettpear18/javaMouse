import java.util.*;
import java.net.*;
import java.lang.*;
import java.io.IOException;


public class ServerTest {
    public static void main (String[] args) {
        while(true) { 
            runUDPServer();
        }
    }

    private static void runUDPServer() {
        String text;
        byte[] message = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(message, message.length);
        DatagramSocket datagramSocket = null;
        try {
            InetAddress serverAddr = InetAddress.getByName("localhost");
            datagramSocket = new DatagramSocket(3000, serverAddr);
            datagramSocket.receive(datagramPacket);
            System.out.println(serverAddr);;
            text = new String(message, 0, datagramPacket.getLength());
            System.out.println("UdatagramPacket packet received " + text);
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