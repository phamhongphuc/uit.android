package uit.group.manager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import view.fragment.FragmentAdapter;

public class ProjectCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);
    }

    private void InitializePages() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), new int[]{
                R.layout.fragment_project_create_1
        });
        ViewPager viewPager = findViewById(R.id.viewPagerLogin);
        viewPager.setAdapter(fragmentAdapter);
    }
}
