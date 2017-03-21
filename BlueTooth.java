public class BlueTooth {
    //sets up bluetooth connection with mobile app
    //recieves accelerometer values from app, outputs coordinates

    private static double timeInterval = .05; //in seconds
    private double xPos = null;
    private double yPos = null;
    private double zPos = null;

    protected static double position (double acceleration) {
        double position = acceleration * Math.pow(timeInterval,3);
        System.out.println(position);
        return position;
    }

    public void coordinateSet (double xAccelerometer, double yAccelerometer, double zAccelerometer ) {
        xPos = position(xAccelerometer);
        yPos = position(yAccelerometer);
        zPos = position(zAccelerometer);
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public double getZ() {
        return zPos;
    }


}