package uit.group.manager;

import android.os.Bundle;
import android.view.View;

import org.parceler.Parcels;

import object.Project;

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

    public void go_back(View view) {
        finish();
    }
}
