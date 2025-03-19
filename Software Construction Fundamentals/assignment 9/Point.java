/**
 * Point represents a point in 2D space with real (x, y) coordinates.
 */
public class Point {
    private  double y;
    private  double x;

    /**
     * Constructs a Point at the specified (x, y) coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x-coordinate of the point.
     */
    public double getX() {
        try {
            return x;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * @return the y-coordinate of the point.
     */
    public double getY() {
        try {
            return y; 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point to calculate distance to
     * @return the Euclidean distance between this point and the other point
     */
    public double distance(Point other) {
        try {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    @Override
    public String toString() {
        return " X: " + getX() + ", Y: " + getY();
    }
}
