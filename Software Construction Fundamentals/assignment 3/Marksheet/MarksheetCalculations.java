package Marksheet;

import java.util.Scanner;

public class MarksheetCalculations {
    int grades[];
    int maxGrades = Integer.MIN_VALUE;
    int minGrades = Integer.MAX_VALUE;
    double avgGrades;
    double passStudents;

    /**
     * @param Integer Students as Number of Total Students
     */
    public MarksheetCalculations(int students) {
        Scanner sc = new Scanner(System.in);
        if (students <= 0)
            throw new ArithmeticException(" Students should be more then 0");
        else {
            grades = new int[students];

            System.out.println("Enter the grades of  " + students + " Students and grades should be between 0 to 100");
            for (int i = 0; i < students; i++) {
                System.out.println("Enter the grade of  Student " + (i + 1));
                int grade = sc.nextInt();

                if (grade >= 0 && grade <= 100) {
                    grades[i] = grade;
                } else {
                    throw new ArithmeticException("Enter grade Between 0 to 100 only. Try Again...!!");
                }
            }

        }
        sc.close();

    }

    /**
     * Calulate the marksheet details for students
     */

    public void marksheetCalculation() {

        int totalGrades = 0;
        int totalPassedStudents = 0;
        int totalStudents = grades.length;
        for (int i = 0; i < totalStudents; i++) {
            totalGrades += grades[i];

            maxGrades = maxGrades > grades[i] ? maxGrades : grades[i];
            minGrades = minGrades < grades[i] ? minGrades : grades[i];
            if (grades[i] >= 40)
                totalPassedStudents++;
        }
        avgGrades = (double) (totalGrades / totalStudents);
        passStudents = (totalPassedStudents * 100) / totalStudents;

    }

    /**
     * 
     * @return Maximum grade of students
     */
    public int maximumGrade() {
        return maxGrades;
    }

    /**
     * 
     * @return Manimum grade of students
     */
    public int manimumGrade() {
        return minGrades;
    }

    /**
     * 
     * @return Average grade of students
     */
    public double avrengeGrade() {
        return avgGrades;
    }

    /**
     * 
     * @return parcentage of passed students
     */
    public double parcentageOfStudentsPassed() {
        return passStudents;
    }
}
