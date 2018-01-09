package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import object.Channel;
import uit.group.manager.databinding.ActivityChannelCreateBinding;

public class ChannelCreateActivity extends AppCompatActivity {
    private Channel channel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_create);

        InitializeUser();
        InitializeDataBinding();
    }

    private void InitializeUser() {
        channel = Parcels.unwrap(getIntent().getParcelableExtra("channel"));
    }

    private void InitializeDataBinding() {
        ActivityChannelCreateBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_channel_create);
        binding.setChannel(channel);
    }
}
