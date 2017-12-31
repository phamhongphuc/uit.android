package view.state;

import android.databinding.ObservableField;

import app.Global;
import io.realm.RealmList;
import object.Project;

/**
 * Created by SofiaJetson on 12/31/2017.
 */

public class ProjectListState {
    public final ObservableField<RealmList<Project>> projects = new ObservableField<>();

    public ProjectListState() {
        projects.set(Global.user.get().getProjects());
    }
}
