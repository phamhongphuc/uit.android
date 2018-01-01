package view.state;

import android.databinding.ObservableField;

import io.realm.RealmList;
import object.Channel;

public class ChannelListState {
    public final ObservableField<RealmList<Channel>> channels = new ObservableField<>();

    public ChannelListState() {
//        channels.set(OldGlobal.project.get().getChannels());
    }
}
