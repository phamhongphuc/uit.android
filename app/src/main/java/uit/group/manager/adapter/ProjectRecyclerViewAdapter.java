package uit.group.manager.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import object.Project;
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.project_item,
                parent, false
        );
        return new ProjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        final Project project = getItem(position);
        holder.project = project;
        //noinspection ConstantConditions
        holder.projectName.setText(project.getName());
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
    }

//    @Override
//    public long getItemId(int position) {
//        return getItem(position).getId();
//    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView projectName;
        Project project;

        ProjectViewHolder(View view) {
            super(view);
            projectName = view.findViewById(R.id.textProject);
        }
    }
}
