package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.parceler.Parcels;

import io.realm.Realm;
import module.socket._Socket;
import object.Project;
import uit.group.manager.databinding.ActivityProjectDetailBinding;
import view.recyclerViewAdapter.UserRecyclerViewAdapter;

//import object.Channel;

public class ProjectDetailActivity extends AppCompatActivity {
    private Project project;
    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        InitializeObject();
        InitializeDataBinding();

        InitializeChannelsRecyclerView();
        InitializeTasksRecyclerView();
        InitializeMembersRecyclerView();
    }

    private void InitializeObject() {
        project = Parcels.unwrap(getIntent().getParcelableExtra("project"));
        realm.beginTransaction();
        project = realm.copyToRealmOrUpdate(project);
        realm.commitTransaction();
    }

    private void InitializeChannelsRecyclerView() {
//        ChannelRecyclerViewAdapter adapter = new ChannelRecyclerViewAdapter(State.project.get().getChannels());
//        RecyclerView recyclerView = findViewById(R.id.list_channels);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
    }

    private void InitializeTasksRecyclerView() {
//        TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter(State.project.get().getTasks());
//        RecyclerView recyclerView = findViewById(R.id.list_tasks);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
    }

    private void InitializeMembersRecyclerView() {
        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(project.getMembers());
        RecyclerView recyclerView = findViewById(R.id.list_members);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void InitializeDataBinding() {
        ActivityProjectDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_project_detail);
        binding.setSocket(_Socket.State.getInstance());

//        binding.setSocket(state);
//        binding.setGlobal(Global.getInstance());
    }

    public void back(View view) {
        finish();
    }
}
