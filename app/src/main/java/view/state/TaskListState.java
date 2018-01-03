package view.state;

import android.databinding.ObservableField;

import io.realm.RealmList;
import module.object.Task;

public class TaskListState {
    public final ObservableField<RealmList<Task>> tasks = new ObservableField<>();

    public TaskListState() {
    }
}
