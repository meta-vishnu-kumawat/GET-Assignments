package Area;

public class AreaCalculation {
    /**
     * Calculate the Area of Triangle
     * 
     * @param double width as first input
     * @param double height as second input
     * @return area of triangle as double
     */
    public double triangleArea(double width, double height) {
        return 0.5 * width * height;
    }

    /**
     * Calculate the Area of Rectangle
     * 
     * @param double width as first input
     * @param double height as second input
     * @return area of rectangle as double
     */
    public double rectangleArea(double width, double height) {
        return width * height;
    }

    /**
     * Calculate the Area of Squire
     * 
     * @param double width as input
     * @return area of squire as double
     */
    public double squireArea(double width) {
        return Math.pow(width, 2);
    }

    /**
     * Calculate the Area of Circle
     * 
     * @param double radius as input
     * @return area of circle as double
     */
    public double circleArea(double radius) {
        return (Math.PI) * Math.pow(radius, 2);
    }
}
