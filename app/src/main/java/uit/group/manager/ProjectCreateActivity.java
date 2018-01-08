package uit.group.manager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
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
    private String userId;
    private ViewPager viewPager;
    private DialogFragment newFragment = new DatePickerFragment();

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
                        realm.close();
                    }
                });
            }
        });

    }

    public void showDatePickerDialog(View v) {
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @SuppressLint("SimpleDateFormat")
        private static final SimpleDateFormat dateFormat =
                new SimpleDateFormat("dd-MM-yyyy");

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            Date date = calendar.getTime();
            String str = dateFormat.format(date);
            Log.d("DATE", str);
        }
    }
}
