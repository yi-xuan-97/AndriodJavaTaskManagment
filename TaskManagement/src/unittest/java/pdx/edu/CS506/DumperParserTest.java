package unittest.java.pdx.edu.CS506;

import main.java.pdx.edu.CS506.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.util.PriorityQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DumperParserTest {

    @Test
    void TaskdumpNparse() throws IOException {
        User user = new User("name","name@pdx.edu","Password123@");
        Task task = new Task("Title","task detail","OR",new Date("01/02/2023 12:33"));
        Task task1 = new Task("Task title","task1 detail","OR",new Date("01/03/2023 12:33"));
        TaskQueue taskQueue = new TaskQueue();
        taskQueue.addTask(user,task);
        taskQueue.addTask(user,task1);

        String fileloc = "src/unittest/resources/" + user.getName() + ".txt";
        File file = new File(fileloc);


        FileWriter filewriter = new FileWriter(file);
        TaskDumper taskdumper = new TaskDumper(filewriter);
        taskdumper.dump(user,taskQueue);

        FileReader fileReader = new FileReader(file);
        TaskParser taskParser = new TaskParser(fileReader);
        assertThat(Objects.requireNonNull(taskParser.parse().poll()).getTitle(), is("Title"));
    }


    @Test
    void UserParseNDump() throws IOException {
        UserList userlist = new UserList();
        userlist.checkNAdd("name","name@pdx.edu","Password123@");
        userlist.checkNAdd("name1","name1@pdx.edu","Password123@");

        String fileloc = "src/unittest/resources/task_management_user_list.txt";
        File file = new File(fileloc);

        FileWriter filewriter = new FileWriter(file);
        UserDumper userdumper = new UserDumper(filewriter);
        userdumper.dump(userlist);

        FileReader fileReader = new FileReader(file);
        UserParser userParser = new UserParser(fileReader);

        assertThat(userParser.parse().get(0).getName(),is("name"));
    }
}
