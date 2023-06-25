package com.kurpas.tasks.view.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kurpas.tasks.App;
import com.kurpas.tasks.R;
import com.kurpas.tasks.models.TaskList;

public class CreateListActivity extends AppCompatActivity {

    Button saveListButton;
    TaskList taskList;
    EditText taskListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        taskListText = findViewById(R.id.taskListText);

        saveListButton = findViewById(R.id.saveListButton);
        saveListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskListText.getText().length() == 0) {
                    finish();
                }

                taskList.text = taskListText.getText().toString();
                taskList.timestamp = System.currentTimeMillis();

                App.getInstance().getTaskListDao().addTaskList(taskList);

                finish();
            }
        });
    }

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, CreateListActivity.class);
        caller.startActivity(intent);
    }
}