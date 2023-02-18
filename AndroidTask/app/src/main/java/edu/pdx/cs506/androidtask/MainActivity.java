package edu.pdx.cs506.androidtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity {

//    private UserList userlist = new UserList();
//    private TaskQueue taskqueue = new TaskQueue();

    String appId = "taskapp-dtpif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Realm.init(this);
        App app = new App(new AppConfiguration.Builder(appId).build());

        Credentials credentials = Credentials.anonymous();
        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess()){
                    Log.v("User","Logged in anonymous");
                }
                else{
                    Log.v("User","Failed to log in");
                }
            }
        });

        Button signin = findViewById(R.id.main_signin);
        signin.setOnClickListener((view -> {
            Intent intent = new Intent(MainActivity.this, AllTaskActivity.class);
            startActivity(intent);
        }));

        Button signup = findViewById(R.id.main_signup);
        signup.setOnClickListener((view -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        }));


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        Intent intent;
//        switch (item.getItemId()) {
//            case R.id.menu_main_page:
//                intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
//                return true;
//            case R.id.menu_add_task:
//                intent = new Intent(MainActivity.this, AddTaskActivity.class);
//                startActivity(intent);
//                return true;
//            case R.id.menu_search:
//                intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intent);
//                return true;
//            case R.id.menu_all_task:
//                intent = new Intent(MainActivity.this, AllTaskActivity.class);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}