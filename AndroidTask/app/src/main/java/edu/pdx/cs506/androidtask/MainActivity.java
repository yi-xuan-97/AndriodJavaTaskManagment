package edu.pdx.cs506.androidtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.bson.Document;

import java.lang.annotation.Documented;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MainActivity extends AppCompatActivity {

//    private UserList userlist = new UserList();
//    private TaskQueue taskqueue = new TaskQueue();

    private String appId = "taskapp-dtpif";

    private EditText userEditText;
    private EditText passEditText;
    private TextView err;

    public static String credential;

    public static App app;

    public static MongoDatabase mongoDatabase;
    public static MongoCollection mongoCollection_userList;
    public static MongoCollection mongoCollection_TaskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEditText = findViewById(R.id.username);
        passEditText = findViewById(R.id.password);
        err = findViewById(R.id.error_main);

        Realm.init(this);
        app = new App(new AppConfiguration.Builder(appId).build());


        Credentials credentials = Credentials.emailPassword("yfeng@pdx.edu","Fyx1234@");
        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess()){
                    Log.v("User","Logged in successfully");
                    User user = MainActivity.app.currentUser();
                    MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
                    mongoDatabase = mongoClient.getDatabase("Application");
                    mongoCollection_userList = mongoDatabase.getCollection("UserList");
                    mongoCollection_TaskList = mongoDatabase.getCollection("TaskList");
                }
                else{
                    Log.v("User","Failed to log in");
                }
            }
        });


        Button signin = findViewById(R.id.main_signin);
        signin.setOnClickListener((view -> {
            String username = userEditText.getText().toString();
            String password = passEditText.getText().toString();

            Document document = new Document().append("_id",username+"_"+password);
            mongoCollection_userList.findOne(document).getAsync(result -> {
                if(result.isSuccess() && result.get()!=null ){
                    Log.v("Data", result.get().toString());
                    credential = username+"_"+password;
                    Intent intent = new Intent(MainActivity.this, AllTaskActivity.class);
                    startActivity(intent);
                }
                else if(result.isSuccess() && result.get()==null){
                    err.setText("There is no such account with this username and password.");
                }
                else{
                    Log.v("Data",result.getError().toString());
                }
            });



        }));

        Button signup = findViewById(R.id.main_signup);
        signup.setOnClickListener((view -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        }));


    }



}