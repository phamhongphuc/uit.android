package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.parceler.Parcels;

import io.realm.Realm;
import module.socket._Socket;
import object.Project;
import object.Task;
import uit.group.manager.databinding.ActivityProjectDetailBinding;

//import object.Channel;

public class ProjectDetailActivity extends AppCompatActivity {
    private final State state = new State();
    private Realm realm = Realm.getDefaultInstance();
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        InitializeObject();
        InitializeDataBinding();
    }

    private void InitializeObject() {
        project = Parcels.unwrap(getIntent().getParcelableExtra("project"));
        realm.beginTransaction();
        project = realm.copyToRealmOrUpdate(project);
        realm.commitTransaction();
        state.Initialize(project);
    }

//    private void InitializeChannelsRecyclerView() {
//        ChannelRecyclerViewAdapter adapter = new ChannelRecyclerViewAdapter(project.getChannels());
//        RecyclerView recyclerView = findViewById(R.id.list_channels);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
//    }
//    private void InitializeTasksRecyclerView() {
//        TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter(State.project.get().getTasks());
//        RecyclerView recyclerView = findViewById(R.id.list_tasks);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
//    }

    private void InitializeDataBinding() {
        ActivityProjectDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_project_detail);
        binding.setSocket(_Socket.State.getInstance());
        binding.setProject(project);
        binding.setState(state);
    }

    public void go_back(View view) {
        finish();
    }

    public void go_projectTasks(View view) {
        Intent intent;

        intent = new Intent(getBaseContext(), TaskListActivity.class);
        intent.putExtra("project", Parcels.wrap(project));

        startActivity(intent);
    }

    public class State {
        public ObservableInt tasks_onhold = new ObservableInt();
        public ObservableInt tasks_ongoing = new ObservableInt();
        public ObservableInt tasks_complete = new ObservableInt();
        public ObservableInt channels = new ObservableInt();
        public ObservableInt members = new ObservableInt();

        public void Initialize(Project project) {
            tasks_onhold.set(
                    project.getTasks().where().equalTo("status", Task.ONHOLD).findAll().size()
            );
            tasks_ongoing.set(
                    project.getTasks().where().equalTo("status", Task.ONGOING).findAll().size()
            );
            tasks_complete.set(
                    project.getTasks().where().equalTo("status", Task.COMPLETE).findAll().size()
            );
            channels.set(
                    project.getChannels().size()
            );
            members.set(
                    project.getMembers().size()
            );
        }
    }
}
