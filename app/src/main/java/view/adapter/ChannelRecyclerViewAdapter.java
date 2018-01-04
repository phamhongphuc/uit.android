package view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import object.Channel;
import uit.group.manager.BR;
import uit.group.manager.R;

public class ChannelRecyclerViewAdapter extends RealmRecyclerViewAdapter<Channel, ChannelRecyclerViewAdapter.ChannelViewHolder> {

    public ChannelRecyclerViewAdapter(final OrderedRealmCollection<Channel> channels) {
        super(channels, true);
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_channel,
                parent, false
        );
        return new ChannelViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder channelViewHolder, int position) {
        Channel channel = getItem(position);
        channelViewHolder.bind(channel);
    }
    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        ChannelViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.binding = binding;
        }

        public void bind(Channel channel) {
            binding.setVariable(BR.channel, channel);
            binding.setVariable(BR.action, new ChannelItemAction(channel));
            binding.executePendingBindings();
        }
    }

    public class ChannelItemAction {
        private Channel channel;

        ChannelItemAction(Channel channel) {
            this.channel = channel;
        }

        public void selectChannel(View view) {
        }
    }

}
