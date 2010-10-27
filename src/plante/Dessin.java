package plante;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dessin {
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension dim = tk.getScreenSize();
    private static int WIDTH = dim.width;
    private static int HEIGHT = dim.height;
    private static int DEFAULT_X = WIDTH / 2;
    private static int DEFAULT_Y = HEIGHT - 32;
    private static int DEFAULT_DISTANCE = 10;
    private static double DEFAULT_DELTA = 90.0;
    private static double DEFAULT_ANGLE = 270.0;

    public static void main(String[] args) throws InvalidRuleException {
        CmdLineArguments cmdLineArgs = new CmdLineArguments();
        cmdLineArgs.parse(args);

        // Optional arguments
        int distance = (int) cmdLineArgs.get("-D", DEFAULT_DISTANCE);
        int x = (int) cmdLineArgs.get("-x", DEFAULT_X);
        int y = (int) cmdLineArgs.get("-y", DEFAULT_Y);
        double delta = cmdLineArgs.get("-delta", DEFAULT_DELTA);
        double angle = cmdLineArgs.get("-a", DEFAULT_ANGLE);

        // Mandatory arguments
        int iterations = 0;

        iterations = (int) CmdLineArguments.toDouble(
            cmdLineArgs.arguments .get(0), 
            "le nombre d'itérations doit être un entier");

        String initialRule = cmdLineArgs.arguments.get(1);
        if (!RuleSet.isRuleValid(initialRule)) {
            System.err.printf("Erreur: '%s' n'est pas une règle valide\n",
                initialRule);
            System.exit(1);
        }

        RuleSet ruleSet = new RuleSet();
        cmdLineArgs.addRules(ruleSet);

        Turtle turtle = new Turtle(x, y, delta, distance, angle, ruleSet);
        TurtlePanel panel = new TurtlePanel(turtle, initialRule, iterations);

        JFrame frame = new JFrame("Devoir #2");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setVisible(true);
    }
}

@SuppressWarnings("serial")
class TurtlePanel extends JPanel {
    private Turtle turtle;
    private String initialString;
    private int initialLevel;

    public TurtlePanel(Turtle turtle, String initialString, int initialLevel) {
        this.turtle = turtle;
        this.initialString = initialString;
        this.initialLevel = initialLevel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0xc3, 0x00, 0xaa));
        turtle.draw(g, initialString, initialLevel);
        turtle.reset();
    }
}