package uit.group.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;
import object.Project;

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
        ///... thuc hien viec them project
        /// sau khi them xong, quay tro lai man hinh nay, va hien ra cung voi project moi
        reloadDB();
    }
}
