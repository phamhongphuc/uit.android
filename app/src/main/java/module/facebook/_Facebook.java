package module.facebook;

import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.internal.ImageRequest;
import com.facebook.login.LoginManager;

import java.util.Collections;

import module.callback.CallbackString;
import module.socket._Socket_User;

public class _Facebook {
    public static void InitializeLogin(final CallbackString userId_Callback) {
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

    public static String GetPicture(String userId) {
        String url;
        // TODO: Kiểm tra kết quả trả về của chỗ này
        url = ImageRequest.getProfilePictureUri(userId, 200, 200).toString();
//        final ObservableField<String> url = new ObservableField<>();
//        Bundle params = new Bundle();
//        params.putBoolean("redirect", false);
//
//        final GraphRequest request = new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/" + fbid + "/picture",
//                params,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    @Override
//                    public void onCompleted(GraphResponse Response) {
//                        try {
//                            url.set((String) Response.getJSONObject().getJSONObject("data").get("url"));
//                        } catch (Exception e) {
//                            url.set("");
//                        }
//                    }
//                }
//        );
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                GraphResponse graphResponse = request.executeAndWait();
//            }
//        });
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return url.get();
        return url;
    }
}