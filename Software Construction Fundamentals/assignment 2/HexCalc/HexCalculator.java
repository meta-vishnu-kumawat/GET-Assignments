import java.util.Scanner;
public class HexCalculator {
public static void main(String[] args) {
    
    HexOperation op = new HexOperation();
    Scanner sc = new Scanner(System.in);
    String hex1 = "";
    String hex2 = "";
    String hex = "";
    int num =  0;
        System.out.println("1. Add \n 2. Subtract \n 3. multiple \n 4. Division \n 5. Equals \n 6. Greater\n 7. Smaller\n 8. hex to dec\n 9. dec to hex\n");
    int option = sc.nextInt();
    if(option == 8){
        System.out.println("Enter a Hexadecimal Number as String");
         hex = sc.next();
    }
    if(option == 9){
        System.out.println("Enter a decimal Number as");
          num = sc.nextInt();
    }
  
    if(option != 8 && option != 9 && option != 10){
        System.out.println("Enter two Hexadecimal Number ");
        hex1 = sc.next();
        hex2 = sc.next();
    }
    
        
    
    switch (option) {
        case 1:
            System.out.println(op.addition(hex1, hex2));
            break;
        case 2:
            System.out.println(op.substraction(hex1, hex2));
            break;     
        case 3:
            System.out.println(op.multiply(hex1, hex2));
            break;

        case 4:
            System.out.println(op.division(hex1, hex2));
            break;
        case 5:
            System.out.println(op.isEqual(hex1, hex2));
            break; 
        case 6:
            System.out.println(op.isGreater(hex1, hex2));
            break;
        case 7:
            System.out.println(op.isLess(hex1, hex2));
            break; 
        case 8:
            System.out.println(op.hexToDec(hex));
            break;
        case 9:
            System.out.println(op.decToHex(num));
            break; 
      
        default:
        System.out.println("Enter a valid option");
        
    
    }
}
}