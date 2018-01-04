package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.Global;
import io.realm.Realm;
import module.facebook._Facebook;
import module.socket._Socket;
import module.socket._Socket_Project;
import object.User;
import uit.group.manager.databinding.ActivityMainBinding;
import view.recyclerViewAdapter.ProjectRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    private final Realm realm = Realm.getDefaultInstance();
    private final User user;
    private final Global global = Global.getInstance();

    MainActivity() {
        String userId = global.currentUserId.get();
        user = User.getUserById(userId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeDataBinding();
        InitializeRecyclerView();
    }

    private void InitializeDataBinding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setGlobal(global);
        binding.setUser(user);
        binding.setSocket(_Socket.getInstance());
    }

    private void InitializeRecyclerView() {
        ProjectRecyclerViewAdapter adapter = new ProjectRecyclerViewAdapter(user.getProjects());
        RecyclerView recyclerView = findViewById(R.id.list_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    public void facebookLogout(View view) {
        _Facebook.Logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void createProject(View view) {
        _Socket_Project.CreateProject(user.getId());
    }
}