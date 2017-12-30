package uit.group.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;

import module._Facebook;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager = CallbackManager.Factory.create();

    private void checkLogin() {
        if (AccessToken.getCurrentAccessToken() == null) return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        checkLogin();
    }

    /**
     * Facebook Login Button
     *
     * @param view components.button
     */
    public void facebookLogin(View view) {
        _Facebook.Login(this);
    }
}