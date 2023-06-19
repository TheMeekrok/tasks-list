package com.kurpas.tasks.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kurpas.tasks.models.Task;
import com.kurpas.tasks.models.TaskList;

@Database(entities = {Task.class, TaskList.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    public abstract TaskListDao taskListDao();

}
