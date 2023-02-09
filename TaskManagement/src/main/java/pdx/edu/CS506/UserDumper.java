package main.java.pdx.edu.CS506;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class UserDumper {
    private final Writer writer;
    public UserDumper(Writer writer) {
        this.writer = writer;
    }
    public void dump(UserList list) {
        PrintWriter pw = new PrintWriter(this.writer);

        for(User u: list.getList()){
            String result = u.getName() + "|"
                    + u.getEmail() + "|"
                    + u.getPassword();
            pw.println(result);
        }
        pw.flush();
    }

}
