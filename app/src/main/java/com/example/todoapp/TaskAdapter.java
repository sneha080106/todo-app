package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<TaskModel> taskList;

    public TaskAdapter(ArrayList<TaskModel> taskList) {
        this.taskList = taskList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskText;

        public ViewHolder(View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskText.setText(taskList.get(position).getTask());
        TaskModel task = taskList.get(position);

        holder.taskText.setText(task.getTask());

        // ✅ Highlight selected
        if (task.isSelected()) {
            holder.itemView.setBackgroundColor(0xFFE0E0E0); // grey
        } else {
            holder.itemView.setBackgroundColor(0xFFFFFFFF); // white
        }

        // ✅ Long press to select
        holder.itemView.setOnLongClickListener(v -> {
            task.setSelected(!task.isSelected());
            notifyItemChanged(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}