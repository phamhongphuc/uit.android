package uit.group.manager.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import uit.group.manager.R;
import uit.group.manager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainState state = new MainState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
    }

    private void Initialize() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        state.status.set("123123");
        binding.setState(state);

        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                state.accessToken.set(currentAccessToken);
                if (currentAccessToken != null) {
                    state.status.set(currentAccessToken.getUserId() + " log");
                }
            }
        };
        state.accessToken.set(AccessToken.getCurrentAccessToken());

        try {
            final Socket socket = IO.socket("http://10.0.2.2:5");

            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    /*state.status.set("timeout");*/
                }
            }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    socket.emit("foo", "hi");
                    // socket.disconnect();
                }
            }).on("event", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    // do something
                }
            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}