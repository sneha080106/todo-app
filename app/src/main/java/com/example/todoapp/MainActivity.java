package com.example.todoapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TaskModel> taskList;
    private TaskAdapter adapter;
    private EditText inputTask;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTask = findViewById(R.id.inputTask);
        addButton = findViewById(R.id.addButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        taskList = new ArrayList<>();
        adapter = new TaskAdapter(taskList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String taskText = inputTask.getText().toString().trim();
            if (!taskText.isEmpty()) {
                taskList.add(new TaskModel(taskText));
                adapter.notifyDataSetChanged();
                inputTask.setText("");
            }
        });
    }
}