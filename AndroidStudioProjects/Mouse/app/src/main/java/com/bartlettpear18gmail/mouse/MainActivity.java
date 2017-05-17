package com.bartlettpear18gmail.mouse;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.bartlettpear18gmail.mouse.ClientSend.sendMessage;
import static com.bartlettpear18gmail.mouse.Position.displacement;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {
    //Accelerometer Set up
    private SensorManager mSensorManager;
    private Sensor mAccel;

    /**
    //Wifi instance variables
    private final IntentFilter intentFilter = new IntentFilter();
    private WifiP2pManager.Channel mChannel;
    private WifiP2pManager mManager;
    private boolean isWifiP2pEnabled = false;
    private WiFiDirectBroadcastReceiver receiver = null;
    private boolean retryChannel = false;


    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Accelerometer Set up
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);

        /**
        //Wifi Direct Set up
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
         */

        //mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        //mChannel = mManager.initialize(this, getMainLooper(), null);

        //runUDPClient();
        //(new Thread(new ClientSend())).start();


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //System.out.println("Accuracy changed by: " + accuracy);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        //unregisterReceiver(receiver);
    }

    @Override
    public void onResume(){
        super.onResume();;
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);
        //receiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
        //registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor acc = sensorEvent.sensor;

        if(acc.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float tempX = sensorEvent.values[0];
            float tempY = sensorEvent.values[1];
            float tempZ = sensorEvent.values[2];

            double x;
            double y;
            double z = (double) Math.round(tempZ * 100) / 100;
            if(Math.abs(z)>1) {
                x =0;
                y=0;
            } else {
                x = (double) Math.round(tempX * 100) / 100;
                y = (double) Math.round(tempY * 100) / 100;


            }


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

        }
    }



    // Clicks
    public void leftClick (View view) { System.out.println("Left Click"); }

    public void rightClick(View view) {
        System.out.println("Right Click");
    }

    public static void testClick(View view) {
        //runUDPClient();
        sendMessage("Android test");
    }






}
