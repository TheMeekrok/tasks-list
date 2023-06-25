package com.kurpas.tasks.view.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kurpas.tasks.models.Task;
import com.kurpas.tasks.models.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TasksListAdapter extends FragmentStateAdapter {

    private final List<Fragment> TaskLists = new ArrayList<>();
    private List<TaskList> taskList = new ArrayList<>();

    public TasksListAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return TaskListFragment.newInstance("dfsdfsdf");
    }

    @Override
    public int getItemCount() {
        return TaskLists.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<TaskList> taskListList) {
        taskList = taskListList;
        notifyDataSetChanged();
    }
}
