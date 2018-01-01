package app;

import android.databinding.ObservableField;

public class Global {
    private static final Global INSTANCE = new Global();

    public static Global getInstance() {
        return INSTANCE;
    }

    public ObservableField<String> socketStatus = new ObservableField<>();

    private Global() {

    }
}
