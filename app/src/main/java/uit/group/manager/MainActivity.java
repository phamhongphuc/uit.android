package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.App;
import components.Button;
import uit.group.manager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeDataBinding();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void InitializeDataBinding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setState(App.state);
    }

    public void clicker(View view) {
        ((Button) view).setActive();
    }

    public void update(View view) {
    }

    public void check(View view) {
        ((Button) view).toggleActive();
    }
}