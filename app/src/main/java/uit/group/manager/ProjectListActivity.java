package uit.group.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;
import module.object.Project;

public class ProjectListActivity extends AppCompatActivity {

    private ListView lvProjects;
    private ArrayAdapter<Project> adapter;
    private Realm realmDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        lvProjects = findViewById(R.id.lvProjects);
        Realm.init(this);
        realmDB = Realm.getDefaultInstance();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getTask());
        lvProjects.setAdapter(adapter);

    }

    private void reloadDB() {
        // dua du lieu vao listview
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getTask());
        lvProjects.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private RealmResults<Project> getTask() {
        return realmDB.where(Project.class).findAll();
    }

    public void addProject(View v) {
        reloadDB();
    }

    public void createProject(View view) {
    }
}
