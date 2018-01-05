package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.CallbackManager;

import app.Global;
import module.callback.CallbackString;
import module.facebook._Facebook;
import module.socket._Socket;
import uit.group.manager.databinding.ActivityLoginBinding;
import view.fragmentAdapter.FragmentAdapter;
import view.state.LoginState;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private LoginState loginState = new LoginState();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitializeDataBinding();
        InitializePages();
        InitializeListener();

        _Facebook.Login(this);
    }

    private void InitializeListener() {
        _Facebook.InitializeLogin(new CallbackString() {
            @Override
            public void Response(String userId) {
                Global.getInstance().userId.set(userId);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void InitializePages() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), new int[]{
                R.layout.fragment_login_1,
                R.layout.fragment_login_2,
        });

        ViewPager viewPager = findViewById(R.id.viewPagerLogin);
        viewPager.setAdapter(fragmentAdapter);
    }

    private void InitializeDataBinding() {
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setState(loginState);
        binding.setGlobal(Global.getInstance());
        binding.setSocket(_Socket.State.getInstance());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void facebookLoginButton(View view) {
        _Facebook.Login(this);
    }
}