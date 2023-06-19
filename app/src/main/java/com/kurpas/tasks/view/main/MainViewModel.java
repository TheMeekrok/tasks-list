package com.kurpas.tasks.view.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kurpas.tasks.App;
import com.kurpas.tasks.models.Task;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final LiveData<List<Task>> listLiveData = App.getInstance().getTaskDao().getAllTasksLive();

    public LiveData<List<Task>> getListLiveData() {
        return listLiveData;
    }
}
