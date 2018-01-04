package uit.group.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import view.fragmentAdapter.FragmentAdapter;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    private void InitializePages() {
        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                state.viewPagerIndex.set(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), new int[]{
                R.layout.fragment_tasks,
                R.layout.fragment_profile,
        });
        ViewPager viewPager = findViewById(R.id.viewPagerLogin);
        viewPager.addOnPageChangeListener(listener);
        viewPager.setAdapter(fragmentAdapter);
    }

    public void goBackToMemberList(View view) {
        startActivity(new Intent(getBaseContext(), MemberListActivity.class));
    }
}
