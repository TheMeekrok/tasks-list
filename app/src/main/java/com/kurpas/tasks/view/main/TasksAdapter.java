package com.kurpas.tasks.view.main;

import android.app.Activity;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;

import com.kurpas.tasks.App;
import com.kurpas.tasks.R;
import com.kurpas.tasks.models.Task;
import com.kurpas.tasks.view.create.CreateTaskActivity;

public class TasksAdapter extends RecyclerView.Adapter<> {

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        boolean isInitState;
        CheckBox toggleCompletedCheckBox;

        Button deleteTaskButton;

        Task task;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskText = itemView.findViewById(R.id.taskText);

            itemView.setOnClickListener(view -> {
                CreateTaskActivity.start((Activity) itemView.getContext(), task);
            });

            toggleCompletedCheckBox = itemView.findViewById(R.id.toggleCompletedCheckBox);
            toggleCompletedCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
                if (!isInitState) {
                    task.isDone = b;
                    App.getInstance().getTaskDao().updateTask(task);
                }

                updateStrokeOut();
            });

            deleteTaskButton = itemView.findViewById(R.id.deleteTaskButton);
            deleteTaskButton.setOnClickListener(view -> {
                App.getInstance().getTaskDao().deleteTask(task);
            });
        }

        public void bind(Task task) {
            this.task = task;

            taskText.setText(task.text);

            isInitState = true;
            toggleCompletedCheckBox.setChecked(task.isDone);
            isInitState = false;
        }

        private void updateStrokeOut() {
            if (task.isDone) {
                taskText.setPaintFlags(taskText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            else {
                taskText.setPaintFlags(taskText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
