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
                users.add(new User("123", "abc", ""));
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
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = getItem(position);
        holder.bind(user);
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
    class UserViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        UserViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(User user) {
            binding.setVariable(BR.user, user);
            binding.executePendingBindings();
        }
    }
}
