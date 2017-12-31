package view.state;

import android.databinding.ObservableField;

import app.Global;
import io.realm.RealmList;
import object.Project;
import object.Task;

public class TaskListState {
    public final ObservableField<RealmList<Task>> tasks = new ObservableField<>();

    public TaskListState() {
        tasks.set(Global.project.get().getTasks());
    }
}
