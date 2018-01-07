package app;

public class Global {
    private static final Global INSTANCE = new Global();

    private Global() {

    }

    public static Global getInstance() {
        return INSTANCE;
    }
}
