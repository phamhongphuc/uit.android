package uit.group.manager;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.parceler.Parcels;

import java.util.Date;

import io.realm.Realm;
import module.callback.DateCallback;
import module.converter._Converter;
import module.socket._Socket_Task;
import object.Task;
import uit.group.manager.databinding.ActivityTaskCreateBinding;

public class TaskCreateActivity extends AppCompatActivity {
    private Task task;
    private ActivityTaskCreateBinding binding;
    private State state = new State();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
        InitializeObject();
        InitializeDataBinding();
    }


    private void InitializeObject() {
        task = Parcels.unwrap(getIntent().getParcelableExtra("task"));
//        Realm realm;
//        realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        task = realm.copyToRealmOrUpdate(task);
//        realm.commitTransaction();
    }

    private void InitializeDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_create);
        binding.setTask(task);
        binding.setState(state);
    }

    public void createTaskDone(View view) {
        finish();
        binding.setTask(new Task());

        _Socket_Task.EditTask(task, new Task.Callback() {
            @Override
            public void Response(final Task task) {
                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        realm.copyToRealmOrUpdate(task);
                    }
                });
            }
        });
    }


    public void EditTaskDeadline(View view) {
        DialogFragment deadlinePicker = new view.fragment.DatePickerFragment(new DateCallback() {
            @Override
            public void Response(Date date) {
                task.setDeadline(date);
                state.Initialize();
            }
        });
        deadlinePicker.show(getSupportFragmentManager(), "deadlinePicker");
    }

    public class State {
        public ObservableField<String> deadline = new ObservableField<>();
        public ObservableField<String> createdate = new ObservableField<>();
        public ObservableField<String> email = new ObservableField<>();

        public void Initialize() {
            deadline.set(
                    _Converter.Date(task.getDeadline())
            );
            createdate.set(
                    _Converter.Date(task.getCreatedate())
            );
        }
    }
}

