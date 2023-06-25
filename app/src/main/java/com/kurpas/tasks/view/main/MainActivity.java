package com.kurpas.tasks.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kurpas.tasks.models.Task;
import com.kurpas.tasks.models.TaskList;
import com.kurpas.tasks.view.create.CreateListActivity;
import com.kurpas.tasks.R;
import com.kurpas.tasks.view.create.CreateTaskActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addTaskButton;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.createNewListItem) {
                    CreateListActivity.start(MainActivity.this);
                }

                return false;
            }
        });
    }

    private void initTabLayout() {
        TasksListAdapter tasksListAdapter = new TasksListAdapter(this);
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getTaskListLiveData().observe(this, new Observer<List<TaskList>>() {
            @Override
            public void onChanged(List<TaskList> taskList) {
                tasksListAdapter.setItems(taskList);
            }
        });

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(tasksListAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(String.format("%d", position));
        });
        tabLayoutMediator.attach();

        tabLayout.selectTab(tabLayout.getTabAt(1));
    }
}