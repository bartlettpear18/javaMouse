public class Singleton {
    //
    private static Singleton cursor = new Singleton();
    private Singleton() { }
    
    public static Singleton getInstance(){
        return cursor;
    }
    protected static void test() {
        System.out.println("Hi");
    }
}

