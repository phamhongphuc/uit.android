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
import object.Task;
import uit.group.manager.BR;
import uit.group.manager.R;

public class TaskRecyclerViewAdapter extends RealmRecyclerViewAdapter<Task, TaskRecyclerViewAdapter.TaskViewHolder> {

    public TaskRecyclerViewAdapter(final OrderedRealmCollection<Task> tasks) {
        super(tasks, true);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                tasks.add(new Task());
            }
        });
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.task_item,
                parent, false
        );
        return new TaskViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder taskViewHolder, int position) {
        Task task = getItem(position);
        taskViewHolder.bind(task);
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
    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        TaskViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.binding = binding;
        }

        public void bind(Task task) {
            binding.setVariable(BR.task, task);
            binding.setVariable(BR.action, new TaskItemAction(task));
            binding.executePendingBindings();
        }
    }

    public class TaskItemAction {
        private Task task;

        TaskItemAction(Task task) {
            this.task = task;
        }

        public void selectTask(View view) {
        }
    }
}
