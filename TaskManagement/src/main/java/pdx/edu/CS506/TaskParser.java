package main.java.pdx.edu.CS506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

public class TaskParser {
    private final Reader reader;

    public TaskParser(Reader reader) {
        this.reader = reader;
    }

    public PriorityQueue<Task> parse() throws IOException {
        PriorityQueue<Task> temp = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                Date d1 = t1.getDate();
                Date d2 = t2.getDate();
                return d1.compareTo(d2);
            }
        });

        BufferedReader br = new BufferedReader(this.reader);
        String taskinfo;
        while((taskinfo = br.readLine()) != null){
            String[] task = taskinfo.split("\\|");
            if(task.length!=4)
                return null;
            Task t = new Task(task[0],task[1],task[2],new Date(task[3]));
            temp.add(t);
        }

        return temp;
    }

}
