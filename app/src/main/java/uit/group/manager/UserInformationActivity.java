package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.Global;
import object.User;
import uit.group.manager.databinding.ActivityUserInformationBinding;

public class UserInformationActivity extends AppCompatActivity {
    final int layout = R.layout.activity_user_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);

        ActivityUserInformationBinding binding = DataBindingUtil.setContentView(this, layout);

        binding.setUser(User.getUserById(
                Global.getInstance().userId.get()
        ));
    }

    public void goBack(View view) {
        finish();
    }
}
