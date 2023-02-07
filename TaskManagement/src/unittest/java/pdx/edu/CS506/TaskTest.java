package unittest.java.pdx.edu.CS506;

import main.java.pdx.edu.CS506.Task;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaskTest {
    @Test
    void getNullInfo(){
        Task task = new Task();
        assertThat(task.getTitle(), is(new String()));
        assertThat(task.getDetail(), is(new String()));
        assertThat(task.getLocation(), is(new String()));
        assertThat(task.getDate(), is(nullValue()));
    }

    @Test
    void setTask(){
        Task task = new Task();
        task.setTitle("title");
        task.setDetail("task detail");
        task.setLocation("OR");
        task.setDate(new Date("01/03/2023 11:23"));
        assertThat(task.getTitle(), equalTo("title"));
        assertThat(task.getDetail(), equalTo("task detail"));
        assertThat(task.getLocation(), equalTo("OR"));
        assertThat(task.getDate(), equalTo(new Date("01/03/2023 11:23")));
    }

    @Test
    void testprint(){
        Task task = new Task("title","task detail","OR",new Date("01/03/2023 11:23"));
        assertThat(task.printAll(), containsString("title"));
        assertThat(task.printAll(), containsString("task detail"));
        assertThat(task.printAll(), containsString("OR"));
    }

    @Test
    void testprettyprint(){
        Task task = new Task("title","task detail","OR",new Date("01/03/2023 11:23"));
        assertThat(task.prettyPrint(), containsString("title"));
        assertThat(task.prettyPrint(), containsString("task detail"));
        assertThat(task.prettyPrint(), containsString("OR"));
    }
}
