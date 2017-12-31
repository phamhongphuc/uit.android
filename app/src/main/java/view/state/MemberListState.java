package view.state;

import android.databinding.ObservableField;

import app.Global;
import object.Project;

public class MemberListState {
    public final ObservableField<Project> project = new ObservableField<>();
    public MemberListState(){
        project.set(Global.project.get());
    }
}
