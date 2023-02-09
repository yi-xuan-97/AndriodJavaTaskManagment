package main.java.pdx.edu.CS506;

import java.util.*;

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

    public void addQueue(User user, PriorityQueue<Task> pqueue){

        if(queue.containsKey(user)){
            for(Task t:pqueue)
                queue.get(user).add(t);
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
        for(Task t:pqueue)
            temp.add(t);
        queue.put(user,temp);
    }

    public ArrayList<Task> searchTask(User user,String s){
        if(!queue.containsKey(user)){
            return null;
        }
        ArrayList<Task> list = new ArrayList<>();
        PriorityQueue<Task> q = queue.get(user);

        for(Task k:q){
            if(k.getTitle().contains(s))
                list.add(k);
        }

        return list;
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
