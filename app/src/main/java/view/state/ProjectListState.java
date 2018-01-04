package view.state;

import android.databinding.ObservableField;

import io.realm.RealmList;
import object.Project;

public class ProjectListState {
    public final ObservableField<RealmList<Project>> projects = new ObservableField<>();

    public ProjectListState() {
        // projects.set(OldGlobal.user.get().getProjects()
    }
}
