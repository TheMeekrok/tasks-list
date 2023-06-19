package com.kurpas.tasks.view.main;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.kurpas.tasks.App;
import com.kurpas.tasks.R;
import com.kurpas.tasks.models.Task;
import com.kurpas.tasks.view.create.CreateTaskActivity;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    private SortedList<Task> sortedList;

    public TasksAdapter() {
        sortedList = new SortedList<>(Task.class, new SortedList.Callback<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.isDone && !o2.isDone) {
                    return -1;
                }

                if (!o1.isDone && o2.isDone) {
                    return 1;
                }

                return (int) (o2.timestamp - o1.timestamp);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Task oldItem, Task newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Task item1, Task item2) {
                return item1.id == item2.id;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.task_list_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Task> tasks) {
        sortedList.replaceAll(tasks);
    }

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
