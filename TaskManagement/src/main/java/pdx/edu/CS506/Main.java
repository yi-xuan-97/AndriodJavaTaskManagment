package main.java.pdx.edu.CS506;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.regex.*;
public class Main {
    public static void main(String[] args) throws ParseException, IOException {
//        AppWithFile();
        AppWithCloud();
    }

    private static void AppWithCloud() throws IOException, ParseException {
        UserList userlist = new UserList();
        TaskQueue list = new TaskQueue();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        while(true){
            User user = new User();
            MongoDB mongoDB;
            System.out.println("Do you have an account with us already? Y/N");
            String res1 = reader.readLine().toUpperCase();
            while(!res1.equals("Y") && !res1.equals("N"))
                res1 = reader.readLine().toUpperCase();

            switch (res1){
                case "Y":{
                    System.out.println("Please enter your username");
                    String lname = reader.readLine();
                    System.out.println("Please enter your password");
                    String lpassword = reader.readLine();
                    mongoDB = new MongoDB(lname);
                    userlist.addList(mongoDB.getUsers());
                    user = userlist.searchUser(lname,lpassword);
                    if(user!=null)
                        break;
                    System.err.println("There is not existing account with the information you entered.");
                }
                case "N":{
                    System.out.println("Let's register an account within 1 minutes! We will need some of your information.");
                    System.out.println("Please enter an username:");
                    String rname = reader.readLine();
                    while(!checkUser(rname))
                        rname = reader.readLine();


                    System.out.println("Please enter an email:");
                    String remail = reader.readLine();
                    while(!checkEmail(remail))
                        remail = reader.readLine();

                    System.out.println("Please choose a password:");
                    String rpassword = reader.readLine();
                    while(!checkPassword(rpassword))
                        rpassword = reader.readLine();

                    if(userlist.searchUser(rname,rpassword)==null)
                        user = userlist.checkNAdd(rname,remail,rpassword);
                    else{
                        System.err.println("The account already exist!");
                        user = userlist.searchUser(rname,rpassword);
                    }
                    break;
                }
            }

            mongoDB = new MongoDB(user.getName());
            mongoDB.addUser(user.getName(),user.getEmail(),user.getPassword());

            while (true){
                PriorityQueue<Task> queue = mongoDB.getTasks();
                list.addQueue(user,queue);

                System.out.println("Please select what you would like to do:\n" +
                        "1. Add new task\n" +
                        "2. Display all your task\n" +
                        "3. Search with task title\n" +
                        "4. Switch user\n" +
                        "5. Exit application");

                int res2 = 0;
                try {
                    res2 = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    System.err.println("That is not a choice");
                    continue;
                }
                System.out.println(res2);
                while(res2<1 || res2>5){
                    try {
                        res2 = Integer.parseInt(reader.readLine());
                    } catch (NumberFormatException e) {
                        System.err.println("That is not a choice");
                    }
                }

                if(res2==4)
                    break;
                else if(res2==5)
                    return;

                switch (res2){
                    case 1:{
                        Task task = new Task();

                        System.out.println("Please enter your task title:");
                        String title = reader.readLine();
                        task.setTitle(title);

                        System.out.println("Please enter your task date time: - MM/dd/yyyy HH:mm");
                        String date = reader.readLine();
                        while (!checkDate(date))
                            date = reader.readLine();
                        task.setDate(new Date(date));

                        System.out.println("Please enter your task location:");
                        String location = reader.readLine();
                        task.setLocation(location);

                        System.out.println("Please enter some note for your task:");
                        String detail = reader.readLine();
                        task.setDetail(detail);

                        queue.add(task);

                        mongoDB.addTask(title,date,location,detail);
                        break;
                    }
                    case 2:{
                        if(queue.size()==0){
                            System.out.println("There is no task yet");
                            break;
                        }
                        for(Task t:queue){
                            System.out.println(t.prettyPrint());
                        }
                        break;
                    }
                    case 3:{
                        System.out.println("Please enter a title you would like to search:");
                        String searchTitle = reader.readLine();
                        ArrayList<Task> arrayList = list.searchTask(user,searchTitle);
                        if(arrayList.size()==0){
                            System.out.println("No task with such title found");
                            break;
                        }
                        for(Task t:arrayList){
                            System.out.println(t.prettyPrint());
                        }
                        break;
                    }
                }

            }

        }
    }

