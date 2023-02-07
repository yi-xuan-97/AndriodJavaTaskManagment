import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TaskQueue {
    private HashMap<Person, PriorityQueue<Task>> queue;
    public TaskQueue(){
        queue = new HashMap<>();
    }

    public void addTask(Person person, Task task){
        if(queue.containsKey(person)){
            queue.get(person).add(task);
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
        queue.put(person,temp);
}

    public Task getNext(Person person) {
        return queue.get(person).peek();
    }

    public void removeNext(Person person){
        queue.get(person).poll();
    }

    public PriorityQueue<Task> getQueue(Person person){
        return queue.get(person);
    }
    public HashMap<Person, PriorityQueue<Task>> getQueue() {
        return queue;
    }


}
