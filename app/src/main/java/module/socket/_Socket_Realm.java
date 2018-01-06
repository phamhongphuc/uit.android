package module.socket;

import android.util.Log;

import java.util.ArrayList;

import module.callback._Callback;
import object.User;

public class _Socket_Realm {
    private static final io.socket.client.Socket socket = _Socket.getSocket();

    public static void Pull(String userId) {
        _Socket_User.GetUserById(userId, new _Callback() {
            @Override
            public void Response(User user) {
                _Socket_Project.GetProjectIdsByUserId(user.getId(), new _Callback() {
                    @Override
                    public void Response(ArrayList<Integer> projects) {
                        Log.d("PROJECTS", projects.toString());
                    }
                });
            }
        });
    }
}
