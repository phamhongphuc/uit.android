package uit.group.manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import module.callback.DateCallback;
import module.callback.VoidCallback;
import module.socket._Socket_Project;
import object.Project;
import view.fragment.ProjectCreateContent_Fragment;
import view.fragment.ProjectCreateTitle_Fragment;
import view.fragmentAbstract.ProjectFragment;
import view.fragmentAdapter.FragmentAdapter;

public class ProjectCreateActivity extends AppCompatActivity {
    private final ProjectFragment title_fragment = new ProjectCreateTitle_Fragment();
    private final ProjectFragment content_fragment = new ProjectCreateContent_Fragment();
    private final FragmentAdapter fragmentAdapter = new FragmentAdapter(
            getSupportFragmentManager(),
            new ArrayList<Fragment>() {{
                add(title_fragment);
                add(content_fragment);
            }}
    );
    public Project project;
    // Will delete
    public int day, month, year;
    private ViewPager viewPager;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);
        InitializeObject();
        InitializePages();
    }

    private void InitializeObject() {
        project = Parcels.unwrap(getIntent().getParcelableExtra("project"));
        userId = getIntent().getStringExtra("userId");
    }

    private void InitializePages() {
        final int[] index = {0};
        viewPager = findViewById(R.id.viewPagerProjectCreate);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                final ProjectFragment oldPage = (ProjectFragment) fragmentAdapter.getItem(index[0]);
                final ProjectFragment newPage = (ProjectFragment) fragmentAdapter.getItem(position);
                project = oldPage.project;
                newPage.setProject(project);
                index[0] = position;
            }
        });
    }

    public void createProjectNext(View view) {
        viewPager.setCurrentItem(1);
    }

    public void createProjectBack(View view) {
        viewPager.setCurrentItem(0);
    }

    public void createProjectDone(View view) {
        finish();
        project = ((ProjectFragment) fragmentAdapter.getItem(viewPager.getCurrentItem())).project;
        title_fragment.setProject(null);
        content_fragment.setProject(null);

        _Socket_Project.EditProject(project, userId, new VoidCallback() {
            @Override
            public void Response() {
                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        realm.copyToRealmOrUpdate(project);
                    }
                });
            }
        });
    }

    public void EditDeadline(View view) {
        DialogFragment deadlinePicker = new view.fragment.DatePickerFragment(new DateCallback() {
            @Override
            public void Response(Date date) {
                project.setDeadline(date);
                content_fragment.setProject(project);
            }
        });
        deadlinePicker.show(getSupportFragmentManager(), "deadlinePicker");
    }
}
