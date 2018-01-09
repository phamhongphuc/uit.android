package uit.group.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
    }

    public void goBack(View view) {
        finish();
    }
}
