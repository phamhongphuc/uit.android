package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.Global;
import object.User;
import uit.group.manager.databinding.ActivityUserInformationBinding;

public class UserInformationActivity extends AppCompatActivity {
    private final User user = User.getUserById(
            Global.getInstance().userId.get()
    );
    private final State state = new State();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = R.layout.activity_user_information;
        setContentView(layout);
        ActivityUserInformationBinding binding = DataBindingUtil.setContentView(this, layout);

        state.Initialize(user);
        binding.setUser(user);
        binding.setState(state);
    }

    public void goBack(View view) {
        finish();
    }

    public class State {
        public ObservableInt projectsOwn = new ObservableInt();
        public ObservableInt projects = new ObservableInt();
        public ObservableInt tasksOwn = new ObservableInt();
        public ObservableInt tasks = new ObservableInt();
        public ObservableField<String> gender = new ObservableField<>();

        public void Initialize(User user) {
            projectsOwn.set(
                    user.getProjectsOwn().size()
            );
            projects.set(
                    user.getProjects().size()
            );
            tasksOwn.set(
                    user.getTasksOwn().size()
            );
            tasks.set(
                    user.getTasks().size()
            );
            gender.set(
                    user.getGender() ? "Name" : "Nữ"
            );
        }
    }
}
