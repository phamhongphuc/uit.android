package view.recyclerViewAdapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import object.Message;
import uit.group.manager.BR;
import uit.group.manager.R;

public class MessageRecyclerViewAdapter extends RealmRecyclerViewAdapter<Message, MessageRecyclerViewAdapter.MessageViewHolder> {

    public MessageRecyclerViewAdapter(final OrderedRealmCollection<Message> messages) {
        super(messages, true);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                messages.add(new Message());
            }
        });
        realm.close();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_message,
                parent, false
        );
        return new MessageViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder messageViewHolder, int position) {
        Message message = getItem(position);
        messageViewHolder.bind(message);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        MessageViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.binding = binding;
        }

        public void bind(Message message) {
            binding.setVariable(BR.message, message);
            binding.setVariable(BR.action, new MessageItemAction(message));
            binding.executePendingBindings();
        }
    }

    public class MessageItemAction {
        private Message message;

        MessageItemAction(Message message) {
            this.message = message;
        }

        public void selectMessage(View view) {
        }
    }
}
