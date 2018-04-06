
import java.util.HashSet;
import java.util.List;
import messageRCore.CommandDeffinition;
import messageRCore.CommandInput;
import messageRCore.CommandsParser;
import messageRCore.Contracts.CommandsDefinitionSource;
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
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("join", 1));
                }
            };
        });

        String text_2 = "-join channelName123";

        List<CommandInput> cmds_2 = parser.extractCommands(text_2);

        assertEquals(cmds_2.get(0).getCommandName(), "join");
        assertEquals(cmds_2.get(0).getCommandArguments().get(0), "channelName123");
    }

    @Test
    public void parsingCommands_Mixed() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("join", 1));
                }
            };
        });

        String text_1 = "..textx -join channelName123 texttt..";
        List<CommandInput> cmds_1 = parser.extractCommands(text_1);

        assertEquals(cmds_1.get(0).getCommandName(), "join");
        assertEquals(cmds_1.get(0).getCommandArguments().get(0), "channelName123");
    }

    @Test
    public void parsingCommands_End() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("exit", 0));
                }
            };
        });

        String text_1 = "..textx -exit";
        List<CommandInput> cmds_1 = parser.extractCommands(text_1);

        assertEquals(cmds_1.get(0).getCommandName(), "exit");
    }

    @Test
    public void parsingCommands_MultipleCommands() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("cmd_1", 1));
                    add(new CommandDeffinition("cmd_2", 1));
                    add(new CommandDeffinition("cmd_3", 0));
                }
            };
        });

        String text_1 = "-cmd_1 arg1 ..textx... -cmd_2 arg2 ...text... -cmd_3 ... text";
        List<CommandInput> cmds_1 = parser.extractCommands(text_1);

        assertEquals(cmds_1.get(0).getCommandName(), "cmd_1");
        assertEquals(cmds_1.get(0).getCommandArguments().get(0), "arg1");

        assertEquals(cmds_1.get(1).getCommandName(), "cmd_2");
        assertEquals(cmds_1.get(1).getCommandArguments().get(0), "arg2");

        assertEquals(cmds_1.get(2).getCommandName(), "cmd_3");
    }

    @Test
    public void parsingCommands_MultipleArguments() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("cmd_1", 2));
                }
            };
        });

        String text_1 = "-cmd_1 arg1 arg2 ..textx... ";
        List<CommandInput> cmds_1 = parser.extractCommands(text_1);

        assertEquals(cmds_1.get(0).getCommandName(), "cmd_1");
        assertEquals(cmds_1.get(0).getCommandArguments().get(0), "arg1");
        assertEquals(cmds_1.get(0).getCommandArguments().get(1), "arg2");
    }
    
    @Test
    public void parsingCommands_MultipleCommands_MultipleArguments() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("cmd_1", 2));
                    add(new CommandDeffinition("cmd_2", 2));
                }
            };
        });

        String text_1 = "text.. -cmd_1 arg1 arg2 ..textx... -cmd_2 arg21 arg22";
        List<CommandInput> cmds_1 = parser.extractCommands(text_1);

        assertEquals(cmds_1.get(0).getCommandName(), "cmd_1");
        assertEquals(cmds_1.get(0).getCommandArguments().get(0), "arg1");
        assertEquals(cmds_1.get(0).getCommandArguments().get(1), "arg2");
        
         assertEquals(cmds_1.get(1).getCommandName(), "cmd_2");
        assertEquals(cmds_1.get(1).getCommandArguments().get(0), "arg21");
        assertEquals(cmds_1.get(1).getCommandArguments().get(1), "arg22");
    }
}
