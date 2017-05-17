package com.company;

import java.awt.*;

import static com.company.Mouse.leftClick;
import static com.company.Mouse.move;


public class Main {

    public static void main(String[] args) {
        //create socket connection, constantly check for packets
        //perform static methods in main based off packets

        /*
        while(true) {
            runUDPServer();
        } */

        try{
            move(20,20);
        } catch (AWTException e){
            e.printStackTrace();
            System.out.println("Failed here");
        }
        leftClick();
    }

}
