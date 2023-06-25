package com.kurpas.tasks.view.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kurpas.tasks.App;
import com.kurpas.tasks.models.Task;
import com.kurpas.tasks.models.TaskList;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final LiveData<List<Task>> listLiveData = App.getInstance().getTaskDao().getAllTasksLive();
    private final LiveData<List<TaskList>> taskListLiveData = App.getInstance().getTaskListDao().getAllTaskListsLive();

    public LiveData<List<Task>> getListLiveData() {
        return listLiveData;
    }
    public LiveData<List<TaskList>> getTaskListLiveData() {return taskListLiveData; }
}
