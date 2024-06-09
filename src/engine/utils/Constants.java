package engine.utils;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.StyleSet;

import java.util.EnumSet;

/**
 * Class to store all constant values
*/
public class Constants {
    public static final String[] mainMenuOptions = new String[] {"Play", "Settings", "About", "Exit"};

    // App logo generated using https://patorjk.com/software/taag/#p=display&f=Big&t=TEMPLATE
    public static final String[] appLogo = """

              _______ ______ _______ _____  _____  _____\s
             |__   __|  ____|__   __|  __ \\|_   _|/ ____|
                | |  | |__     | |  | |__) | | | | (___ \s
                | |  |  __|    | |  |  _  /  | |  \\___ \\\s
                | |  | |____   | |  | | \\ \\ _| |_ ____) |
                |_|  |______|  |_|  |_|  \\_\\_____|_____/\s
                                                        \s
                                                        \s

            """.split("\n");
    // Applying the blinkStyle to a text will make it blink
    public static final StyleSet<StyleSet.Set> blinkStyle = (new StyleSet.Set()).setModifiers(EnumSet.of(SGR.BLINK));
    public static final TextColor appForeground = new TextColor.RGB(200, 200, 255);
    public static final TextColor appBackground = new TextColor.RGB(0, 0, 0);


    public static final String creatorText = "Game made by *Sidney Canonica*";
    public static final String aboutText = """
Usa la freccia destra e sinistra per spostare il blocco e cerca di riempire le righe per cancellarle.
Non è possibile ruotare i blocchi o farli cadere più velocemente.
""";
    public static int GameFPS = 60;
    public static final String appDataDir = "data/";
    public static final String configPath = appDataDir+"config/exampleConfiguration.txt";
    public static String soundsDir = appDataDir+"sounds/";
    //Array contenente i colori dei vari blocchi di tetris.
    public static final TextColor[] blocksColor = new TextColor.RGB[] {new TextColor.RGB(0, 240, 240), new TextColor.RGB(0, 0, 240),
            new TextColor.RGB(240, 160, 0), new TextColor.RGB(240,240,0),
            new TextColor.RGB(0, 240, 0), new TextColor.RGB(160, 0, 240),
            new TextColor.RGB(240,0,0)};
    //Array contenente i caratteri usati per stampare i blocchi di tetris.
    public static final String[][] blockTypes= new String[][] {
            {"\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588"},{"\u2588\u2588","\u2588\u2588\u2588\u2588\u2588\u2588"},
            {"    \u2588\u2588","\u2588\u2588\u2588\u2588\u2588\u2588"},{"\u2588\u2588\u2588\u2588","\u2588\u2588\u2588\u2588"},
            {"  \u2588\u2588\u2588\u2588","\u2588\u2588\u2588\u2588  "},{"  \u2588\u2588  " ,"\u2588\u2588\u2588\u2588\u2588\u2588"},
            {"\u2588\u2588\u2588\u2588","  \u2588\u2588\u2588\u2588"}
    };
}
