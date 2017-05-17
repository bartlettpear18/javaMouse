package com.company;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by Joel.Bartlett18 on 5/15/2017.
 */
public class Mouse {
    //make singleton for robot mouse here
    private static Robot bot = null;

    static {
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }



    /**
     * Move mouse
     * @param x
     * @param y
     */
    public static void move( int x, int y) throws AWTException {
        //get current position
        //add displacement to current position
        //move mouse
        Point currentPos = MouseInfo.getPointerInfo().getLocation();
        int curX = (int) currentPos.getX();
        int curY = (int) currentPos.getY();
        System.out.print(y);
        System.out.print(x);


        bot.mouseMove(curX + x,curY - y);
        System.out.println("move successful");
        System.out.println("New coordinates: " + MouseInfo.getPointerInfo().getLocation());
    }

    public static void leftClick() {
        int mask = InputEvent.BUTTON1_MASK;
        bot.mousePress(mask);
        bot.mouseRelease(mask);    }

}
