/*
 * Vincent Foley-Bourgon (FOLV08078309)
 * Stella Domingo (DOMC03588902)
 */

package plante;

/**
 * @author vince The state of a turtle is a 3-tuple containing the x and y
 *         coordinates as well as the current angle.
 */
public class TurtleState implements Cloneable {
    public int x;
    public int y;
    public double angle;

    public TurtleState(int x, int y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + angle + ")";
    }

    // Create a new copy of the current state; used for pushing/popping states
    // from a stack of states.
    public TurtleState clone() {
        return new TurtleState(x, y, angle);
    }
}
