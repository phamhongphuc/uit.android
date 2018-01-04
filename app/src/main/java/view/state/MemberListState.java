package view.state;

import android.databinding.ObservableField;

import object.Project;

public class MemberListState {
    public final ObservableField<Project> project = new ObservableField<>();
    public MemberListState(){
//        projects.set(OldGlobal.projects.get());
    }
}
