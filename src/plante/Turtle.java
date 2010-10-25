package plante;

import java.awt.Graphics;
import java.util.Stack;

public class Turtle {
    private TurtleState state;
    private Stack<TurtleState> previousStates = new Stack<TurtleState>();
    private int initial_x;
    private int initial_y;

    private int distance;
    private double angle;
    private double delta;
    private RuleSet ruleSet;

    public Turtle(int x, int y, double delta, int distance, double angle,
        RuleSet ruleSet) {
        this.initial_x = x;
        this.initial_y = y;
        this.delta = Math.toRadians(delta);
        this.distance = distance;
        this.angle = Math.toRadians(angle);
        this.ruleSet = ruleSet;
        reset();
    }

    public void reset() {
        state = new TurtleState(initial_x, initial_y, angle);
    }

    private void moveForward() {
        moveForward(null);
    }

    private void moveForward(Graphics g) {
        int new_x = state.x + (int) (Math.cos(state.delta) * distance);
        int new_y = state.y + (int) (Math.sin(state.delta) * distance);

        if (g != null) {
            g.drawLine(state.x, state.y, new_x, new_y);
        }

        state.x = new_x;
        state.y = new_y;
    }

    private void turnLeft() {
        state.delta -= delta;
    }

    private void turnRight() {
        state.delta += delta;
    }

    private void pushState() {
        previousStates.push(state.clone());
    }

    private void popState() {
        state = previousStates.pop();
    }

    private void applyOperation(Character c, Graphics g) {
        switch (c) {
        case 'F':
            moveForward(g);
            break;
        case 'f':
            moveForward();
            break;
        case '+':
            turnLeft();
            break;
        case '-':
            turnRight();
            break;
        case '[':
            pushState();
            break;
        case ']':
            popState();
            break;
        case 'X':
            break;
        }
    }

    // Take s and recursively apply draw() onto the expansion of every character
    // of s until level is 0.  When level is 0, apply the operation associated
    // with each character to g.
    //
    // This recursive function keeps memory down to a minimum versus storing
    // the entire expansion in memory.
    public void draw(Graphics g, String s, int level) {
        if (level == 0) {
            for (int i = 0; i < s.length(); ++i)
                applyOperation(s.charAt(i), g);
            return;
        }

        for (int i = 0; i < s.length(); ++i) {
            draw(g, ruleSet.getString(s.charAt(i)), level - 1);
        }
    }

    public String toString() {
        return state.toString();
    }
}
