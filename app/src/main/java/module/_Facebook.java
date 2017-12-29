package module;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

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
}