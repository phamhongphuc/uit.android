package app;

import android.app.Application;

import com.facebook.FacebookSdk;

import io.realm.Realm;
import module._Facebook;
import module._Socket;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

        _Socket.Initialize();
        _Facebook.Initialize();

//        JSONObject JSON = new User("123", "java", "mail@mail").getJson();
//        _Socket.getSocket().emit("aJson", JSON);

        Realm.init(this);

//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
//                        .build()
//        );
    }
}