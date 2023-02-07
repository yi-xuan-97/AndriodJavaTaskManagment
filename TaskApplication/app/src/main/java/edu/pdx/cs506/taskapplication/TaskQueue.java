package edu.pdx.cs506.taskapplication;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TaskQueue {
    private HashMap<User, PriorityQueue<Task>> queue;
    public TaskQueue(){
        queue = new HashMap<>();
    }

    public void addTask(User user, Task task){

        if(queue.containsKey(user)){
            queue.get(user).add(task);
            return;
        }
        PriorityQueue<Task> temp = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                    Date d1 = t1.getDate();
                    Date d2 = t2.getDate();
                    return d1.compareTo(d2);
            }
        });
        temp.add(task);
        queue.put(user,temp);
}

    public Task getNext(User user) {
        return queue.get(user).peek();
    }

    public Task removeNext(User user){
        return queue.get(user).remove();
    }

    public PriorityQueue<Task> getQueue(User user){
        return queue.get(user);
    }

}
