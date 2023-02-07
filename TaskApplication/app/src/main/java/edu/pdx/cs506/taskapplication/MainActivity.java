package edu.pdx.cs506.taskapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button name = findViewById(R.id.main_signin);
//        name.setOnClickListener((view -> {
//            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
//            startActivity(intent);
//        }));
    }
}