package com.example.todoapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TaskModel> taskList;
    private TaskAdapter adapter;
    private EditText inputTask;
    private Button addButton, deleteBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        inputTask = findViewById(R.id.inputTask);
        addButton = findViewById(R.id.addButton);
        deleteBtn = findViewById(R.id.deleteBtn);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // ✅ Initialize list
        taskList = new ArrayList<>();

        // ✅ Load saved data
        loadData();

        // ✅ Setup RecyclerView
        adapter = new TaskAdapter(taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // ➕ Add Task
        addButton.setOnClickListener(v -> {
            String taskText = inputTask.getText().toString().trim();

            if (!taskText.isEmpty()) {
                taskList.add(new TaskModel(taskText));
                adapter.notifyItemInserted(taskList.size() - 1); // 👈 IMPORTANT
                inputTask.setText("");
                saveData();
            }
        });

        // ❌ Delete Selected
        deleteBtn.setOnClickListener(v -> {
            for (int i = taskList.size() - 1; i >= 0; i--) {
                if (taskList.get(i).isSelected()) {
                    taskList.remove(i);
                }
            }
            adapter.notifyDataSetChanged();
            saveData();
        });
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray = new JSONArray();

        for (TaskModel task : taskList) {
            jsonArray.put(task.getTask());
        }

        editor.putString("tasks", jsonArray.toString());
        editor.apply();
    }

    private void loadData() {
        try {
            String data = sharedPreferences.getString("tasks", "");

            if (data != null && !data.equals("")) {
                JSONArray jsonArray = new JSONArray(data);

                for (int i = 0; i < jsonArray.length(); i++) {
                    taskList.add(new TaskModel(jsonArray.getString(i)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}