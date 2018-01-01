package module;

import android.util.Log;

import com.facebook.AccessToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import app.Constant;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import object.User;

public class _Socket {
    public static String status;
    private static Socket socket;

    public static void Initialize() {
        getSocket();
        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                status = Socket.EVENT_CONNECT_ERROR;
                Log.d("SOCKET EVENT:", status);
            }
        }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                status = Socket.EVENT_CONNECT;
                Log.d("SOCKET EVENT:", status);
            }
        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                status = Socket.EVENT_CONNECT_TIMEOUT;
                Log.d("SOCKET EVENT:", status);
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                status = Socket.EVENT_DISCONNECT;
                Log.d("SOCKET EVENT:", status);
            }
        });


        socket.on("UserData", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject user = (JSONObject) args[0];
                String id = null;
                String email = null;
                String name = null;
                try {
                    id = (String) user.get("id");
                    name = (String) user.get("name");
                    email = (String) user.get("email");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (id != null) {
                    User.SetCurrentUser(id, name, email);
                }
            }
        });
        socket.connect();
    }

    public static Socket getSocket() {
        if (socket == null) {
            try {
                socket = IO.socket(Constant.SERVER_URL);
            } catch (URISyntaxException e) {
                socket = null;
            }
        }
        return socket;
    }

    /**
     * Sau khi gọi cái này, máy chủ sẽ emit ngược về "UserData"
     */
    public static void GetUserByAccessToken() {
        if (AccessToken.getCurrentAccessToken() != null) {
//            JSONObject json = new JSONObject();
//            try {
//                json.put("accessToken", AccessToken.getCurrentAccessToken().getToken());
//                json.put("id", AccessToken.getCurrentAccessToken().getUserId());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            String accessToken = AccessToken.getCurrentAccessToken().getToken();
            String id = AccessToken.getCurrentAccessToken().getUserId();
            socket.emit("Get:User(accessToken, id)", accessToken, id, new Ack() {
                @Override
                public void call(Object... args) {
                    int a = 5;
                }
            });
        }
    }
}
