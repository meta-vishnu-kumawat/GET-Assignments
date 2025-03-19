/**
 * Interface representing a Shape in the graphics library.
 */
public interface Shape {

    /**
     * Returns the area of the shape.
     * @return Area of the shape.
     */
    double getArea();

    /**
     * Returns the perimeter of the shape.
     * @return Perimeter of the shape.
     */
    double getPerimeter();

    /**
     * Returns the origin point of the shape.
     * @return Origin point of the shape.
     */
    Point getOrigin();

    /**
     * Checks if the given point is enclosed within the shape.
     * @param point The point to check.
     * @return True if the point is enclosed, false otherwise.
     */
    boolean isPointEnclosed(Point point);

    /**
     * Returns the timestamp when the shape was created.
     * @return Creation timestamp of the shape.
     */
    long getTimestamp();

    /**
     * Returns the type of the shape.
     * @return The ShapeType of this shape.
     */
    ShapeType getShapeType();

    /**
     * Returns a string representation of the shape.
     * @return String representation of the shape.
     */
    @Override
    String toString();
}
