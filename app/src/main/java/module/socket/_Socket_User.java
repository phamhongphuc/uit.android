package module.socket;

import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;

import org.json.JSONObject;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.socket.client.Ack;
import io.socket.client.Socket;
import module.callback._Callback;
import object.User;

public class _Socket_User {
    private static final Socket socket = _Socket.getSocket();

    public static void GetUserById(String userId, final _Callback callback) {
        socket.emit("Get:User(userId)", userId, new Ack() {
            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("REALM", "Không có user, request lỗi");
                } else {
                    JSONObject obj = (JSONObject) args[1];
                    realm.beginTransaction();
                    User user = realm.createOrUpdateObjectFromJson(User.class, obj);
                    realm.commitTransaction();
                    callback.Response(user);
                }
            }
        });
    }

    public static void GetUserByAccessToken(@Nullable AccessToken accessToken, final _Callback callback) {
        AccessToken token = accessToken != null ? accessToken : AccessToken.getCurrentAccessToken();

        if (token != null) {
            String accessTokenString = token.getToken();
            socket.emit("Get:User(accessToken)", accessTokenString, new Ack() {
                @Override
                public void call(Object... args) {
                    Realm realm = Realm.getDefaultInstance();
                    if (args[0] != null) {
                        Log.d("SOCKET: ERROR", "Lỗi trả về" + args[0]);
                    } else {
                        final JSONObject obj = (JSONObject) args[1];
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(@NonNull Realm realm) {
                                final User user = realm.createOrUpdateObjectFromJson(User.class, obj);
                                callback.Response(user.getId());
                            }
                        });
                    }
                }
            });
        }
    }
}
