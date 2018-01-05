package uit.group.manager;

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

import java.util.Calendar;
import java.util.Date;

import view.fragment.FragmentAdapter;

public class ProjectCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);
        InitializePages();
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
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

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
            month++;
            Date getDate;
            String input_date = day + "/" + month + "/" + year;
            ///Them ngay vao bien dang Date
        /*    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
            try {
                getDate=format.parse(input_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

            Log.d("GET DATE AFTER CHOOSING", day + "/" + month + "/" + year);
        }
    }
}
