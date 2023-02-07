package unittest.java.pdx.edu.CS506;

import main.java.pdx.edu.CS506.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class UserTest {
    @Test
    void setUser(){
        User user = new User();
        user.setName("name");
        user.setEmail("test@pdx.edu");
        user.setPassword("Pass123456@");

        assertThat(user.getName(), equalTo("name"));
        assertThat(user.getEmail(), equalTo("test@pdx.edu"));
        assertThat(user.getPassword(), equalTo("Pass123456@"));

    }
}
