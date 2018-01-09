package uit.group.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ProjectEditActivity extends AppCompatActivity {
//    private DialogFragment newFragment = new ProjectCreateActivity.DatePickerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_edit);
    }

    public void showDatePickerDialog(View v) {
//        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

