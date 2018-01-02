package view.adapter;

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
import object.User;
import uit.group.manager.BR;
import uit.group.manager.R;

public class UserRecyclerViewAdapter extends RealmRecyclerViewAdapter<User, UserRecyclerViewAdapter.UserViewHolder> {

    public UserRecyclerViewAdapter(final OrderedRealmCollection<User> users) {
        super(users, true);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                users.add(new User());
            }
        });
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.user_item,
                parent, false
        );
        return new UserViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int position) {
        User user = getItem(position);
        userViewHolder.bind(user);
    }
    //        final Project projects = getItem(position);
//        holder.projects = projects;
//        //noinspection ConstantConditions
//        holder.projectView.set_text(projects.getName());
//        if (inDeletionMode) {
//            holder.deletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//                        countersToDelete.add(projects.getId());
//                    } else {
//                        countersToDelete.remove(projects.getId());
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
