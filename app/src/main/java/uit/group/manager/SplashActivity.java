package uit.group.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(
                this,
                AccessToken.getCurrentAccessToken() == null ?
                        LoginActivity.class :
                        MainActivity.class
        ));
        finish();
    }
}
