package com.kurpas.tasks.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kurpas.tasks.R;
import com.kurpas.tasks.models.Task;

import java.util.List;

public class TaskListFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_list_fragment, container, false);

        recyclerView = view.findViewById(R.id.tasksRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this.getContext(),
                RecyclerView.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this.getContext(),
                DividerItemDecoration.HORIZONTAL
        ));

        TasksAdapter adapter = new TasksAdapter();
        recyclerView.setAdapter(adapter);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setItems(tasks);
            }
        });

        return view;
    }
}
