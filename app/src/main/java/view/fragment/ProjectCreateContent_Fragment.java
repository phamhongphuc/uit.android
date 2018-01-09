package view.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import module.converter._Converter;
import object.Project;
import uit.group.manager.ProjectCreateActivity;
import uit.group.manager.R;
import uit.group.manager.databinding.FragmentProjectCreateContentBinding;
import view.fragmentAbstract.ProjectFragment;

@SuppressLint("ValidFragment")
public class ProjectCreateContent_Fragment extends ProjectFragment {
    private final State state = new State();
    public FragmentProjectCreateContentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        project = ((ProjectCreateActivity) (getActivity())).project;
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_project_create_content,
                container, false
        );
        state.Initialize();
        binding.setProject(project);
        binding.setState(state);
        return binding.getRoot();
    }

    @Override
    public void setProject(Project project) {
        binding.setProject(project);
        state.Initialize();
    }

    public class State {
        public ObservableField<String> deadline = new ObservableField<>();
        public ObservableField<String> createdate = new ObservableField<>();

        public void Initialize() {
            deadline.set(
                    _Converter.Date(project.getDeadline())
            );
            createdate.set(
                    _Converter.Date(project.getCreatedate())
            );
        }
    }
}
