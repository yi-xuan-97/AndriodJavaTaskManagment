import java.util.*;

public class TaskList {
    private HashMap<String, ArrayList<Task>> map;

    public TaskList(){
        map = new HashMap();
    }

    public void addTask(Person person,Task task){
        String username = person.getName();
        if(map.containsKey(username))
            map.get(username).add(task);
        else{
            ArrayList<Task> list = new ArrayList<>();
            list.add(task);
        }

    }
    public ArrayList<Task> getList(String username) {
        return map.getOrDefault(username,new ArrayList<>());
    }


}
