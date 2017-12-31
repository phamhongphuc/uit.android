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
import object.Project;
import uit.group.manager.BR;
import uit.group.manager.R;

public class ProjectRecyclerViewAdapter extends RealmRecyclerViewAdapter<Project, ProjectRecyclerViewAdapter.ProjectViewHolder> {

    public ProjectRecyclerViewAdapter(final OrderedRealmCollection<Project> projects) {
        super(projects, true);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                projects.add(new Project(123, "name123123"));
            }
        });
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
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        Project project = getItem(position);
        holder.bind(project);
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
