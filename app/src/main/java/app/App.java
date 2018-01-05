package app;

import android.app.Application;

import com.facebook.FacebookSdk;

import io.realm.Realm;
import module.socket._Socket;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

        _Socket.Initialize();
//        _Facebook.Initialize();

        Realm.init(this);
    }
}