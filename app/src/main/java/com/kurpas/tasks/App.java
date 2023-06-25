package com.kurpas.tasks;

import android.app.Application;

import androidx.room.Room;

import com.kurpas.tasks.data.AppDatabase;
import com.kurpas.tasks.data.TaskDao;
import com.kurpas.tasks.data.TaskListDao;
import com.kurpas.tasks.view.create.CreateTaskActivity;

public class App extends Application {

    private AppDatabase appDatabase;
    private TaskDao taskDao;
    private TaskListDao taskListDao;

    private static App instance;
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks")
                .allowMainThreadQueries()
                .build();

        taskDao = appDatabase.taskDao();
        taskListDao = appDatabase.taskListDao();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public TaskListDao getTaskListDao() {
        return taskListDao;
    }
}
