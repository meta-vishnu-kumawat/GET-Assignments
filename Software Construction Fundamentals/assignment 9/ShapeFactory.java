import java.util.List;

/**
 * ShapeFactory is responsible for creating various shape objects.
 */
public class ShapeFactory {

    /**
     * Creates a shape object of the specified type.
     *
     * @param shapeType the type of shape to create
     * @param point the reference point for shape creation (like center for circle, starting point for square, etc.)
     * @param parameters a list of parameters required for creating the shape
     * @return a Shape object of the specified type
     * @throws IllegalArgumentException if the shapeType or parameters are invalid
     */
    public static Shape createShape(ShapeType shapeType, Point point, List<Double> parameters) {
        try {
            switch (shapeType) {
                case CIRCLE:
                    if (parameters.size() != 1) throw new IllegalArgumentException("Circle requires 1 parameter: radius.");
                    return new Circle(point, parameters.get(0));
                    
                case SQUARE:
                    if (parameters.size() != 1) throw new IllegalArgumentException("Square requires 1 parameter: side length.");
                    return new Square(point, parameters.get(0));
                    
                case RECTANGLE:
                    if (parameters.size() != 2) throw new IllegalArgumentException("Rectangle requires 2 parameters: length and breadth.");
                    return new Rectangle(point, parameters.get(0), parameters.get(1));
                    
                case TRIANGLE:
                    if (parameters.size() != 6) throw new IllegalArgumentException("Triangle requires 6 parameters: x1, y1, x2, y2, x3, y3.");
                    Point vertexA = new Point(parameters.get(0), parameters.get(1));
                    Point vertexB = new Point(parameters.get(2), parameters.get(3));
                    Point vertexC = new Point(parameters.get(4), parameters.get(5));
                    return new Triangle(vertexA, vertexB, vertexC);
                    
                case REGULAR_POLYGON:
                    if (parameters.size() != 2) throw new IllegalArgumentException("Regular Polygon requires 2 parameters: number of sides and side length.");
                    return new RegularPolygon(point, parameters.get(0).intValue(), parameters.get(1));
                    
                default:
                    throw new IllegalArgumentException("Invalid shape type.");
        }} catch (Exception e) {
            System.out.println(e.getMessage());
        } 
     return null;
}
}
