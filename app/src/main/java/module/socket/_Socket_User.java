package module.socket;

import android.annotation.SuppressLint;
import android.util.Log;

import com.facebook.AccessToken;

import org.json.JSONObject;

import app.Global;
import io.realm.Realm;
import io.socket.client.Ack;
import object.User;

public class _Socket_User {
    private static io.socket.client.Socket socket = _Socket.getSocket();

    public static void GetUserById(String userId) {
        socket.emit("Get:User(id)", userId, new Ack() {
            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("REALM", "Không có user, request lỗi");
                } else {
                    JSONObject obj = (JSONObject) args[1];
                    realm.beginTransaction();
                    final User user = realm.createOrUpdateObjectFromJson(User.class, obj);
                    realm.commitTransaction();
                }
            }
        });
    }

    public static void LoginUserByAccessToken() {
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
                        realm.beginTransaction();
                        final User user = realm.createOrUpdateObjectFromJson(User.class, obj);
                        realm.commitTransaction();

                        Global.getInstance().currentUserId.set(user.getId());
                    }
                }
            });
        }
    }
}
