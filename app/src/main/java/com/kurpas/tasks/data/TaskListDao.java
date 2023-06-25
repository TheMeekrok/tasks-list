package com.kurpas.tasks.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kurpas.tasks.models.TaskList;

import java.util.List;

@Dao
public interface TaskListDao {
    @Query("SELECT * FROM task")
    LiveData<List<TaskList>> getAllTaskListsLive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTaskList(TaskList taskList);
}
