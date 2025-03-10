package Marksheet;

import java.util.*;

public class Marksheet {

  public static void main(String[] args) {
    System.out.println("Enter the Number of Students : ");
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    MarksheetCalculations m = new MarksheetCalculations(n);
    m.marksheetCalculation();
    System.out.println("===========================================");
    System.out.printf("Avarage grade of Students is :     %.2f\n ", m.avrengeGrade());
    System.out.println("Maximum grade of Students is :    "+ m.maximumGrade());
    System.out.println("Minimum grade of Students is :     "+ m.manimumGrade());
    System.out.printf("Parcentage of Students passed is : %.2f",m.parcentageOfStudentsPassed());
    System.out.print("%");

    sc.close();
  }
}