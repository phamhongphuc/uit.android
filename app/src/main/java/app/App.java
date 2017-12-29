package app;

import android.app.Application;

import com.facebook.FacebookSdk;

import io.realm.Realm;
import module._Facebook;
import module._Socket;

public class App extends Application {
    // Private
    public static State state = new State();

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

        _Socket.Initialize();
        _Facebook.Initialize();

        Realm.init(this);
    }
}