package module.facebook;

import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.LoginManager;

import java.util.Collections;

import module.socket._Socket_User;
import object.User;

public class _Facebook {
    private static AccessTokenTracker accessTokenTracker = null;

    public static void InitializeLogin(final User.Callback user_Callback) {
        if (accessTokenTracker == null) {
            accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    _Socket_User.GetUserByAccessToken(
                            currentAccessToken,
                            user_Callback
                    );
                }
            };
        }
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