    private static void AppWithFile() throws IOException {
        UserList userlist = new UserList();
        TaskQueue list = new TaskQueue();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String fileloc1 = "src/main/resources/task_management_user_list.txt";
        File file1 = new File(fileloc1);
        FileReader fileReader1 = new FileReader(file1);
        UserParser userParser = new UserParser(fileReader1);
        userlist.addList(userParser.parse());

        while(true){
            User user = new User();
            System.out.println("Do you have an account with us already? Y/N");
            String res1 = reader.readLine().toUpperCase();
            while(!res1.equals("Y") && !res1.equals("N"))
                res1 = reader.readLine().toUpperCase();

            switch (res1){
                case "Y":{
                    System.out.println("Please enter your username");
                    String lname = reader.readLine();
                    System.out.println("Please enter your password");
                    String lpassword = reader.readLine();
                    user = userlist.searchUser(lname,lpassword);
                    if(user!=null)
                        break;
                    System.err.println("There is not existing account with the information you entered.");
                }
                case "N":{
                    System.out.println("Let's register an account within 1 minutes! We will need some of your information.");
                    System.out.println("Please enter an username:");
                    String rname = reader.readLine();
                    while(!checkUser(rname))
                        rname = reader.readLine();


                    System.out.println("Please enter an email:");
                    String remail = reader.readLine();
                    while(!checkEmail(remail))
                        remail = reader.readLine();

                    System.out.println("Please choose a password:");
                    String rpassword = reader.readLine();
                    while(!checkPassword(rpassword))
                        rpassword = reader.readLine();

                    if(userlist.searchUser(rname,rpassword)==null)
                        user = userlist.checkNAdd(rname,remail,rpassword);
                    else{
                        System.err.println("The account already exist!");
                        user = userlist.searchUser(rname,rpassword);
                    }
                    break;
                }
            }

            String fileloc = "src/main/resources/" + user.getName() + ".txt";
            File file = new File(fileloc);
            if(!file.exists())
                file.createNewFile();
            FileReader fileReader = new FileReader(file);
            TaskParser taskParser = new TaskParser(fileReader);
            list.addQueue(user,taskParser.parse());
            PriorityQueue<Task> queue = list.getQueue(user);

            while (true){

                System.out.println("Please select what you would like to do:\n" +
                        "1. Add new task\n" +
                        "2. Display all your task\n" +
                        "3. Search with task title\n" +
                        "4. Switch user\n" +
                        "5. Exit application");

                int res2 = Integer.parseInt(reader.readLine());
                System.out.println(res2);
                while(res2<1 || res2>5)
                    res2 = Integer.parseInt(reader.readLine());

                if(res2==4){
                    FileWriter filewriter = new FileWriter(file);
                    TaskDumper taskdumper = new TaskDumper(filewriter);
                    taskdumper.dump(user,list);
                    break;
                }
                else if(res2==5){
                    FileWriter filewriter = new FileWriter(file);
                    TaskDumper taskdumper = new TaskDumper(filewriter);
                    taskdumper.dump(user,list);

                    FileWriter filewriter1 = new FileWriter(file1);
                    UserDumper userdumper = new UserDumper(filewriter1);
                    userdumper.dump(userlist);
                    return;
                }

                switch (res2){
                    case 1:{
                        Task task = new Task();

                        System.out.println("Please enter your task title:");
                        String title = reader.readLine();
                        task.setTitle(title);

                        System.out.println("Please enter your task date time: - MM/dd/yyyy HH:mm");
                        String date = reader.readLine();
                        while (!checkDate(date))
                            date = reader.readLine();
                        task.setDate(new Date(date));

                        System.out.println("Please enter your task location:");
                        String location = reader.readLine();
                        task.setLocation(location);

                        System.out.println("Please enter some note for your task:");
                        String detail = reader.readLine();
                        task.setDetail(detail);

                        queue.add(task);

                        break;
                    }
                    case 2:{
                        if(queue.size()==0){
                            System.out.println("There is no task yet");
                            break;
                        }
                        for(Task t:queue){
                            System.out.println(t.prettyPrint());
                        }
                        break;
                    }
                    case 3:{
                        System.out.println("Please enter a title you would like to search:");
                        String searchTitle = reader.readLine();
                        ArrayList<Task> arrayList = list.searchTask(user,searchTitle);
                        if(arrayList.size()==0){
                            System.out.println("No task with such title found");
                            break;
                        }
                        for(Task t:arrayList){
                            System.out.println(t.prettyPrint());
                        }
                        break;
                    }
                }

            }

        }
    }

    private static boolean checkUser(String username){
        //Check valid username
        //Must contain 5-30 characters
        //Can only contain underscores
        String regex = "^[A-Za-z]\\w{4,29}$";
        Pattern puser = Pattern.compile(regex);
        Matcher muser = puser.matcher(username);
        if(!muser.matches()) {
            System.err.println("Please make sure your username is valid.\nThe username should contains 5-30 character of alphanumeric characters and underscores.");
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
            System.err.println("Please make sure your email address is valid.");
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
            System.err.println("Unvalid password.\nPassword must have:\n" +
                    "At leaset one numeric character\n" +
                    "At least one lowercase character\n" +
                    "At least one special symbol among @#$%\n" +
                    "Password length should be between 8 and 20");
            return false;
        }
        return true;
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
}