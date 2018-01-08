package view.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import object.User;
import uit.group.manager.R;
import uit.group.manager.databinding.FragmentTasksCompletedBinding;

@SuppressLint("ValidFragment")
public class TasksComplete_Fragment extends Fragment {
    private final User user;

    public TasksComplete_Fragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTasksCompletedBinding binding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.fragment_tasks_completed,
                        container, false
                );
        binding.setUser(user);
        return binding.getRoot();
    }
}
