package Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InfixExpressionEvaluate<T> extends StackImplement<T> {

   public List<String> infixToPostfix(String expression) {
    StackImplement<String> operators = new StackImplement<>();
    List<String> tokens = Arrays.asList(expression.trim().split(" "));
          List<String> postfix = new ArrayList<>();
    for (String token : tokens) {
        if (isOperand(token)) {
            postfix.add(token);
        } else if (token.equals("(")) {
            operators.push(token);
        } else if (token.equals(")")) {
            while (!operators.isEmpty() && !operators.peek().equals("(")) {
                postfix.add(operators.pop());
            }
            if (!operators.isEmpty() && operators.peek().equals("(")) {
                operators.pop(); 
            }
        } else if (isOperator(token)) {
            while (!operators.isEmpty() && isOperator(operators.peek()) &&
            ((operatorPrecedence(operators.peek()) > operatorPrecedence(token)) ||
                    (operatorPrecedence(operators.peek()) == operatorPrecedence(token)
                            && isLeftAssociative(token)))) {
                postfix.add(operators.pop());
            }
            operators.push(token);
        }
    }
    
    while (!operators.isEmpty()) {
        postfix.add(operators.pop());
    }

    return postfix;
}

    private boolean isOperand(String token) {
        return !(isOperator(token) || token.equals("(") || token.equals(")"));
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") ||
                token.equals("==") || token.equals("!=") || token.equals("<") || token.equals(">") ||
                token.equals("<=") || token.equals(">=") || token.equals("&&") || token.equals("||") ||
                token.equals("!");
    }

    private int operatorPrecedence(String op) {
        switch (op) {
            case "!":
                return 7;
            case "*":
            case "/":
                return 6;
            case "+":
            case "-":
                return 5;
            case "<":
            case ">":
            case "<=":
            case ">=":
                return 4;
            case "==":
            case "!=":
                return 3;
            case "&&":
                return 2;
            case "||":
                return 1;
            default:
                return 0;
        }
    }

    private boolean isLeftAssociative(String op) {
        return !op.equals("!");
    }
    private Object evaluatePostfix(String expression) {
        List<String> tokens = infixToPostfix(expression);
        StackImplement<Object> evalStack = new StackImplement<>(tokens.size());
        for (String token : tokens) {
            if (isOperand(token)) {
               
                try {
                    evalStack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    evalStack.push(0);
                }
            } else if (isOperator(token)) {
                if (token.equals("!")) {
                    Object operand = evalStack.pop();
                    boolean value = toBoolean(operand);
                    evalStack.push(!value);
                } else {
                    Object right = evalStack.pop();
                    Object left = evalStack.pop();
                    Object result = applyOperator(token, left, right);
                    evalStack.push(result);
                }
            }
        }
        return evalStack.pop();
    }

    private Object applyOperator(String op, Object leftObj, Object rightObj) {
        if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
            int left = toInteger(leftObj);
            int right = toInteger(rightObj);
            switch(op) {
                case "+": return left + right;
                case "-": return left - right;
                case "*": return left * right;
                case "/": 
                    if (right == 0) {
                        throw new ArithmeticException("Division by zero!");
                    }
                    return left / right;
            }
        } else if (op.equals("<") || op.equals(">") || op.equals("<=") || op.equals(">=")) {
            int left = toInteger(leftObj);
            int right = toInteger(rightObj);
            switch(op) {
                case "<":  return left < right;
                case ">":  return left > right;
                case "<=": return left <= right;
                case ">=": return left >= right;
            }
        } else if (op.equals("==") || op.equals("!=")) {
            if (leftObj instanceof Boolean || rightObj instanceof Boolean) {
                boolean left = toBoolean(leftObj);
                boolean right = toBoolean(rightObj);
                switch(op) {
                    case "==": return left == right;
                    case "!=": return left != right;
                }
            } else {
                int left = toInteger(leftObj);
                int right = toInteger(rightObj);
                switch(op) {
                    case "==": return left == right;
                    case "!=": return left != right;
                }
            }
        } else if (op.equals("&&") || op.equals("||")) {
            boolean left = toBoolean(leftObj);
            boolean right = toBoolean(rightObj);
            switch(op) {
                case "&&": return left && right;
                case "||": return left || right;
            }
        }
        throw new RuntimeException("Unknown operator: " + op);
    }

    private int toInteger(Object obj) {
        if (obj instanceof Integer) {
            return (Integer)obj;
        } else if (obj instanceof Boolean) {
            return ((Boolean)obj) ? 1 : 0;
        } else {
            throw new RuntimeException("Cannot convert to integer: " + obj);
        }
    }

    private boolean toBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean)obj;
        } else if (obj instanceof Integer) {
            return ((Integer)obj) != 0;
        } else {
            throw new RuntimeException("Cannot convert to boolean: " + obj);
        }
    }


    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InfixExpressionEvaluate<String> s = new InfixExpressionEvaluate<>();
     try {
        int choice;
       do {
        System.out.println("1. Evaluate Expression");
        System.out.println("2.Exit");
        choice  = sc.nextInt();
        switch (choice) {
            case 1:
            sc.nextLine();
            System.out.println("Enter expression. expression must be comma seprated ");
            System.out.println("result is : " + s.evaluatePostfix(sc.nextLine()));
                break;
            case 2:
            System.out.println("Exiting the program...!");
            break;
            default:
            System.out.println("Wrong choice");
                break;
        }
        
       } while (choice !=2);
     } catch (Exception e) {
        System.out.println(e.getMessage());
     }
       
        sc.close();
    }
}