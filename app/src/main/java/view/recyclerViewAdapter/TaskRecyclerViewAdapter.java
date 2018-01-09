package view.recyclerViewAdapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import object.Task;
import uit.group.manager.BR;
import uit.group.manager.R;
import uit.group.manager.TaskDetailActivity;

public class TaskRecyclerViewAdapter
        extends RealmRecyclerViewAdapter<Task, TaskRecyclerViewAdapter.TaskViewHolder> {

    public TaskRecyclerViewAdapter(final OrderedRealmCollection<Task> tasks) {
        super(tasks, true);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_task,
                parent, false
        );
        return new TaskViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder taskViewHolder, int position) {
        Task task = getItem(position);
        taskViewHolder.bind(task);
    }

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
            binding.setVariable(BR.action, new Action(task));
            binding.executePendingBindings();
        }
    }

    public class Action {
        private Task task;

        Action(Task task) {
            this.task = task;
        }

        public void selectTask(View view) {
            Intent intent = new Intent(view.getContext(), TaskDetailActivity.class);
            intent.putExtra("task", Parcels.wrap(task));
            view.getContext().startActivity(intent);
        }
    }
}
