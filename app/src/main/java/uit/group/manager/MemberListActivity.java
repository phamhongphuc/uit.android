package uit.group.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.Realm;
import object.Project;
import view.recyclerViewAdapter.UserRecyclerViewAdapter;
import view.state.MainState;

//import view.a.UserRecyclerViewAdapter;

public class MemberListActivity extends AppCompatActivity {

    private MainState state = new MainState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        InitializeDataBinding();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Project project = realm.where(Project.class).findFirst();
        realm.commitTransaction();
        realm.close();

        if (project != null) {
            UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(project.getMembers());
            RecyclerView recyclerView = findViewById(R.id.list_user);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
        }
    }

    private void InitializeDataBinding() {
//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_member_list);
//        binding.setState(state);
    }

    public void goBackToProjectDetail(View view) {
        startActivity(new Intent(getBaseContext(), ProjectDetailActivity.class));
        finish();
    }
}
