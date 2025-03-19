import java.util.*;
import java.util.stream.Collectors;

/**
 * Screen represents a virtual screen that stores and manages shapes.
 */
public class Screen {
    private final List<Shape> shapes;
    /**
     * Constructs an empty Screen.
     */
    public Screen() {
        this.shapes = new ArrayList<>();
    }

    /**
     * Adds a shape to the screen.
     *
     * @param shape the shape to be added
     */
    public void addShape(Shape shape) {
        try {
            shapes.add(shape);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a specific shape from the screen.
     *
     * @param shape the shape to be deleted
     */
    public void deleteShape(Shape shape) {
        try {
            shapes.remove(shape);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }

    /**
     * Deletes all shapes of a specific type from the screen.
     *
     * @param shapeType the type of shapes to delete
     */
    public void deleteShapesByType(ShapeType shapeType) {
        try {
            shapes.removeIf(shape -> shape.getClass().getSimpleName().equalsIgnoreCase(shapeType.name()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }

    /**
     * Returns a sorted list of shapes based on the specified criteria.
     *
     * @param criteria the sorting criteria ("area", "perimeter", "timestamp",
     *                 "originDistance")
     * @return a sorted list of shapes
     */
    public List<Shape> getSortedShapes(String criteria) {
        try {
            return shapes.stream().sorted((shape1, shape2) -> {
                switch (criteria.toLowerCase()) {
                    case "area":
                        return Double.compare(shape1.getArea(), shape2.getArea());
                    case "perimeter":
                        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
                    case "timestamp":
                        return Long.compare(shape1.getTimestamp(), shape2.getTimestamp());
                    case "origindistance":
                        double distance1 = shape1.getOrigin().distance(new Point(0, 0));
                        double distance2 = shape2.getOrigin().distance(new Point(0, 0));
                        return Double.compare(distance1, distance2);
                    default:
                        throw new IllegalArgumentException("Invalid sorting criteria.");
                }
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return null;
    }

    /**
     * Returns a list of shapes that enclose the specified point.
     *
     * @param point the point to check
     * @return a list of shapes that enclose the point
     */
    public List<Shape> getShapesEnclosingPoint(Point point) {
        try {
            return shapes.stream()
            .filter(shape -> shape.isPointEnclosed(point))
            .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return null;
    }

}