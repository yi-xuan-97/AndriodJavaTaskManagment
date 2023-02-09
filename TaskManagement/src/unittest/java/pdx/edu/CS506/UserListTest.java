package unittest.java.pdx.edu.CS506;

import main.java.pdx.edu.CS506.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class UserListTest {
    @Test
    void addNonExit(){
        UserList list = new UserList();
        list.checkNAdd("name","name@pdx.edu","Password123@");
        list.checkNAdd("name1","name1@pdx.edu","Password123@");
        assertThat(list.getList().size(), equalTo(2));
    }

    @Test
    void addExit(){
        UserList list = new UserList();
        list.checkNAdd("name","name@pdx.edu","Password123@");
        list.checkNAdd("name","name@pdx.edu","Password123@");
        assertThat(list.getList().size(), equalTo(1));
    }

    @Test
    void searchUserExit(){
        UserList list = new UserList();
        list.checkNAdd("name","name@pdx.edu","Password123@");
        User user1 = list.searchUser("name","Password123@");
        assertThat(user1, notNullValue());
        User user2 = list.searchUser("name@pdx.edu","Password123@");
        assertThat(user2, notNullValue());
    }

    @Test
    void searchUserNonExit(){
        UserList list = new UserList();
        list.checkNAdd("name","name@pdx.edu","Password123@");

        User user1 = list.searchUser("name_test","Password123@");
        assertThat(user1, nullValue());
        User user2 = list.searchUser("name_test@pdx.edu","Password123@");
        assertThat(user2, nullValue());
    }
}
