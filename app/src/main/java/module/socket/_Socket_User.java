package module.socket;

import android.util.Log;

import com.facebook.AccessToken;

import org.json.JSONObject;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.socket.client.Ack;
import io.socket.client.Socket;
import object.User;

public class _Socket_User {
    private static final Socket socket = _Socket.getSocket();

    public static void GetUserById(String userId, final User.Callback callback) {
        socket.emit("Get:User(userId)", userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.d("REALM", "Không có user, request lỗi");
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    User user = realm.createOrUpdateObjectFromJson(User.class, obj);
                    realm.commitTransaction();

                    callback.Response(user);
                }
            }
        });
    }

    public static void GetUserByAccessToken(@Nullable AccessToken accessToken, final User.Callback callback) {
        AccessToken token = accessToken != null ? accessToken : AccessToken.getCurrentAccessToken();

        if (token != null) {
            String accessTokenString = token.getToken();
            socket.emit("Get:User(accessToken)", accessTokenString, new Ack() {
                @Override
                public void call(Object... args) {
                    if (args[0] != null) {
                        Log.d("SOCKET: ERROR", "Lỗi trả về" + args[0]);
                    } else {
                        final JSONObject obj = (JSONObject) args[1];

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        final User user = realm.createOrUpdateObjectFromJson(User.class, obj);
                        realm.commitTransaction();

                        callback.Response(user);
                    }
                }
            });
        }
    }
}
