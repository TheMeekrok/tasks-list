package com.kurpas.tasks.view.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TasksListAdapter extends FragmentPagerAdapter  {
    public TasksListAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TaskListFragment();
            case 1:
                return new TaskListFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
