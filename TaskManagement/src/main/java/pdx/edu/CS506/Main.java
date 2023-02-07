package main.java.pdx.edu.CS506;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import java.util.regex.*;
public class Main {
    public static void main(String[] args) throws ParseException {

        for(String s:args)
            System.out.println(s);

        UserList userlist = new UserList();
        Task task = new Task();

        //Check valid username
        //Must contain 5-30 characters
        //Can only contain underscores
        String username = args[5];
        String regex = "^[A-Za-z]\\w{4,29}$";
        Pattern puser = Pattern.compile(regex);
        Matcher muser = puser.matcher(username);
        if(!muser.matches()) {
            System.err.println("Please make sure your username is valid.\nThe username should contains 5-30 character of alphanumeric characters and underscores.");
            System.exit(0);
        }

        //Check valid email address
        String email = args[6];
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pemail = Pattern.compile(emailRegex);
        Matcher memail = pemail.matcher(email);
        if(!memail.matches()){
            System.err.println("Please make sure your email address is valid.");
            System.exit(0);
        }

        //Must have at least one numeric character
        //Must have at least one lowercase character
        //Must have at least one uppercase character
        //Must have at least one special symbol among @#$%
        //Password length should be between 8 and 20
        String password = args[7];
        String regexpass = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20}$";
        Pattern pattern = Pattern.compile(regexpass);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            System.err.println("Unvalid password.\nPassword must have:\n" +
                    "At leaset one numeric character\n" +
                    "At least one lowercase character\n" +
                    "At least one special symbol among @#$%\n" +
                    "Password length should be between 8 and 20\n");
            System.exit(0);
        }

        User user = userlist.checkNAdd(username,email,password);

        String date = args[3]+" "+args[4];
        Date d = null;
        String format = "MM/dd/yyyy HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        d = sdf.parse(date);
        if (!date.equals(sdf.format(d))) {
            d = null;
        }
        if(d == null){
            System.err.println("Please enter valid date and time.");
            System.exit(0);
        }
        task.setDate(new Date(date));



//        Task task = new Task(args[0],args[1],args[2],date);

        task.setTitle(args[0]);
        User person = userlist.checkNAdd(args[5],args[6],args[7] );
//        User person1 = userlist.checkNAdd(args[5],args[6],args[7] );

        Task task1 = new Task("t1","task1","OR",new Date("01/28/2023 13:46"));
        Task task2 = new Task("t2","task2","WA",new Date("01/27/2023 13:46"));
        Task task3 = new Task("t3","task3","NY",new Date("01/24/2023 13:46"));

        TaskQueue list = new TaskQueue();
        list.addTask(person,task);
        list.addTask(person,task1);
//        list.addTask(person1,task2);
        list.addTask(person,task3);

        Queue q = list.getQueue(person);
        while(!q.isEmpty()){
            Task t = (Task) q.poll();
            System.out.println(t.prettyPrint());
        }

//        System.out.println(("***********"));
//        Queue q1 = list.getQueue(person1);
//        while(!q1.isEmpty()){
//            Task t1 = (Task) q1.poll();
//            if(t1!=null)
//                System.out.println(t1.prettyPrint());
//            else
//                System.out.println("thing here!!!!NO TASK!!!");
//        }




    }
}