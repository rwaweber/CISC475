package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.Repl;

public class ReplTest {

    @Test
    public void testCommandify() throws IOException {
	Repl r = new Repl();
	String x = "the cat hopped over the fence";
	String[] b = r.commandify(x);
	String[] c = new String[] {"the", "cat", "hopped", "over", "the", "fence"};
	assertEquals(c, b);
    }
	

}
