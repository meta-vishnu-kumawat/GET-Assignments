

/**
 * Circle represents a circle shape defined by its radius and center point.
 */
public class Circle implements Shape {
    private  Point center;
    private  double radius;
    private  long timestamp;

    /**
     * Constructs a Circle with the specified center point and radius.
     *
     * @param center the center point of the circle
     * @param radius the radius of the circle
     */
    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public double getArea() {
        try {
            return Math.PI * radius * radius;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public double getPerimeter() {
       try {
        return 2 * Math.PI * radius;
       } catch (Exception e) {
        System.out.println(e.getMessage());
       }
       return 0;
    }

    @Override
    public Point getOrigin() {
        try {
            return center;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isPointEnclosed(Point point) {
        try {
            return center.distance(point) <= radius;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    @Override
public ShapeType getShapeType() {
    try {
        return ShapeType.CIRCLE;
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
    return "Circle [Type: " + getShapeType() + ", Center: " + center + ", Radius: " + radius + 
           ", Area: " + getArea() + ", Perimeter: " + getPerimeter() + 
           ", Timestamp: " + timestamp + "]";
}

}
