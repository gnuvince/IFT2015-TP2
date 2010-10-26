package plante;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dessin {
    private static int WIDTH = 1000;
    private static int HEIGHT = 760;
    private static int DEFAULT_X = WIDTH / 2;
    private static int DEFAULT_Y = HEIGHT - 30;
    private static int DEFAULT_DISTANCE = 10;
    private static double DEFAULT_DELTA = 90.0;
    private static double DEFAULT_ANGLE = 270.0;

    public static void main(String[] args) throws InvalidRuleException {
        CmdLineArguments cmdLineArgs = new CmdLineArguments();
        cmdLineArgs.parse(args);
        
        // Optional arguments
        int distance = (int)cmdLineArgs.get("-D", DEFAULT_DISTANCE);
        int x = (int)cmdLineArgs.get("-x", DEFAULT_X);
        int y = (int)cmdLineArgs.get("-y", DEFAULT_Y);
        double delta = cmdLineArgs.get("-delta", DEFAULT_DELTA);
        double angle = cmdLineArgs.get("-a", DEFAULT_ANGLE);

        // Mandatory arguments
        int iterations = Integer.parseInt(cmdLineArgs.arguments.get(0));
        String initialRule = cmdLineArgs.arguments.get(1);
        RuleSet ruleSet = new RuleSet();
        cmdLineArgs.addRules(ruleSet);

        /*
        System.out.println("Distance: " + distance);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Angle: " + angle);
        System.out.println("Delta: " + delta);
        System.out.println("Iterations: " + iterations);
        System.out.println("Initial: " + initialRule);
        System.out.println("Rules: " + ruleSet);
        */
        
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