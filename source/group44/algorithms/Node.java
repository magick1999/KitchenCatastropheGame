package group44.algorithms;

/**
 * A structure to hold the neccesary parameters.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Node {
    private Node parent;
    private Coordinates coordinates;
    private double f;
    private double g;
    private double h;

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return the f
     */
    public double getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(double f) {
        this.f = f;
    }

    /**
     * @return the g
     */
    public double getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(double g) {
        this.g = g;
    }

    /**
     * @return the h
     */
    public double getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(double h) {
        this.h = h;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }
}