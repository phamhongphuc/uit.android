package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import io.realm.Realm;
import object.Channel;
import uit.group.manager.databinding.ActivityChannelListBinding;

public class ChannelListActivity extends AppCompatActivity {
    private final State state = new State();
    private Realm realm = Realm.getDefaultInstance();
    private Channel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);

        InitializeObject();
        InitializeDataBinding();
    }

    private void InitializeObject() {
        channel = Parcels.unwrap(getIntent().getParcelableExtra("channel"));
        realm.beginTransaction();
        channel = realm.copyToRealmOrUpdate(channel);
        realm.commitTransaction();
        state.Initialize(channel);
    }

    private void InitializeDataBinding() {
        ActivityChannelListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_channel_list);
        binding.setChannel(channel);
        binding.setState(state);
    }

    public class State {
        public ObservableField<String> search = new ObservableField<>();

        public void Initialize(Channel channel) {

        }
    }

}
