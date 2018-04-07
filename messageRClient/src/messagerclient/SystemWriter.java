package messagerclient;

/**
 *
 * @author maritn
 */
public class SystemWriter {

    private final int consoleWidth;
    private static final char SYSTEM_SYMBOL = '*';

    public SystemWriter(int consoleWidth) {
        this.consoleWidth = consoleWidth;
    }

    public void PrintLine() {
        System.out.print(SYSTEM_SYMBOL);

        for (int i = 1; i < consoleWidth - 1; i++) {
            System.out.print(" ");
        }

        System.out.print(SYSTEM_SYMBOL);
        System.out.println();
    }

    public void PrintLine(String text, int align) {
        System.out.print(SYSTEM_SYMBOL);
        System.out.print(" ");

        if (align < 0) {
            // Left
            System.out.print(text);
            for (int i = 2 + text.length(); i < consoleWidth - 2; i++) {
                System.out.print(" ");
            }
        } else if (align > 0) {
            // Right
            // TODO: Implement
        } else {
            // Center
            int start = (consoleWidth / 2) - (text.length() / 2) - 2;
            for (int i = 0; i < start; i++) {
                System.out.print(" ");
            }

            System.out.print(text);

            for (int i = start + text.length(); i < consoleWidth - 4; i++) {
                System.out.print(" ");
            }
        }

        System.out.print(" ");
        System.out.print(SYSTEM_SYMBOL);
        System.out.println();
    }

    public void PrintBorder() {
        for (int i = 0; i < consoleWidth; i++) {
            System.out.print(SYSTEM_SYMBOL);
        }

        System.out.println();
    }
}
