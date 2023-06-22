package com.kurpas.tasks.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.kurpas.tasks.R;
import com.kurpas.tasks.models.Task;
import com.kurpas.tasks.view.create.CreateTaskActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tasksView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this,
                DividerItemDecoration.HORIZONTAL
        ));

        addTaskButton = findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(view -> {
            CreateTaskActivity.start(MainActivity.this, null);
        });

        TasksAdapter adapter = new TasksAdapter();
        recyclerView.setAdapter(adapter);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getListLiveData().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setItems(tasks);
            }
        });
    }
}