/*
 * Vincent Foley-Bourgon (FOLV08078309)
 * Stella Domingo (DOMC03588902)
 */


package plante;

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
    
    public TurtleState clone() {
        return new TurtleState(x, y, angle);
    }
}
