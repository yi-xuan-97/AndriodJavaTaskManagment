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

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText search = findViewById(R.id.search);
        TextView res = findViewById(R.id.search_result);
        Button search_btn = findViewById(R.id.search_btn);

        search_btn.setOnClickListener((view -> {
            String s = search.getText().toString();

            Document document = new Document().append("user",MainActivity.credential).append("title",s);
            RealmResultTask<MongoCursor<Document>> findTask = mongoCollection_TaskList.find(document).iterator();
            findTask.getAsync(task -> {
                if (task.isSuccess()) {
                    MongoCursor<Document> results = task.get();
                    if(results!=null){

                        Log.v("TASK LIST", "successfully found all task for user:");
                        while (results.hasNext()) {
                            Document d = results.next();
                            String title = d.get("title").toString();
                            String time = d.get("time").toString();
                            String location = d.get("location").toString();
                            String detail = d.get("detail").toString();
                            String stemp =
                                    "\n  Event: " + title +
                                            "\n  Time: " + time +
                                            "\n  Location: " + location +
                                            "\n  Detail: " + detail + "\n";
                            Log.v("EXAMPLE", stemp);
                            res.append(stemp);
                        }
                    }
                    else{
                        res.setText("There is no such task.");
                    }
                } else {
                    Log.e("TASK LIST", "failed to find documents with: ", task.getError());
                }
            });
        }));
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
                intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_add_task:
                intent = new Intent(SearchActivity.this, AddTaskActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_search:
                intent = new Intent(SearchActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_all_task:
                intent = new Intent(SearchActivity.this, AllTaskActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
