package com.kurpas.tasks.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.kurpas.tasks.models.Task;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    LiveData<Task> getAllTasksLive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

}
