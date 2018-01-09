package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;

import org.parceler.Parcels;

import app.Global;
import io.realm.Realm;
import module.facebook._Facebook;
import module.socket._Socket;
import object.User;
import uit.group.manager.databinding.ActivityLoginBinding;
import view.fragmentAdapter.FragmentAdapter;
import view.state.LoginState;

public class LoginActivity extends RealmActivity {
    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private LoginState loginState = new LoginState();
    private Boolean isLogin = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitializeDataBinding();
        InitializePages();
        InitializeListener();
    }

    private void InitializeListener() {
        _Facebook.InitializeLogin(new User.Callback() {
            @Override
            public void Response(User user) {
                Intent intent;

                intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("user", Parcels.wrap(user));

                Realm realm;
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(user);
                realm.commitTransaction();
                realm.close();

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
        isLogin = false;
    }

    public void facebookLoginButton(View view) {
        if (AccessToken.getCurrentAccessToken() != null) {
            _Facebook.Logout();
        }
        if (!isLogin) {
            _Facebook.Login(this);
            isLogin = true;
        }
    }
}