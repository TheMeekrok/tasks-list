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

    private RecyclerView recyclerView;
    private Button addTaskButton;
    private TabLayout tasksListTabs;
    private ViewPager viewPager;

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

        initToolbar();
        initTabLayout();

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

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);
    }

    private void initTabLayout() {
        tasksListTabs = findViewById(R.id.tasksListTabs);
        viewPager = findViewById(R.id.viewPager);

        tasksListTabs.setupWithViewPager(viewPager);
//        viewPager.adap

        tasksListTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabsCount = tasksListTabs.getTabCount();
                if (tab.getPosition() == 1) {
                    TabLayout.Tab newTab = tasksListTabs.newTab()
                            .setText(String.format("Список %s", tabsCount - 1));
                    tasksListTabs.addTab(newTab);
                    tasksListTabs.selectTab(newTab);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}