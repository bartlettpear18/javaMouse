package com.bartlettpear18gmail.mouse;

/**
 * Created by Joel.Bartlett18 on 5/14/2017.
 */
public class Position {
    public static double lastAcceleration = 0;
    public static double lastVelocity = 0;
    public static double lastPosition = 0;

    public static double currentAcceleration;
    public static double currentVelocity;
    public static double currentPosition;

    public static final double deltaTime = 0.1;

    public static double parallelIntegral(double aFinal, double aInitial) {
        double area;
        area = ((aFinal + aInitial)/2)*deltaTime;
        return area;
    }

    public static double displacement(double accl) {
        int displacement = 0;

        currentVelocity = parallelIntegral(accl, lastAcceleration);
        lastAcceleration = accl;

        currentPosition = parallelIntegral(currentVelocity, lastVelocity);
        lastVelocity = currentVelocity;

        displacement = (int) Math.floor(currentPosition - lastPosition);
        lastPosition = currentPosition;

        return displacement;
    }


}
