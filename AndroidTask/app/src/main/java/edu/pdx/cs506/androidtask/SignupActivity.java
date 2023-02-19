package edu.pdx.cs506.androidtask;

import static edu.pdx.cs506.androidtask.MainActivity.mongoCollection_TaskList;
import static edu.pdx.cs506.androidtask.MainActivity.mongoCollection_userList;
import static edu.pdx.cs506.androidtask.MainActivity.mongoDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.bson.Document;

import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.mongodb.mongo.MongoDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText userEditText;
    private EditText emailEditText;
    private EditText passEditText;
    private TextView err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        userEditText = findViewById(R.id.signup_username);
        emailEditText = findViewById(R.id.signup_email);
        passEditText = findViewById(R.id.signup_password);
        err = findViewById(R.id.signup_error);


        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener((view -> {
            String error_info = "";

            String username = userEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passEditText.getText().toString();

            if(!checkUser(username)){
                error_info += "Username should contains 5-30 alphanumeric characters and underscores\n\n";
            }
            if(!checkEmail(email)){
                error_info += "Invalid email format\n\n";
            }
            if(!checkPassword(password)){
                error_info += "Password must have: at leaset one numeric character, one lowercase character, one special symbol among @#$% and length should be between 8 and 20\n";
            }

            err.setText(error_info);

            if(checkUser(username) && checkEmail(email) && checkPassword(password)){
                MainActivity.mongoCollection_userList.insertOne(new Document("_id",username+"_"+password)
                                .append("username",username)
                                .append("email",email)
                                .append("password",password))
                        .getAsync(result -> {
                            if(result.isSuccess()){
                                Log.v("Data","Data Insert Successfully");
                                MainActivity.credential = username+"_"+password;
                                Intent intent = new Intent(SignupActivity.this, AddTaskActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Log.v("Data","Error:"+result.getError().toString());
                                err.append("**Account with this username and password already exist!");
                            }
                        });
            }


        }));


    }

    private static boolean checkUser(String username){
        //Check valid username
        //Must contain 5-30 characters
        //Can only contain underscores
        String regex = "^[A-Za-z]\\w{4,29}$";
        Pattern puser = Pattern.compile(regex);
        Matcher muser = puser.matcher(username);
        if(!muser.matches()) {
            return false;
        }
        return true;
    }

    private static boolean checkEmail(String email){
        //Check valid email address
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pemail = Pattern.compile(emailRegex);
        Matcher memail = pemail.matcher(email);
        if(!memail.matches()){
            return false;
        }
        return true;
    }

    private static boolean checkPassword(String password){
        //Must have at least one numeric character
        //Must have at least one lowercase character
        //Must have at least one uppercase character
        //Must have at least one special symbol among @#$%
        //Password length should be between 8 and 20
        String regexpass = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20}$";
        Pattern pattern = Pattern.compile(regexpass);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            return false;
        }
        return true;
    }

}
