package view.fragmentAbstract;

import android.support.v4.app.Fragment;

import object.Project;

public abstract class ProjectFragment extends Fragment {
    public Project project;

    public abstract void setProject(Project project);
}
