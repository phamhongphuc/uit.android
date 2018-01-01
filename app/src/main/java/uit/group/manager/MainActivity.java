package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.Realm;
import object.User;
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

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User user = realm.where(User.class).findFirst();
        realm.commitTransaction();
        if (user != null) {
            ProjectRecyclerViewAdapter adapter = new ProjectRecyclerViewAdapter(user.getProjects());
            RecyclerView recyclerView = findViewById(R.id.list_project);
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
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setState(state);
    }

    public void debug(View view) {
        int a = 1;
    }

//    startActivity(new Intent(this, MainActivity.class));
}