package com.kurpas.tasks.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.kurpas.tasks.R;
import com.kurpas.tasks.models.Task;
import com.kurpas.tasks.view.create.CreateTaskActivity;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button addTaskButton;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTaskButton = findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(view -> {
            CreateTaskActivity.start(MainActivity.this, null);
        });

        initToolbar();
        initTabLayout();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);
    }

    private void initTabLayout() {
        TasksListAdapter tasksListAdapter = new TasksListAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(tasksListAdapter);
        viewPager.setCurrentItem(0);
    }
}