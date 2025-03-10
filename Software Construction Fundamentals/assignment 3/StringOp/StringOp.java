
import java.util.Scanner;

public class StringOp {

    public static void main(String[] args) {
        StringOperation op = new StringOperation();
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            System.out.println("Select option");
            System.out.println(
                    "1. Equlity Of String \n 2. Reverse String  \n 3. Modification Of String \n 4.Find  Longest Word in String \n 5.Exit");

            System.out.println("Select option");
            int option = 0;
            try {
                
                 option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            String str = "";
            String str1 = "";
            String str2 = "";
            if (option == 1) {
                System.out.println("Enter first String for checking equlity");
                str1 = sc.nextLine();
                System.out.println("Enter second String for checking equlity");
                str2 = sc.nextLine();
            } else if(option !=5) {
                System.out.println("Enter a String ");
                str = sc.nextLine();
            }
            System.out.println("=============================");

            switch (option) {
                case 1:
                    System.out.println("Output will be 1 if equal otherwise 0 ");

                    System.out.println(op.isEquals(str1, str2));

                    break;
                case 2:
                    System.out.println("Reverse of given String is ->" + op.revseString(str));
                    break;
                case 3:

                    System.out.println("Modification of given String is ->" + op.replaceChar(str));
                    break;
                case 4:

                    System.out.println("longest word in  given String is ->" + op.longestWord(str));
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
