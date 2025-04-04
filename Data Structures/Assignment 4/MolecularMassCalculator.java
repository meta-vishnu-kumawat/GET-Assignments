import java.util.*;

public class MolecularMassCalculator {
    private static final Map<Character, Integer> atomicMasses = Map.of('C', 12, 'O', 16, 'H', 1);
    private static int i; // Global index for recursive parsing

    public static int calculateMass(String formula) {
        i = 0;
        return parseFormula(formula);
    }

    private static int parseFormula(String formula) {
        int totalMass = 0;
        Map<Character, Integer> elementCount = new HashMap<>();

        while (i < formula.length()) {
            char ch = formula.charAt(i);

            if (atomicMasses.containsKey(ch)) { 
                i++; 
                int count = (i < formula.length() && Character.isDigit(formula.charAt(i))) 
                            ? formula.charAt(i++) - '0' : 1;
                elementCount.put(ch, elementCount.getOrDefault(ch, 0) + count);

            } else if (ch == '(') {
                i++;
                int nestedMass = parseFormula(formula);

                int multiplier = (i < formula.length() && Character.isDigit(formula.charAt(i))) 
                                 ? formula.charAt(i++) - '0' : 1;

                totalMass += nestedMass * multiplier;

            } else if (ch == ')') { 
                i++;
                break;
            }
        }

        
        for (Map.Entry<Character, Integer> entry : elementCount.entrySet()) {
            totalMass += atomicMasses.get(entry.getKey()) * entry.getValue();
        }

        return totalMass;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter molecular formula: ");
        String formula = scanner.nextLine();

        int mass = calculateMass(formula);
        System.out.println("Molecular mass of " + formula + " is: " + mass);

        scanner.close();
    }
}