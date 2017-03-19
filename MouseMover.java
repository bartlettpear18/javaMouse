import java.awt.Robot;
import java.util.Random;

public class MouseMover {
    public static void main (String[] args) {
        MouseSingleton tmp = MouseSingleton.getInstance();
        tmp.test();
        tmp.blueCheck(3.0);
    }
}