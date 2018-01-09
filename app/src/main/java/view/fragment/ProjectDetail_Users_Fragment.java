package view.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import object.Project;
import uit.group.manager.R;
import uit.group.manager.databinding.FragmentProjectDetailUsersBinding;
import view.recyclerViewAdapter.UserRecyclerViewAdapter;

@SuppressLint("ValidFragment")
public class ProjectDetail_Users_Fragment extends Fragment {
    private final Project project;
    private View view;

    public ProjectDetail_Users_Fragment(Project project) {
        this.project = project;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        InitializeBinding(inflater, container);
        InitializeMembersRecyclerView();
        return view;
    }

    private void InitializeBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        FragmentProjectDetailUsersBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_project_detail_users,
                container, false
        );
        binding.setProject(project);
        view = binding.getRoot();
    }

    private void InitializeMembersRecyclerView() {
        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(project.getMembers());
        RecyclerView recyclerView = view.findViewById(R.id.project_list_members);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
