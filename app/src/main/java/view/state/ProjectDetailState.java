package view.state;

import android.databinding.ObservableField;

import object.Project;

public class ProjectDetailState {
    public final ObservableField<Project> project = new ObservableField<>();

    public ProjectDetailState() {
//        projects.set(OldGlobal.projects.get());
    }
}
