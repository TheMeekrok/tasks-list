package com.kurpas.tasks.view.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.kurpas.tasks.App;
import com.kurpas.tasks.R;
import com.kurpas.tasks.models.Task;

public class CreateTaskActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "CreateTaskActivity.EXTRA_TASK";

    Task task;
    EditText editText;
    Button saveTaskButton;

    public static void start(Activity caller, Task task) {
        Intent intent = new Intent(caller, CreateTaskActivity.class);

        if (task != null) {
            intent.putExtra(EXTRA_TASK, task);
        }

        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_task);

        initToolbar();

        editText = findViewById(R.id.taskText);

        saveTaskButton = findViewById(R.id.saveTaskButton);
        saveTaskButton.setOnClickListener(view -> {
            if (editText.getText().length() == 0) {
                finish();
            }

            task.text = editText.getText().toString();
            task.isDone = false;
            task.timestamp = System.currentTimeMillis();

            if (getIntent().hasExtra(EXTRA_TASK)) {
                App.getInstance().getTaskDao().updateTask(task);
            }
            else {
                App.getInstance().getTaskDao().addTask(task);
            }

            editText.setText("sdfdsfsdf");
            finish();
        });

        if (getIntent().hasExtra(EXTRA_TASK)) {
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            editText.setText(task.text);
        }
        else {
            task = new Task();
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle(R.string.create_task_title);
    }
}
