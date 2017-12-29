package uit.group.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.util.Collections;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private Intent Main;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Main = new Intent(this, MainActivity.class);
        CheckLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        CheckLogin();
    }

    public void facebookLogin(View view) {
        LoginManager.getInstance().logInWithReadPermissions(this, Collections.singletonList("email"));
    }

    private void CheckLogin() {
        if (AccessToken.getCurrentAccessToken() == null) return;
        startActivity(Main);
        finish();
    }
}
