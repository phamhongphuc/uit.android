package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import object.Project;
import object.User;
import uit.group.manager.adapter.ProjectRecyclerViewAdapter;
//import uit.group.manager.adapter.UserRecyclerViewAdapter;
import uit.group.manager.adapter.UserRecyclerViewAdapter;
import uit.group.manager.databinding.ActivityMainBinding;
import view.state.MainState;

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
        if (project != null) {
            UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(project.getMembers());
            RecyclerView recyclerView = findViewById(R.id.list_user);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
//            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL){
//                @Override
//                public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                    // super.onDraw(c, parent, state);
//                }
//            });
        }
    }

    private void InitializeDataBinding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_member_list);
        binding.setState(state);
    }
}
