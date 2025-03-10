package Area;

import java.util.Scanner;

public class Area {
    
    public static void main(String[] args) {
        AreaCalculation area = new AreaCalculation();
       Scanner sc = new Scanner(System.in);
       
     boolean flag  = true;
     while(flag){
        System.out.println("Select option");
       
       System.out.println("1. Triangle Area  \n 2. Rectangle Area  \n 3. Squire Area \n 4. Circle Area \n 5.Exit");
    int option = sc.nextInt();
     
    System.out.println("=============================");
    
        switch (option) {
            case 1:
            System.out.println("Enter the Width of Triangle : ");
            double width = sc.nextDouble();
            
            System.out.println("Enter the Height of Triangle : ");
            double height = sc.nextDouble();
               System.out.println( "Area of Triangle is ->  " +area.triangleArea(width, height));
               break;
            case 2:
            System.out.println("Enter the Width of Rectangle : ");
             width = sc.nextDouble();
            System.out.println("Enter the Height of Rectangle : ");
                 height = sc.nextDouble();
               System.out.println( "Area of Rectangle  is ->  " +area.rectangleArea(width, height));
                 break;
            case 3:
            System.out.println("Enter the Width of Squire : ");
            width = sc.nextDouble();
          System.out.println("Area of Squire is ->  " + area.squireArea(width));
            break;
            case 4:
            System.out.println("Enter the radius of Circle : ");
            double radius = sc.nextDouble();
          System.out.println("Area of Circle is ->  " + area.circleArea(radius));
            break;
            case 5:
            flag = false;
            break;
            default:
            System.out.println("Select valid option . Try Again...!!");
            
                break;
         }
         System.out.println("=============================");
     }
     sc.close();
        
    }
}
