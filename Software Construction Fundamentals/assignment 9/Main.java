import java.util.*;

/**
 * Main class to interact with the Graphics Library via a menu-driven program.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Screen screen = new Screen();
      private static  int Xmax = 0;
      private static  int Ymax = 0;
    public static void main(String[] args) {
        System.out.println("Enter Xmax for screen :");
         Xmax = getIntInput(scanner, 0, Integer.MAX_VALUE);
        System.out.println("Enter Ymax for screen :");
         Ymax = getIntInput(scanner, 0, Integer.MAX_VALUE);
             
        try {
            
            while (true) {
                System.out.println("\nGraphics Library Menu:");
                System.out.println("1. Add Shape");
                System.out.println("2. Delete Shape");
                System.out.println("3. Delete Shapes by Type");
                System.out.println("4. Display Sorted Shapes");
                System.out.println("5. Find Shapes Enclosing a Point");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = getIntInput(scanner, 1, 7);
    
                switch (choice) {
                    case 1 -> addShape();
                    case 2 -> deleteShape();
                    case 3 -> deleteShapesByType();
                    case 4 -> displaySortedShapes();
                    case 5 -> findShapesEnclosingPoint();
                    case 6 -> {
                        System.out.println("Exiting... Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addShape() {
        try {
            System.out.println("Enter Shape Type (CIRCLE, SQUARE, RECTANGLE, TRIANGLE, REGULAR_POLYGON): ");
            String typeStr = scanner.next().toUpperCase();
            ShapeType type;
    
            try {
                type = ShapeType.valueOf(typeStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid shape type. Try again.");
                return;
            }
    
            List<Double> params = new ArrayList<>();
    
            if (type == ShapeType.TRIANGLE) {
                System.out.println("Enter the coordinates of three vertices (x1 y1 x2 y2 x3 y3): ");
                for (int i = 0; i < 6; i++) params.add(getDoubleInput(scanner, 0,Xmax ));
            } else {
                System.out.println("Enter the x and y coordinates of the reference point: ");
                double x = getDoubleInput(scanner, 0,Xmax );
                double y = getDoubleInput(scanner, 0,Ymax );
                Point point = new Point(x, y);
    
                switch (type) {
                    case CIRCLE -> {
                        System.out.print("Enter radius: ");
                        params.add(getDoubleInput(scanner, 0,Xmax ));
                    }
                    case SQUARE -> {
                        System.out.print("Enter side length: ");
                        params.add(getDoubleInput(scanner, 0,Xmax ));
                    }
                    case RECTANGLE -> {
                        System.out.print("Enter length and breadth: ");
                        params.add(getDoubleInput(scanner, 0,Xmax ));
                        params.add(getDoubleInput(scanner,0, Ymax));
                    }
                    case REGULAR_POLYGON -> {
                        System.out.print("Enter number of sides and side length: ");
                        params.add(getDoubleInput(scanner, 0,Xmax ));
                        params.add(getDoubleInput(scanner, 0,Ymax ));
                    }
                }
    
                Shape shape = ShapeFactory.createShape(type, point, params);
                screen.addShape(shape);
                System.out.println(type + " added successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteShape() {
        try {
            List<Shape> sortedShapes = screen.getSortedShapes("timestamp");
            int index = 0; 
            sortedShapes.forEach(shape -> System.out.println(shape.getClass().getSimpleName()));
            System.out.print("Enter index of shape to delete from above list : ");
             index = getIntInput(scanner, 1, Integer.MAX_VALUE)-1;
    
            try {
                screen.deleteShape(screen.getSortedShapes("timestamp").get(index));
                System.out.println("Shape deleted successfully.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index. Try again.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteShapesByType() {
        try {
            System.out.print("Enter Shape Type to delete (CIRCLE, SQUARE, RECTANGLE, TRIANGLE, REGULAR_POLYGON): ");
        String typeStr = scanner.next().toUpperCase();

        try {
            screen.deleteShapesByType(ShapeType.valueOf(typeStr));
            System.out.println(typeStr + " shapes deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid shape type. Try again.");
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displaySortedShapes() {
        System.out.print("Enter sorting criteria (area, perimeter, timestamp, originDistance): ");
        String criteria = scanner.next();

        try {
            List<Shape> sortedShapes = screen.getSortedShapes(criteria);
            sortedShapes.forEach(shape -> System.out.println(shape.getClass().getSimpleName() + " - " + shape));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid sorting criteria. Try again.");
        }
    }

    private static void findShapesEnclosingPoint() {
        try {
            System.out.print("Enter point coordinates (x y): ");
            double x = getDoubleInput(scanner, Integer.MIN_VALUE, Integer.MAX_VALUE);
            double y =getDoubleInput(scanner, Integer.MIN_VALUE, Integer.MAX_VALUE);
    
            Point point = new Point(x, y);
            List<Shape> enclosingShapes = screen.getShapesEnclosingPoint(point);
    
            if (enclosingShapes.isEmpty()) {
                System.out.println("No shapes enclose the given point.");
            } else {
                System.out.println("Shapes enclosing the point:");
                enclosingShapes.forEach(shape -> System.out.println(shape.getClass().getSimpleName() + " - " + shape));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
     /**
     * Ensures valid integer input from the user within a specified range.
     * @param sc  Scanner object for input.
     * @param min Minimum valid value.
     * @param max Maximum valid value.
     * @return The validated integer input.
     */
    public static int getIntInput(Scanner sc, int min, int max) {
        int num;
        while (true) {
            try {
                num = sc.nextInt();
                if (num >= min && num <= max) return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            } catch (InputMismatchException e) {
                System.out.println("Enter valid Integer. Try Again...");
                sc.nextLine();
            }
        }
    }
    /**
     * Ensures valid double input from the user within a specified range.
     * @param sc  Scanner object for input.
     * @param min Minimum valid value.
     * @param max Maximum valid value.
     * @return The validated integer input.
     */
    public static double getDoubleInput(Scanner sc, int min, int max) {
        double num;
        while (true) {
            try {
                num = sc.nextDouble();
                if (num >= min && num <= max) return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            } catch (InputMismatchException e) {
                System.out.println("Enter valid Integer. Try Again...");
                sc.nextLine();
            }
        }
    }
}
