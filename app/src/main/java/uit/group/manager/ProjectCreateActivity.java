package uit.group.manager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import view.fragmentAdapter.FragmentAdapter;

public class ProjectCreateActivity extends AppCompatActivity {
    public int day, month, year;
    private DialogFragment newFragment = new DatePickerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);
        InitializePages();

        /*TextView createdate = findViewById(R.id.createdate);
        Date curentdate = Calendar.getInstance().getTime();
        String day          = (String) DateFormat.format("dd",   curentdate); // 20
        String month  = (String) DateFormat.format("MM",   curentdate); // 06
        String year         = (String) DateFormat.format("yyyy", curentdate);

        createdate.setText(String.format("%s/%s/%s", day, month, year));*/
    }

    private void InitializePages() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), new int[]{
                R.layout.fragment_project_create_1,
                R.layout.fragment_project_create_2
        });
        ViewPager viewPager = findViewById(R.id.viewPagerProjectCreate);
        viewPager.setAdapter(fragmentAdapter);
    }

    public void showDatePickerDialog(View v) {
        newFragment.show(getSupportFragmentManager(), "datePicker");
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
