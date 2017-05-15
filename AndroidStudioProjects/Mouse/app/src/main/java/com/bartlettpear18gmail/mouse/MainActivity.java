package com.bartlettpear18gmail.mouse;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.bartlettpear18gmail.mouse.Position;

import static com.bartlettpear18gmail.mouse.Position.displacement;
import static com.bartlettpear18gmail.mouse.Position.parallelIntegral;
import static java.lang.Math.round;


public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mAccel;

    private double last_x, last_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);

        //runUDPClient();
        //(new Thread(new ClientSend())).start();


    }


    // Clicks
    public void leftClick (View view) {
        System.out.println("Left Click");
    }

    public void rightClick(View view) {
        System.out.println("Right Click");
    }

    //Accelerometer Intialization
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //System.out.println("Accuracy changed by: " + accuracy);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();;
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor acc = sensorEvent.sensor;

        if(acc.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float tempX = sensorEvent.values[0];
            float tempY = sensorEvent.values[1];

            double x = (double) Math.round(tempX * 100) / 100;
            double y = (double) Math.round(tempY * 100) / 100;

            //System.out.println("X Value: " + x);
            //System.out.println("Y Value: " + y);

            TextView accX = (TextView) findViewById(R.id.sensorX);
            String printAccX = "Accelerometer X: " + x;
            accX.setText(printAccX);

            TextView accY = (TextView) findViewById(R.id.sensorY);
            String printAccY = "Accelerometer Y: " + y;
            accY.setText(printAccY);

            TextView disX = (TextView) findViewById(R.id.disX);
            String printDisX = "Displacement X: " + displacement(x);
            disX.setText(printDisX);

            TextView disY = (TextView) findViewById(R.id.disY);
            String printDisY = "Displacement Y: " + displacement(y);
            disY.setText(printDisY);



            last_x = x;
            last_y = y;
        }
    }

    //Datagram Stuff
    private static void runUDPClient()  {
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

    private void sendMessage(final String message) {
        System.out.println("Datagram test");
        Thread thread = new Thread(new Runnable() {

            String stringData;

            @Override
            public void run() {
                System.out.println("Datagram run start");
                DatagramSocket ds = null;
                try {
                    InetAddress host = InetAddress.getByName("24.6.219.44");
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

    public void testClick(View view) {
        //runUDPClient();
        sendMessage("Android test");
    }

}
