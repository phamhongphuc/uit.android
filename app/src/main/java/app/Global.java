package app;

import android.databinding.ObservableField;

public class Global {
    private static final Global INSTANCE = new Global();
    public ObservableField<String> socketStatus = new ObservableField<>();
    public ObservableField<String> currentUserId = new ObservableField<>();
    private Global() {
        currentUserId.set(null);
    }

    public static Global getInstance() {
        return INSTANCE;
    }
}
