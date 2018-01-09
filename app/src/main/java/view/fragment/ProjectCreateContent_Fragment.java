package view.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import module.converter._Converter;
import object.Project;
import uit.group.manager.ProjectCreateActivity;
import uit.group.manager.R;
import uit.group.manager.databinding.FragmentProjectCreateContentBinding;
import view.fragmentAbstract.ProjectFragment;

@SuppressLint("ValidFragment")
public class ProjectCreateContent_Fragment extends ProjectFragment {
    public FragmentProjectCreateContentBinding binding;
    private final State state = new State();

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

        public void EditDeadline(View view) {
            final DatePickerFragment deadlinePicker = new DatePickerFragment() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    super.onDateSet(view, year, month, day);
                    project.setDeadline(getDate());
                    Initialize();
                }
            };
            deadlinePicker.show(getFragmentManager(), "deadlinePicker");
        }
    }
}
