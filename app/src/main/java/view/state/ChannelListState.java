package view.state;

import android.databinding.ObservableField;

import app.Global;
import io.realm.RealmList;
import object.Channel;

/**
 * Created by SofiaJetson on 12/31/2017.
 */

public class ChannelListState {
    public final ObservableField<RealmList<Channel>> channels = new ObservableField<>();

    public ChannelListState() {
        channels.set(Global.project.get().getChannels());

    }
}
