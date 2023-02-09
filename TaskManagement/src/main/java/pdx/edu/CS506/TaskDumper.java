package main.java.pdx.edu.CS506;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.PriorityQueue;

public class TaskDumper {
    private final Writer writer;
    public TaskDumper(Writer writer) {
        this.writer = writer;
    }
    public void dump(User user, TaskQueue taskqueue) {
        PrintWriter pw = new PrintWriter(this.writer);

        PriorityQueue<Task> queue = taskqueue.getQueue(user);

        for(Task t: queue){
            String result = t.getTitle() + "|"
                    + t.getDetail() + "|"
                    + t.getLocation() + "|"
                    + t.getDate();
            pw.println(result);
        }
        pw.flush();
    }

}
