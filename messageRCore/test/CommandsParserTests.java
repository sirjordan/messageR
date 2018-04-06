
import java.util.List;
import messageRCore.Command;
import messageRCore.CommandsParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maritn
 */
public class CommandsParserTests {

    public CommandsParserTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void parsingCommands_Simple() {
        CommandsParser parser = new CommandsParser();

        String text_2 = "-join channelName123";

        List<Command> cmds_2 = parser.parseCommands(text_2);

        assertEquals(cmds_2.get(0).getCommandName(), "join");
        assertEquals(cmds_2.get(0).getCommandArgument(), "channelName123");
    }

    @Test
    public void parsingCommands_Mixed() {
        CommandsParser parser = new CommandsParser();

        String text_1 = "..textx -join channelName123 texttt..";
        List<Command> cmds_1 = parser.parseCommands(text_1);

        assertEquals(cmds_1.get(0).getCommandName(), "join");
        assertEquals(cmds_1.get(0).getCommandArgument(), "channelName123");
    }

    @Test
    public void parsingCommands_End() {
        CommandsParser parser = new CommandsParser();

        String text_1 = "..textx -exit";
        List<Command> cmds_1 = parser.parseCommands(text_1);

        assertEquals(cmds_1.get(0).getCommandName(), "exit");
    }

    @Test
    public void parsingCommands_MultipleCommands() {
        CommandsParser parser = new CommandsParser();

        String text_1 = "-cmd_1 arg1 ..textx... -cmd_2 arg2 ...text... -cmd_3";
        List<Command> cmds_1 = parser.parseCommands(text_1);

        assertEquals(cmds_1.get(0).getCommandName(), "cmd_1");
        assertEquals(cmds_1.get(0).getCommandArgument(), "arg1");

        assertEquals(cmds_1.get(1).getCommandName(), "cmd_2");
        assertEquals(cmds_1.get(1).getCommandArgument(), "arg2");

        assertEquals(cmds_1.get(2).getCommandName(), "cmd_3");
    }
}
