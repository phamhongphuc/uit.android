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
import object.Project;
import uit.group.manager.BR;
import uit.group.manager.ProjectDetailActivity;
import uit.group.manager.R;

public class ProjectRecyclerViewAdapter
        extends RealmRecyclerViewAdapter<Project, ProjectRecyclerViewAdapter.ProjectViewHolder> {

    public ProjectRecyclerViewAdapter(final OrderedRealmCollection<Project> projects) {
        super(projects, true);
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_project,
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
            binding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.binding = binding;
        }

        public void bind(Project project) {
            binding.setVariable(BR.project, project);
            binding.setVariable(BR.action, new Action(project));
            binding.executePendingBindings();
        }
    }

    public class Action {
        private Project project;

        Action(Project project) {
            this.project = project;
        }

        public void selectProject(View view) {
            Intent intent = new Intent(view.getContext(), ProjectDetailActivity.class);
            intent.putExtra("project", Parcels.wrap(project));
            view.getContext().startActivity(intent);
        }
    }
}
