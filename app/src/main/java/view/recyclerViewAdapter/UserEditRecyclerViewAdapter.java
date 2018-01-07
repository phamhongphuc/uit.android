package view.recyclerViewAdapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import object.User;
import uit.group.manager.BR;
import uit.group.manager.R;

public class UserEditRecyclerViewAdapter extends RealmRecyclerViewAdapter<User, UserEditRecyclerViewAdapter.UserViewHolder> {

    public UserEditRecyclerViewAdapter(final OrderedRealmCollection<User> users) {
        super(users, true);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_user_add,
                parent, false
        );
        return new UserViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int position) {
        User user = getItem(position);
        userViewHolder.bind(user);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        UserViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.binding = binding;
        }

        public void bind(User user) {
            binding.setVariable(BR.user, user);
            binding.setVariable(BR.action, new UserItemAction(user));
            binding.executePendingBindings();
        }
    }

    public class UserItemAction {
        private User user;

        UserItemAction(User user) {
            this.user = user;
        }

        public void selectUser(View view) {
        }
    }
}