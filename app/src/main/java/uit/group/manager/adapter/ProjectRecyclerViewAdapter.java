package uit.group.manager.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import object.Project;
import uit.group.manager.BR;
import uit.group.manager.R;

public class ProjectRecyclerViewAdapter extends RealmRecyclerViewAdapter<Project, ProjectRecyclerViewAdapter.ProjectViewHolder> {

    public ProjectRecyclerViewAdapter(final OrderedRealmCollection<Project> projects) {
        super(projects, true);
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.project_item,
                parent, false
        );
        return new ProjectViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder projectViewHolder, int position) {
        Project project = getItem(position);
        projectViewHolder.bind(project);
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        ProjectViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Project project) {
            binding.setVariable(BR.project, project);
            binding.executePendingBindings();
        }
    }
}
