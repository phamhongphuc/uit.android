package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import object.Project;
import uit.group.manager.databinding.ActivityProjectDetailBinding;
import view.state.ProjectDetailState;

public class ProjectDetailActivity extends AppCompatActivity {

    private ProjectDetailState state = new ProjectDetailState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        InitializeDataBinding();

        Intent intent = getIntent();
        int projectId = intent.getIntExtra("projectId", -1);
        if(projectId != -1){
            state.project.set(Project.getProjectById(projectId));
        }
    }

    private void InitializeDataBinding() {
        ActivityProjectDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_project_detail);
        binding.setState(state);
    }

    public void goBackToMainActivity(View view) {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }
}
