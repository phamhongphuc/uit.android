package view.fragment;


import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import object.Project;
import uit.group.manager.R;
import uit.group.manager.databinding.FragmentProjectCreateContentBinding;

@SuppressLint("ValidFragment")
public class ProjectCreateContent_Fragment extends Fragment {
    private final Project project;

    public ProjectCreateContent_Fragment(Project project) {
        this.project = project;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FragmentProjectCreateContentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_project_create_content,
                container,
                false);
        binding.setProject(project);
        return binding.getRoot();
    }

    public class State {

    }
}
