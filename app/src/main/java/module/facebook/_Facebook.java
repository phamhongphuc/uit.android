package module.facebook;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

import java.util.Collections;

import module.socket._Socket_User;

public class _Facebook {
    /**
     * Hàm này được khai báo luôn trong App
     */
    public static void Initialize() {
        _Socket_User.LoginUserByAccessToken();
        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                _Socket_User.LoginUserByAccessToken();
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

    public static String GetPicture(String fbid) {
        final ObservableField<String> url = new ObservableField<>();
        Bundle params = new Bundle();
        params.putBoolean("redirect", false);

        final GraphRequest request = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + fbid + "/picture",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        try {
                            url.set((String) response.getJSONObject().getJSONObject("data").get("url"));
                        } catch (Exception e) {
                            url.set("");
//                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GraphResponse graphResponse = request.executeAndWait();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return url.get();
    }
}