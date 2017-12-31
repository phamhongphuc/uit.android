package module;

import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.LoginManager;

import java.util.Collections;

public class _Facebook {
    /**
     * Hàm này được khai báo luôn trong App
     */
    public static void Initialize() {
        _Socket.GetUserByAccessToken();
        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                _Socket.GetUserByAccessToken();
            }
        };
    }

    public static void Login(AppCompatActivity activity) {
        LoginManager.getInstance().logInWithReadPermissions(
                activity,
                Collections.singletonList("email")
        );
    }

    public static void Logout() {
        LoginManager.getInstance().logOut();
    }
}