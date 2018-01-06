package module.facebook;

import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.LoginManager;

import java.util.Collections;

import module.callback._Callback;
import module.socket._Socket_User;

public class _Facebook {
    public static void InitializeLogin(final _Callback userId_Callback) {
        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                _Socket_User.GetUserByAccessToken(
                        currentAccessToken,
                        userId_Callback
                );
            }
        };
    }

    public static void Login(AppCompatActivity activity) {
        LoginManager
                .getInstance()
                .logInWithReadPermissions(activity, Collections.singletonList("email"));
    }

    public static void Logout() {
        LoginManager.getInstance().logOut();
    }
}