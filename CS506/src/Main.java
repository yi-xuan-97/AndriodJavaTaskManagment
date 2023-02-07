import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        for(String s:args)
            System.out.println(s);
//        System.out.println(args[3]+" "+args[4]);
        Date date = new Date(args[3]+" "+args[4]);
        Task task = new Task(args[0],args[1],args[2],date);
        Person person = new Person(args[5],args[6],args[7] );

        Task task1 = new Task("t1","task1","CA",new Date("01/28/2023 13:46"));
        Task task2 = new Task("t1","task1","CA",new Date("01/27/2023 13:46"));
        Task task3 = new Task("t1","task1","CA",new Date("01/24/2023 13:46"));

        TaskQueue list = new TaskQueue();
        list.addTask(person,task);
        list.addTask(person,task1);
        list.addTask(person,task2);
        list.addTask(person,task3);

        Queue q = list.getQueue(person);
        while(!q.isEmpty()){
            Task t = (Task) q.poll();
            System.out.println(t.printAll());
        }
    }
}