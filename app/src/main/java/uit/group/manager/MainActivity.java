package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import java.net.URISyntaxException;

import application.ApplicationClass;
import application.ApplicationState;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import uit.group.manager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private ApplicationState state = ApplicationClass.getState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Initialize();
    }

    private void Initialize() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setState(state);

        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                state.accessToken.set(currentAccessToken);
                if (currentAccessToken != null) {
                    state.status.set(currentAccessToken.getUserId() + " log");
                }
            }
        };
//        state.accessToken.set(AccessToken.getCurrentAccessToken());

        try {
            final Socket socket = IO.socket("http://10.0.2.2:5/");

            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
//                    state.status.set("Timeout, không thể kết nối");
                }
            }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    socket.emit("foo", "Tín hiệu này được gửi đi từ điện thoại");
                    state.status.set("Đã gửi đi một tín hiệu");
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    state.status.set("Đã ngắt kết nối");
                }
            }).on("eventName", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    state.status.set("Nhận được một event trả về.");
                }
            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Có kết quả trả về activity này ~ (khi đã đăng nhập xong)
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void check(View view) {
    }

    public void update(View view) {
    }
}