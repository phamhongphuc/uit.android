package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.MainState;
import uit.group.manager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainState state = new MainState();

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
        binding.setState(state);
    }

//    startActivity(new Intent(this, MainActivity.class));
}