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
import uit.group.manager.databinding.FragmentProjectCreateContentBinding;

public class ProjectCreateContent_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FragmentProjectCreateContentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_project_create_content,
                container,
                false);
        binding.setNewproject(new Infor());
        return binding.getRoot();
    }

    public class Infor {
        public ObservableField<String> str = new ObservableField<>();

        Infor() {
            str.set("game Aladdin");
        }
    }
}
