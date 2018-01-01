package app;

import android.databinding.ObservableField;

import object.User;

public class Global {
    private static final Global INSTANCE = new Global();

    public static Global getInstance() {
        return INSTANCE;
    }

    public ObservableField<String> socketStatus = new ObservableField<>();
    public ObservableField<User> currentUser = new ObservableField<>();

    private Global() {
        currentUser.set(null);
    }
}
