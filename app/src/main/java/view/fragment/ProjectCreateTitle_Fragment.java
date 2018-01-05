package view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uit.group.manager.R;
import uit.group.manager.databinding.FragmentProjectCreateTitleBinding;

public class ProjectCreateTitle_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProjectCreateTitleBinding binding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.fragment_project_create_title,
                        container, false
                );
        binding.setSss(new State());
        return binding.getRoot();
    }

    public class State {
        public ObservableField<String> status = new ObservableField<>();

        State() {
            status.set("");
        }
    }
}
