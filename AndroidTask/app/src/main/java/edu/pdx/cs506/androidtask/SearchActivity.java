package edu.pdx.cs506.androidtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
