import java.util.ArrayList;
import java.util.List;

public class RegularPolygon implements Shape {

    private final Point origin;
    private final int numSides;
    private final double sideLength;
    private final long timestamp;
    private final List<Point> vertices;

    public RegularPolygon(Point origin, int numSides, double sideLength) {
        if (numSides < 3) {
            throw new IllegalArgumentException("A polygon must have at least 3 sides.");
        }
        this.origin = origin;
        this.numSides = numSides;
        this.sideLength = sideLength;
        this.timestamp = System.currentTimeMillis();
        this.vertices = calculateVertices();
    }

    private List<Point> calculateVertices() {
        List<Point> points = new ArrayList<>();
        try {
            double angleIncrement = 2 * Math.PI / numSides;

            for (int i = 0; i < numSides; i++) {
                double angle = i * angleIncrement;
                double x = origin.getX() + sideLength * Math.cos(angle);
                double y = origin.getY() + sideLength * Math.sin(angle);
                points.add(new Point(x, y));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return points;
    }

    @Override
    public double getArea() {
        try {
            double apothem = sideLength / (2 * Math.tan(Math.PI / numSides));
            return (0.5 * numSides * sideLength * apothem);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public double getPerimeter() {
        try {
            return numSides * sideLength;
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
            int count = 0;
            for (int i = 0; i < vertices.size(); i++) {
                Point v1 = vertices.get(i);
                Point v2 = vertices.get((i + 1) % vertices.size());
    
                if (((v1.getY() > point.getY()) != (v2.getY() > point.getY())) &&
                    (point.getX() < (v2.getX() - v1.getX()) * (point.getY() - v1.getY()) / (v2.getY() - v1.getY()) + v1.getX())) {
                    count++;
                }
            }
            return count % 2 == 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
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

    public List<Point> getVertices() {
        try {
            return vertices;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public ShapeType getShapeType() {
        try {
            return ShapeType.REGULAR_POLYGON;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  
    return null;
  }
    
    @Override
    public String toString() {
        return "RegularPolygon [Origin: " + origin + ", Sides: " + numSides + ", Side Length: " + sideLength + 
               ", Area: " + getArea() + ", Perimeter: " + getPerimeter() + ", Timestamp: " + timestamp + "]";
    }
}
