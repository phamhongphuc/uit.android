package app;

import android.databinding.ObservableField;

import object.User;

public class Global {
    private static final Global INSTANCE = new Global();
    public final ObservableField<User> user = new ObservableField<>();

    private Global() {

    }

    public static Global getInstance() {
        return INSTANCE;
    }
}
