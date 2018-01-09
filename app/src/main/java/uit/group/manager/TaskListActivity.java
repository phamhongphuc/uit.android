package uit.group.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.parceler.Parcels;

import object.Project;
import view.recyclerViewAdapter.TaskRecyclerViewAdapter;

public class TaskListActivity extends RealmActivity {
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
    }

    private void InitializeObject() {
        project = Parcels.unwrap(getIntent().getParcelableExtra("project"));
        realm.beginTransaction();
        project = realm.copyToRealmOrUpdate(project);
        realm.commitTransaction();
    }

    private void InitializeRecyclerView() {
        final RecyclerView recyclerView;

        recyclerView = findViewById(R.id.list_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskRecyclerViewAdapter(project.getTasks()));
        recyclerView.setHasFixedSize(true);
    }

    public void go_back(View view) {
        finish();
    }

    public void go_createTask(View view) {
        Intent intent;

        intent = new Intent(getBaseContext(), TaskCreateActivity.class);
        intent.putExtra("project", Parcels.wrap(project));
//        intent.putExtra("userId", userId);

        startActivity(intent);
    }
}
