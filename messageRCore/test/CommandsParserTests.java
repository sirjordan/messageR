
import java.util.*;
import messageRCore.Commands.CommandDeffinition;
import messageRCore.Commands.CommandInput;
import messageRCore.Commands.CommandsParser;
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
    public void extractCommands_Simple() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("join", 1, null));
                }
            };
        });

        String text_2 = "-join channelName123";

        Queue<CommandInput> cmds_2 = parser.extractCommands(text_2);

        CommandInput cmd = cmds_2.poll();

        assertEquals(cmd.getCommandName(), "join");
        assertEquals(cmd.getCommandArguments().get(0), "channelName123");
    }

    @Test
    public void extractCommands_Mixed() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("join", 1, null));
                }
            };
        });

        String text_1 = "..textx -join channelName123 texttt..";
        Queue<CommandInput> cmds_1 = parser.extractCommands(text_1);

        CommandInput cmd = cmds_1.poll();

        assertEquals(cmd.getCommandName(), "join");
        assertEquals(cmd.getCommandArguments().get(0), "channelName123");
    }

    @Test
    public void extractCommands_End() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("exit", 0, null));
                }
            };
        });

        String text_1 = "..textx -exit";
        Queue<CommandInput> cmds_1 = parser.extractCommands(text_1);

        assertEquals(cmds_1.poll().getCommandName(), "exit");
    }

    @Test
    public void extractCommands_MultipleCommands() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("cmd_1", 1, null));
                    add(new CommandDeffinition("cmd_2", 1, null));
                    add(new CommandDeffinition("cmd_3", 0, null));
                }
            };
        });

        String text_1 = "-cmd_1 arg1 ..textx... -cmd_2 arg2 ...text... -cmd_3 ... text";
        Queue<CommandInput> cmds_1 = parser.extractCommands(text_1);

        CommandInput cmd1 = cmds_1.poll();
        assertEquals(cmd1.getCommandName(), "cmd_1");
        assertEquals(cmd1.getCommandArguments().get(0), "arg1");

        CommandInput cmd2 = cmds_1.poll();
        assertEquals(cmd2.getCommandName(), "cmd_2");
        assertEquals(cmd2.getCommandArguments().get(0), "arg2");

        assertEquals(cmds_1.poll().getCommandName(), "cmd_3");
    }

    @Test
    public void extractCommands_MultipleArguments() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("cmd_1", 2, null));
                }
            };
        });

        String text_1 = "-cmd_1 arg1 arg2 ..textx... ";
        Queue<CommandInput> cmds_1 = parser.extractCommands(text_1);

        CommandInput cmd = cmds_1.poll();

        assertEquals(cmd.getCommandName(), "cmd_1");
        assertEquals(cmd.getCommandArguments().get(0), "arg1");
        assertEquals(cmd.getCommandArguments().get(1), "arg2");
    }

    @Test
    public void extractCommands_MultipleCommands_MultipleArguments() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("cmd_1", 2, null));
                    add(new CommandDeffinition("cmd_2", 2, null));
                }
            };
        });

        String text_1 = "text.. -cmd_1 arg1 arg2 ..textx... -cmd_2 arg21 arg22";
        Queue<CommandInput> cmds_1 = parser.extractCommands(text_1);

        CommandInput cmd1 = cmds_1.poll();

        assertEquals(cmd1.getCommandName(), "cmd_1");
        assertEquals(cmd1.getCommandArguments().get(0), "arg1");
        assertEquals(cmd1.getCommandArguments().get(1), "arg2");

        CommandInput cmd2 = cmds_1.poll();

        assertEquals(cmd2.getCommandName(), "cmd_2");
        assertEquals(cmd2.getCommandArguments().get(0), "arg21");
        assertEquals(cmd2.getCommandArguments().get(1), "arg22");
    }
    
    @Test
    public void cleanFromCommands_MultipleCommands_MultipleArguments() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("cmd_1", 2, null));
                    add(new CommandDeffinition("cmd_2", 2, null));
                }
            };
        });

        String text_1 = "text.. -cmd_1 arg1 arg2 ..textx... -cmd_2 arg21 arg22";
        
        String clean = parser.cleanFromCommands(text_1);
        
        assertEquals("text..  ..textx... ", clean);
    }
    
    @Test
    public void cleanFromCommands_CommandOnly() {
        CommandsParser parser = new CommandsParser(() -> {
            return new HashSet<CommandDeffinition>() {
                {
                    add(new CommandDeffinition("help", 0, null));
                }
            };
        });

        String text_1 = "-help";
        
        String clean = parser.cleanFromCommands(text_1);
        
        assertEquals("", clean);
    }
}
