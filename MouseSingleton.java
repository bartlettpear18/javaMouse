public class MouseSingleton {
    //
    private static MouseSingleton cursor = new MouseSingleton();
    private MouseSingleton() { }
    
    public static MouseSingleton getInstance(){
        return cursor;
    }
    protected static void test() {
        System.out.println("Hi");
    }
}

