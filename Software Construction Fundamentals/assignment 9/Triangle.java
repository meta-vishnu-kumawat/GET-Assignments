

/**
 * Triangle represents a general triangle defined by three vertices (points).
 */
public class Triangle implements Shape {
    private  Point A;
    private  Point B;
    private  Point C;
    private  long timestamp;

    /**
     * Constructs a Triangle with the specified three vertices.
     *
     * @param vertexA the first vertex of the triangle
     * @param vertexB the second vertex of the triangle
     * @param vertexC the third vertex of the triangle
     */
    public Triangle(Point vertexA, Point vertexB, Point vertexC) {
        this.A = vertexA;
        this.B = vertexB;
        this.C = vertexC;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public double getArea() {
        // Using the area formula for a triangle given three vertices
        try {
            return Math.abs(0.5*(A.getX() * (B.getY() - C.getY()) +
            B.getX() * (C.getY() - A.getY()) +
            C.getX() * (A.getY() - B.getY())) );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return 0;
    }

    @Override
    public double getPerimeter() {
        // Sum of distances between all three vertices
        try {
            return A.distance(B) + B.distance(C) + C.distance(A);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return 0;
    }

    @Override
    public Point getOrigin() {
        // Returning centroid as the origin of the triangle
        try {
            double centroidX = (A.getX() + B.getX() + C.getX()) / 3.0;
            double centroidY = (A.getY() + B.getY() + C.getY()) / 3.0;
            return new Point(centroidX, centroidY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isPointEnclosed(Point point) {
        // Checking if the point is inside the triangle using area comparison
        try {
            double totalArea = getArea();
            double area1 = new Triangle(point, B, C).getArea();
            double area2 = new Triangle(A, point, C).getArea();
            double area3 = new Triangle(A, B, point).getArea();
    
            // If sum of smaller areas is equal to the original area, point is inside or on the boundary
            return Math.abs(totalArea - (area1 + area2 + area3)) < 1e-9;
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

    public Point getVertexA(){
        try {
            return A;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    return null;
   }
    public Point getVertexB(){
        try {
            return B;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    return null;
}
    public Point getVertexC(){
        try {
            return C;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    return null;
    }
    @Override
public ShapeType getShapeType() {
    try {
        return ShapeType.TRIANGLE;     
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return null;
}
@Override
public String toString() {
    return "Triangle [Type: " + getShapeType() + ", Vertex A: " + A + 
           ", Vertex B: " + B + ", Vertex C: " + C + 
           ", Area: " + getArea() + ", Perimeter: " + getPerimeter() + 
           ", Timestamp: " + timestamp + "]";
}

}
