import java.util.*;
import java.net.*;
import java.lang.*;
import java.io.IOException;


public class ClientTest {
    public static void main (String[] args) {
        runUDPClient();
    }

    private static void runUDPClient()  {
        String clientMessage = "test from UdatagramPacket client laptop";
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName("localhost");
            System.out.println(serverAddr);;
            DatagramPacket datagramPacket;
            datagramPacket = new DatagramPacket(clientMessage.getBytes(), clientMessage.length(), serverAddr, 3000);
            datagramSocket.send(datagramPacket);
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
}