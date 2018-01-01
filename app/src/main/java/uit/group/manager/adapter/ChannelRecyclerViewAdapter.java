package uit.group.manager.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import object.Channel;
import object.Project;
import uit.group.manager.BR;
import uit.group.manager.R;

public class ChannelRecyclerViewAdapter extends RealmRecyclerViewAdapter<Channel, ChannelRecyclerViewAdapter.ChannelViewHolder> {

    public ChannelRecyclerViewAdapter(final OrderedRealmCollection<Channel> channels) {
        super(channels, true);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
            }
        });
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.channel_item,
                parent, false
        );
        return new ChannelViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        Channel channel = getItem(position);
        holder.bind(channel);
    }
    //        final Project project = getItem(position);
//        holder.project = project;
//        //noinspection ConstantConditions
//        holder.projectView.set_text(project.getName());
//        if (inDeletionMode) {
//            holder.deletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//                        countersToDelete.add(project.getId());
//                    } else {
//                        countersToDelete.remove(project.getId());
//                    }
//                }
//            });
//        } else {
//            holder.deletedCheckBox.setOnCheckedChangeListener(null);
//        }
//        holder.deletedCheckBox.setVisibility(inDeletionMode ? View.VISIBLE : View.GONE);

    //    @Override
//    public long getItemId(int position) {
//        return getItem(position).getId();
//    }
    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        ChannelViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Channel channel) {
            binding.setVariable(BR.channel, channel);
            binding.executePendingBindings();
        }
    }
}
