package app;

import android.databinding.ObservableField;

public class Global {
    private static final Global INSTANCE = new Global();
    public ObservableField<String> userId = new ObservableField<>();

    private Global() {

    }

    public static Global getInstance() {
        return INSTANCE;
    }
}
