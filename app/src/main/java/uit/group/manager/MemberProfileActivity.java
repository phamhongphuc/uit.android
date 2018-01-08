package uit.group.manager;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;

import object.User;
import uit.group.manager.databinding.ActivityMemberProfileBinding;

public class MemberProfileActivity extends AppCompatActivity {
    private final State state = new State();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);

        InitializeUser();
        InitializeDataBinding();
    }

    private void InitializeUser() {
        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        state.Initialize(user);
    }

    private void InitializeDataBinding() {
        ActivityMemberProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_member_profile);
        binding.setUser(user);
        binding.setState(state);
    }

    public class State {
        @SuppressLint("SimpleDateFormat")
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        public ObservableField<String> gender = new ObservableField<>();
        public ObservableField<String> birthday = new ObservableField<>();

        public void Initialize(User user) {
            gender.set(
                    user.getGender() == null ? "???" : user.getGender() ? "Name" : "Nữ"
            );
            birthday.set(
                    user.getBirthdate() == null ? "???" : dateFormat.format(user.getBirthdate())
            );
        }
    }
}