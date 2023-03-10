package unittest.java.pdx.edu.CS506;

import main.java.pdx.edu.CS506.*;
//import main.java.pdx.edu.CS506.Task;
//import main.java.pdx.edu.CS506.TaskQueue;
//import main.java.pdx.edu.CS506.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaskQueueTest {
    @Test
    void getTask(){
        User user = new User("name","name@pdx.edu","Password123@");
        Task task = new Task("Title","task detail","OR",new Date("01/02/2023 12:33"));
        TaskQueue list = new TaskQueue();
        list.addTask(user,task);
        Task t = list.getNext(user);

        assertThat(t.getTitle(), is("Title"));
    }

    @Test
    void getTaskwithRemove(){
        User user = new User("name","name@pdx.edu","Password123@");
        Task task = new Task("Title","task detail","OR",new Date("01/02/2023 12:33"));
        Task task1 = new Task("Title1","task1 detail","OR",new Date("01/01/2023 12:33"));
        TaskQueue list = new TaskQueue();
        list.addTask(user,task);
        list.addTask(user,task1);
        Task t = list.removeNext(user);
        assertThat(t.getTitle(), is("Title1"));
        Task t1 = list.getNext(user);
        assertThat(t1.getTitle(), is("Title"));
    }

    @Test
    void getUserQueue(){
        User user = new User("name","name@pdx.edu","Password123@");
        Task task = new Task("Title","task detail","OR",new Date("01/02/2023 12:33"));
        Task task1 = new Task("Title1","task1 detail","OR",new Date("01/01/2023 12:33"));
        TaskQueue list = new TaskQueue();
        list.addTask(user,task);
        list.addTask(user,task1);
        PriorityQueue<Task> p = list.getQueue(user);

        Task t = list.removeNext(user);
        assertThat(t.getTitle(), is("Title1"));
        Task t1 = list.getNext(user);
        assertThat(t1.getTitle(), is("Title"));
    }

    @Test
    void searchQueue(){
        User user = new User("name","name@pdx.edu","Password123@");
        Task task = new Task("Title","task detail","OR",new Date("01/02/2023 12:33"));
        Task task1 = new Task("Task title","task1 detail","OR",new Date("01/01/2023 12:33"));
        TaskQueue list = new TaskQueue();
        list.addTask(user,task);
        list.addTask(user,task1);

        ArrayList<Task> curr = list.searchTask(user,"Title");
        for(Task t:curr)
            assertThat(t.getTitle(), containsString("Title"));
    }

    @Test
    void addQueue(){
        User user = new User("name","name@pdx.edu","Password123@");
        Task task = new Task("Title","task detail","OR",new Date("01/02/2023 12:33"));
        Task task1 = new Task("Task title","task1 detail","OR",new Date("01/02/2023 12:33"));
        TaskQueue list = new TaskQueue();
        list.addTask(user,task);

        PriorityQueue<Task> p = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                Date d1 = t1.getDate();
                Date d2 = t2.getDate();
                return d1.compareTo(d2);
            }
        });
        p.add(task1);

        list.addQueue(user,p);

        assertThat(list.getQueue(user).size(), is(2));
    }

    @Test
    void addQueueNonExit(){
        User user = new User("name","name@pdx.edu","Password123@");
        Task task = new Task("Title","task detail","OR",new Date("01/02/2023 12:33"));
        Task task1 = new Task("Task title","task1 detail","OR",new Date("01/02/2023 12:33"));
        TaskQueue list = new TaskQueue();
        PriorityQueue<Task> p = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                Date d1 = t1.getDate();
                Date d2 = t2.getDate();
                return d1.compareTo(d2);
            }
        });
        p.add(task);
        p.add(task1);
        list.addQueue(user,p);
        assertThat(list.getQueue(user).size(), is(2));
    }
}
