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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import view.fragment.ProjectCreateContent_Fragment;
import view.fragment.ProjectCreateTitle_Fragment;
import view.fragmentAdapter.FragmentAdapter;

public class ProjectCreateActivity extends AppCompatActivity {
    public int day, month, year;
    private DialogFragment newFragment = new DatePickerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);
        InitializePages();
    }

    private void InitializePages() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(),
                new ArrayList<Fragment>() {{
                    add(new ProjectCreateTitle_Fragment());
                    add(new ProjectCreateContent_Fragment());
                }}
        );
        ViewPager viewPager = findViewById(R.id.viewPagerProjectCreate);
        viewPager.setAdapter(fragmentAdapter);
    }

    public void showDatePickerDialog(View v) {
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void createProjectNext(View view) {
        ViewPager viewPager = findViewById(R.id.viewPagerProjectCreate);
        viewPager.setCurrentItem(1);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
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
