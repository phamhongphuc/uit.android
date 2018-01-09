package uit.group.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.parceler.Parcels;

import module.socket._Socket_Task;
import object.Project;
import object.Task;
import object.User;
import view.recyclerViewAdapter.TaskRecyclerViewAdapter;

public class TaskListActivity extends RealmActivity {
    private Project project;
    private int projectId = 1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        InitializeObject();
        InitializeRecyclerView();
    }

    private void InitializeObject() {
        project = Parcels.unwrap(getIntent().getParcelableExtra("project"));
        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        realm.beginTransaction();
        project = realm.copyToRealmOrUpdate(project);
        projectId = project.getId();
        realm.commitTransaction();
    }

    private void InitializeRecyclerView() {
        final RecyclerView recyclerView;

        recyclerView = findViewById(R.id.list_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskRecyclerViewAdapter(project.getTasks()));
        recyclerView.setHasFixedSize(true);
    }

    public void go_back(View view) {
        finish();
    }

    public void go_createTask(View view) {
        _Socket_Task.CreateTask(projectId, new Task.Callback() {
            @Override
            public void Response(Task task) {
                Intent intent;

                intent = new Intent(getBaseContext(), TaskCreateActivity.class);
                intent.putExtra("task", Parcels.wrap(task));

                startActivity(intent);
            }
        });
    }
}
