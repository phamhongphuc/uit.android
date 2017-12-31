package view.state;

import android.databinding.ObservableField;

import app.Global;
import object.Project;

public class ProjectDetailState {
    public final ObservableField<Project> project = new ObservableField<>();

    public ProjectDetailState() {
        project.set(Global.project.get());
    }
}
