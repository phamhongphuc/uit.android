package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;

import module._Facebook;
import uit.group.manager.databinding.ActivityLoginBinding;
import view.fragment.FragmentAdapter;
import view.state.LoginState;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private LoginState state = new LoginState();

    private void checkLogin() {
        if (AccessToken.getCurrentAccessToken() == null) return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state.status.set("Goocssdfsdf");
        setContentView(R.layout.activity_login);

        InitializeDataBinding();
        InitializeViewPager();
    }

    private void InitializeViewPager() {
        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                state.status.set(String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        ViewPager viewPager = findViewById(R.id.loginPage);
        viewPager.addOnPageChangeListener(listener);
        viewPager.setAdapter(
                new FragmentAdapter(getSupportFragmentManager(), new int[]{
                        R.layout.fragment_login_1,
                        R.layout.fragment_login_2,
                })
        );
    }

    private void InitializeDataBinding() {
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setState(state);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        checkLogin();
    }

    public void facebookLogin(View view) {
        _Facebook.Login(this);
    }
}