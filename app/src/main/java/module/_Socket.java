package module;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.util.Log;

import com.facebook.AccessToken;

import org.json.JSONObject;

import java.net.URISyntaxException;

import app.Constant;
import app.Global;
import io.realm.Realm;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import object.User;

public class _Socket {
    public static ObservableField<String> status;
    private static Socket socket;
    private static Global global = Global.getInstance();

    public static void Initialize() {
        getSocket();
        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                global.socketStatus.set(Socket.EVENT_CONNECT_ERROR);
                Log.d("SOCKET EVENT:", global.socketStatus.get());
            }
        }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                global.socketStatus.set(Socket.EVENT_CONNECT);
                Log.d("SOCKET EVENT:", global.socketStatus.get());
            }
        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                global.socketStatus.set(Socket.EVENT_CONNECT_TIMEOUT);
                Log.d("SOCKET EVENT:", global.socketStatus.get());
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                global.socketStatus.set(Socket.EVENT_DISCONNECT);
                Log.d("SOCKET EVENT:", global.socketStatus.get());
            }
        });

        //        socket.on("UserData", new Emitter.Listener() {
        //            @Override
        //            public void call(Object... args) {
        //                JSONObject user = (JSONObject) args[0];
        //                String id = null;
        //                String email = null;
        //                String name = null;
        //                try {
        //                    id = (String) user.get("id");
        //                    name = (String) user.get("name");
        //                    email = (String) user.get("email");
        //                } catch (JSONException e) {
        //                    e.printStackTrace();
        //                }
        //                if (id != null) {
        //                    User.SetCurrentUser(id, name, email);
        //                }
        //            }
        //        });

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
            String accessToken = AccessToken.getCurrentAccessToken().getToken();
            String id = AccessToken.getCurrentAccessToken().getUserId();
            socket.emit("Get:User(accessToken, id)", accessToken, id, new Ack() {
                @SuppressLint("LongLogTag")
                @Override
                public void call(Object... args) {
                    Realm realm = Realm.getDefaultInstance();
                    if (args[0] != null) {
                        Log.d("SOCKET: ERROR", "Lỗi trả về" + args[0]);
                    } else {
                        JSONObject obj = (JSONObject) args[1];
//                        try {
//                            obj.put("projects", new JSONArray());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        realm.beginTransaction();
                        final User objectFromJson = realm.createOrUpdateObjectFromJson(User.class, obj);
                        realm.commitTransaction();
                    }
                }
            });
        }
    }
}
