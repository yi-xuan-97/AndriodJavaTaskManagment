package edu.pdx.cs506.androidtask;

import static edu.pdx.cs506.androidtask.MainActivity.mongoDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    private EditText title;
    private EditText time;
    private EditText location;
    private EditText detail;
    private TextView err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        title = findViewById(R.id.task_title);
        time = findViewById(R.id.task_time);
        location = findViewById(R.id.task_location);
        detail = findViewById(R.id.task_detail);
        err = findViewById(R.id.error_addtask);

        Button add_task = findViewById(R.id.addtask_btn);
        add_task.setOnClickListener((view -> {
            String stitle = title.getText().toString();
            String stime = time.getText().toString();
            String slocation = location.getText().toString();
            String sdetail = detail.getText().toString();

            if(!checkDate(stime))
                err.setText("Wrong date format!");

            if(checkDate(stime)){
                MainActivity.mongoCollection_TaskList.insertOne(new Document("_id", MainActivity.credential+"_"+stime)
                                .append("user",MainActivity.credential)
                                .append("title",stitle)
                                .append("time",stime)
                                .append("location",slocation)
                                .append("detail",sdetail))
                        .getAsync(result -> {
                            if(result.isSuccess()){
                                Log.v("Data","Data Insert Successfully");
                                Intent intent = new Intent(AddTaskActivity.this, AllTaskActivity.class);
                                startActivity(intent);
                            }
                            else{
                                err.setText("Already have a task for this time.");
                                Log.v("Data","Error:"+result.getError().toString());
                            }
                        });
            }

        }));

    }

    private static boolean checkDate(String date){
        Date d = null;
        String format = "MM/dd/yyyy HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            System.err.println("Please enter valid date and time.");
            return false;
        }
        if (!date.equals(sdf.format(d))) {
            d = null;
        }
        if(d == null){
            System.err.println("Please enter valid date and time.");
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_main_page:
                intent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_add_task:
                intent = new Intent(AddTaskActivity.this, AddTaskActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_search:
                intent = new Intent(AddTaskActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_all_task:
                intent = new Intent(AddTaskActivity.this, AllTaskActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
