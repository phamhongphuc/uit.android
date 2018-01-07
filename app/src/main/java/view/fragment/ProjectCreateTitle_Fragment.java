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
import uit.group.manager.databinding.FragmentProjectCreateTitleBinding;

@SuppressLint("ValidFragment")
public class ProjectCreateTitle_Fragment extends Fragment {
    private final Project project;

    public ProjectCreateTitle_Fragment(Project project) {
        this.project = project;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProjectCreateTitleBinding binding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.fragment_project_create_title,
                        container, false
                );
        binding.setProject(project);
        return binding.getRoot();
    }
}
