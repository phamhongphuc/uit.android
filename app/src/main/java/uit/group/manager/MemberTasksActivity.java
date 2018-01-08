package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import object.User;
import uit.group.manager.databinding.ActivityMemberTasksBinding;
import view.fragment.TasksComplete_Fragment;
import view.fragmentAdapter.FragmentAdapter;

public class MemberTasksActivity extends AppCompatActivity {
    private User user;
    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_tasks);

        InitializeUser();
        InitializeDataBinding();
        InitializePages();
    }

    private void InitializePages() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(),
                new ArrayList<Fragment>() {{
                    add(new TasksComplete_Fragment(user));
                }}
        );
        viewPager = findViewById(R.id.viewPagerProjectCreate);
        viewPager.setAdapter(fragmentAdapter);
    }

    private void InitializeUser() {
        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
    }

    private void InitializeDataBinding() {
        ActivityMemberTasksBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_member_tasks);
        binding.setUser(user);
    }
}
