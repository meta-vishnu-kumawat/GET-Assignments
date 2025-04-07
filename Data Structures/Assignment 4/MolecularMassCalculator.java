import java.util.*;

public class MolecularMassCalculator {
    private static final Map<Character, Integer> atomicMasses = Map.of(
            'C', 12, 'O', 16, 'H', 1
    );

    public static int calculateMass(String formula) {
        Queue<Character> queue = new LinkedList<>();
        for (char ch : formula.toCharArray()) {
            queue.add(ch);
        }

        return parseFormula(queue);
    }

    private static int parseFormula(Queue<Character> queue) {
        Map<Character, Integer> elementCount = new HashMap<>();
        Stack<Map<Character, Integer>> groupStack = new Stack<>();

        while (!queue.isEmpty()) {
            char ch = queue.poll();

            if (atomicMasses.containsKey(ch)) {  
                int count = 1;

                if (!queue.isEmpty() && Character.isDigit(queue.peek())) {
                    count = queue.poll() - '0';
                }

                elementCount.put(ch, elementCount.getOrDefault(ch, 0) + count);

            } else if (ch == '(') {  
                groupStack.push(new HashMap<>(elementCount));
                elementCount.clear();

            } else if (ch == ')') {  
                int multiplier = 1;

                if (!queue.isEmpty() && Character.isDigit(queue.peek())) {
                    multiplier = queue.poll() - '0';
                }

                for (Map.Entry<Character, Integer> entry : elementCount.entrySet()) {
                    elementCount.put(entry.getKey(), entry.getValue() * multiplier);
                }

                if (!groupStack.isEmpty()) {
                    Map<Character, Integer> previousCount = groupStack.pop();
                    for (Map.Entry<Character, Integer> entry : previousCount.entrySet()) {
                        elementCount.put(entry.getKey(), elementCount.getOrDefault(entry.getKey(), 0) + entry.getValue());
                    }
                }
            }
        }

       
        int totalMass = 0;
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