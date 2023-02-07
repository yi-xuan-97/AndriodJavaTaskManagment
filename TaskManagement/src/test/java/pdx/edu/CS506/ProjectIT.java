package test.java.pdx.edu.CS506;

import main.java.pdx.edu.CS506.Main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

//import static com.github.stefanbirkner.systemlambda.SystemLambda.*;
public class ProjectIT{
    @Test
    public void testAllValid() throws ParseException {
        String[] s = new String[]{("task1\n" +
                "\"need to finish hw1 asap\"\n" +
                "remote\n" +
                "01/26/2023\n" +
                "13:46\n" +
                "yfeng\n" +
                "yfeng@pdx.edu\n" +
                "123456Fyx@")};
//        Main.main(s);
//        Assertions.assertEquals(Main.main(s));
//        Main m = Mockito.mock(Main.class);
//        Assert.assertEquals("Hello Baeldung Readers!!", outputStreamCaptor.toString()
//                .trim());
//        exit.expectSystemExitWithStatus(0);
//        String text = tapSystemOut(() -> {
//            print("Hello Baeldung Readers!!");
//        });
//
//        assertEquals("Hello Baeldung Readers!!", text.trim());

    }

//    @Rule
//    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
//
//    @Test
//    public void givenSystemOutRule_whenInvokePrintln_thenLogSuccess() {
//        print("Hello Baeldung Readers!!");
//
//        Assert.assertEquals("Hello Baeldung Readers!!", systemOutRule.getLog()
//                .trim());
//    }
}
