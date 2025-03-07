
public class HexOperation {
    private String hex = "0123456789ABCDEF";
private int base = 16;

//function to convert hexadecimal number to decimal number
    public int hexToDec(String hexNum){
      
      int decimal = 0;
     hexNum =  hexNum.toUpperCase();
      for(int i  = 0;i<hexNum.length();i++){
         int digit = hex.indexOf(hexNum.charAt(i));

         decimal = decimal *base +digit;
      }
      return decimal;
    }


    //function to convert decimal number to hexadecimal number
    public String decToHex(int decimal){
        if(decimal == 0)return "0"; 
    StringBuilder hexNum = new StringBuilder("");

    char[] hexChars = hex.toCharArray();
    while(decimal > 0){
      int reminder = decimal % base;
      hexNum.insert(0,hexChars[reminder]);
      decimal/=16;
    }
    return hexNum.toString();

    }

    //function to addtion of two Hexadecimal number
    public String addition(String num1,String num2){
        return decToHex(hexToDec(num1)+hexToDec(num2));
    }
//function to  substraction of two Hexadecimal number
public String substraction(String num1,String num2){
    return decToHex(hexToDec(num1) - hexToDec(num2));
}
//function to multiplication of two Hexadecimal number
public String multiply(String num1,String num2){
return decToHex(hexToDec(num1)*hexToDec(num2));
}
//function to devision of two Hexadecimal number
public String division(String num1,String num2){
    return decToHex(hexToDec(num1)/hexToDec(num2));
    }
    public boolean isGreater(String num1 , String num2){
    
        num1 = num1.toUpperCase();
        num2 = num2.toUpperCase();
        num1 =  num1.replaceFirst("0", "");
        num2 =  num2.replaceFirst("0", "");
    if(num1.length() > num2.length()){
        return true;
    }
    if(num1.length() < num2.length()){
        return false; 
    }
    for(int i = 0;i<num1.length();i++){
        if(num1.charAt(i) > num2.charAt(i)){
            return true;
        }
        if(num1.charAt(i) < num2.charAt(i)){
            return false;
        }

    }
    return false;
    }
    public boolean isLess(String num1 , String num2){
    
        num1 = num1.toUpperCase();
        num2 = num2.toUpperCase();
        num1 =  num1.replaceFirst("0", "");
        num2 =  num2.replaceFirst("0", "");
    if(num1.length() < num2.length()){
        return true;
    }
    if(num1.length() > num2.length()){
    return false;
    }
    for(int i = 0;i<num1.length();i++){
        if(num1.charAt(i) < num2.charAt(i)){
            return true;
        }
        if(num1.charAt(i) > num2.charAt(i)){
        return false;
         }

    }
    return false;
    }
    public boolean isEqual(String num1 , String num2){   
        num1 = num1.toUpperCase();
        num2 = num2.toUpperCase();
        num1 =  num1.replaceFirst("0", "");
        num2 =  num2.replaceFirst("0", "");
    if(num1.length() < num2.length()){
        return false;
    }
    if(num1.length() > num2.length()){
        return false;
    }
    for(int i = 0;i<num1.length();i++){
        if(num1.charAt(i) < num2.charAt(i)){
            return false;
        }
        if(num1.charAt(i) > num2.charAt(i)){
        return false;
        }

    }
    return true;
    }

}
