package main.java.pdx.edu.CS506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

public class UserParser {
    private final Reader reader;

    public UserParser(Reader reader) {
        this.reader = reader;
    }

    public ArrayList<User> parse() throws IOException {

        BufferedReader br = new BufferedReader(this.reader);
        String taskinfo;
        ArrayList<User> temp = new ArrayList<>();
        while((taskinfo = br.readLine()) != null){
            String[] task = taskinfo.split("\\|");
            if(task.length!=3)
                return null;
            User u = new User(task[0],task[1],task[2]);
            temp.add(u);
        }

        return temp;
    }

}
