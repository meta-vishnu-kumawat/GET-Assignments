/**
 * Rectangle represents a rectangle shape defined by its length, breadth, and
 * origin point.
 */
public class Rectangle implements Shape {
    private Point origin;
    private double length;
    private double breadth;
    private long timestamp;

    /**
     * Constructs a Rectangle with the specified origin point, length, and breadth.
     *
     * @param origin  the bottom-left corner of the rectangle
     * @param length  the length of the rectangle
     * @param breadth the breadth of the rectangle
     */
    public Rectangle(Point origin, double length, double breadth) {
        this.origin = origin;
        this.length = length;
        this.breadth = breadth;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public double getArea() {
        try {
            return length * breadth;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public double getPerimeter() {
        try {
            return 2 * (length + breadth);
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
                    point.getX() <= origin.getX() + length &&
                    point.getY() >= origin.getY() &&
                    point.getY() <= origin.getY() + breadth;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public ShapeType getShapeType() {
        try {
            return ShapeType.RECTANGLE;
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
        return "Rectangle [Type: " + getShapeType() + ", Origin: " + origin + ", Length: " + length +
                " Breadth: " + breadth +", Area: " + getArea() + ", Perimeter: " + getPerimeter() +
                ", Timestamp: " + timestamp + "]";
    }

}
