package application;

import android.app.Application;

import io.realm.Realm;

public class ApplicationClass extends Application {
    // Private
    private static ApplicationState state;

    // Public
    public static ApplicationState getState() {
        if (state == null) state = new ApplicationState();
        return state;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

    }
}
