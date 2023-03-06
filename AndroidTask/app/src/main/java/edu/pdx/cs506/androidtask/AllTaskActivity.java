package edu.pdx.cs506.androidtask;

import static edu.pdx.cs506.androidtask.MainActivity.mongoCollection_TaskList;

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

import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class AllTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alltask);

        TextView all_task = findViewById(R.id.print_all_task);
        Document document = new Document().append("user",MainActivity.credential);
        RealmResultTask<MongoCursor<Document>> findTask = mongoCollection_TaskList.find(document).iterator();
        findTask.getAsync(task -> {
            if (task.isSuccess()) {
                MongoCursor<Document> results = task.get();
                if(results.hasNext()){

                    Log.v("TASK LIST", "successfully found all task for user:");
                    while (results.hasNext()) {
                        Document d = results.next();
                        String title = d.get("title").toString();
                        String time = d.get("time").toString();
                        String location = d.get("location").toString();
                        String detail = d.get("detail").toString();
                        String s =
                                "\n  Event: " + title +
                                        "\n  Time: " + time +
                                        "\n  Location: " + location +
                                        "\n  Detail: " + detail + "\n";
                        Log.v("EXAMPLE", s);
                        all_task.append(s);
                    }
                }
                else{
                    all_task.setText("\nThere is no task yet, try add a new task.");
                }
            } else {
                Log.e("TASK LIST", "failed to find documents with: ", task.getError());
            }
        });



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
                intent = new Intent(AllTaskActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_add_task:
                intent = new Intent(AllTaskActivity.this, AddTaskActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_search:
                intent = new Intent(AllTaskActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_all_task:
                intent = new Intent(AllTaskActivity.this, AllTaskActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
