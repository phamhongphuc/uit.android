package module;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.LoginManager;

import java.util.Collections;

import uit.group.manager.LoginActivity;

public class _Facebook {
    /**
     * Hàm này được khai báo luôn trong App
     */
    public static void Initialize() {
        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                _Socket.EmitAccessToken(currentAccessToken == null ? "null" : currentAccessToken.getToken());
            }
        };
    }

    public static void Login(LoginActivity loginActivity) {
        LoginManager.getInstance().logInWithReadPermissions(
                loginActivity,
                Collections.singletonList("email")
        );
    }

    public static void Logout() {
        LoginManager.getInstance().logOut();
    }
}