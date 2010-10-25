package plante;

public class TurtleState implements Cloneable {
    public int x;
    public int y;
    public double delta;
    
    public TurtleState(int x, int y, double delta) {
        this.x = x;
        this.y = y;
        this.delta = delta;
    }
    
    public String toString() {
        return "(" + x + ", " + y + ", " + delta + ")";
    }
    
    public TurtleState clone() {
        return new TurtleState(x, y, delta);
    }
}
