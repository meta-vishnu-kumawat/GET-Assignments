/**
 * Square represents a square shape defined by its side length and origin point.
 */
public class Square implements Shape {
    private Point origin;
    private double side;
    private long timestamp;

    /**
     * Constructs a Square with the specified origin point and side length.
     *
     * @param origin the bottom-left corner of the square
     * @param side   the length of the sides of the square
     */
    public Square(Point origin, double side) {
        try {
            this.origin = origin;
            this.side = side;
            this.timestamp = System.currentTimeMillis();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public double getArea() {
        try {
            return side * side;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public double getPerimeter() {
        try {
            return 4 * side;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Point getOrigin() {
        try {
            return origin;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isPointEnclosed(Point point) {
        try {
            return point.getX() >= origin.getX() &&
                    point.getX() <= origin.getX() + side &&
                    point.getY() >= origin.getY() &&
                    point.getY() <= origin.getY() + side;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public ShapeType getShapeType() {
        try {
            return ShapeType.SQUARE;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public long getTimestamp() {
        try {
            return timestamp;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    @Override
    public String toString() {
        return "Square [Type: " + getShapeType() + ", Origin: " + origin + ", Side: "+side+ ",Area: " + getArea() + ", Perimeter: " + getPerimeter() +
                ", Timestamp: " + timestamp + "]";
    }
}
