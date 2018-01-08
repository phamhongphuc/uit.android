package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.parceler.Parcels;

import io.realm.Realm;
import module.facebook._Facebook;
import module.socket._Socket;
import module.socket._Socket_Project;
import object.Project;
import object.User;
import uit.group.manager.databinding.ActivityMainBinding;
import view.recyclerViewAdapter.ProjectRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    private User user;
    private String userId;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        InitializeUser();
        InitializeDataBinding();
        InitializeRecyclerView();
    }

    private void InitializeUser() {
        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        realm.beginTransaction();
        user = realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        userId = user.getId();
    }

    private void InitializeDataBinding() {
        final ActivityMainBinding binding;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setSocket(_Socket.State.getInstance());
        binding.setUser(user);
    }

    private void InitializeRecyclerView() {
        final RecyclerView recyclerView;

        recyclerView = findViewById(R.id.list_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProjectRecyclerViewAdapter(user.getProjects()));
        recyclerView.setHasFixedSize(true);
    }

    public void facebookLogout(View view) {
        _Facebook.Logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();

        Realm realm = Realm.getDefaultInstance();
        realm.commitTransaction();
        realm.deleteAll();
        realm.beginTransaction();
        realm.close();
    }

    public void createProject(View view) {
        _Socket_Project.CreateProject(user, new Project.CallbackWithUser() {
            @Override
            public void Response(final Project project, final User user) {
                Intent intent;

                intent = new Intent(getBaseContext(), ProjectCreateActivity.class);
                intent.putExtra("project", Parcels.wrap(project));
                intent.putExtra("userId", userId);

                startActivity(intent);
            }
        });
    }

    public void goToUserInfomation(View view) {
        final Intent intent;

        intent = new Intent(this, UserInformationActivity.class);
        intent.putExtra("user", Parcels.wrap(user));

        startActivity(intent);
    }
}