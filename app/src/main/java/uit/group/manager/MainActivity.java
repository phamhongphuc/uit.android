package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;
import module._Facebook;
import object.Project;
import uit.group.manager.adapter.ProjectRecyclerViewAdapter;
import uit.group.manager.databinding.ActivityMainBinding;
import view.state.MainState;

public class MainActivity extends AppCompatActivity {

    private MainState state = new MainState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeDataBinding();
        InitializeRecyclerView();
    }

    private void InitializeDataBinding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setState(state);
    }

    private void InitializeRecyclerView() {
        ProjectRecyclerViewAdapter adapter = new ProjectRecyclerViewAdapter(state.projects);
        RecyclerView recyclerView = findViewById(R.id.list_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    public void facebookLogout(View view) {
        _Facebook.Logout();
    }

    public void createProject(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                try {
                    Number id = realm.where(Project.class).max("id");
                    int nextId = id == null ? 1 : id.intValue() + 1;
                    Project project = new Project(nextId, "name123123");
                    realm.copyToRealm(project);
                } catch (RealmPrimaryKeyConstraintException ignored) {
                    Log.d("REALM: ", "Trùng ID");
                }
            }
        });
    }
}