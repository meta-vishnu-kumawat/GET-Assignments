import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Polynomial {
    List<Term>terms;

    public Polynomial(){
        terms = new ArrayList<>();
    }
   
    public void addTerm(Term term){
        terms.add(term);
    }
    public int getDegree() {
      
        return terms.stream().mapToInt(Term::getDegree).max().orElse(0);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Term term : terms) {
            if (sb.length() > 0) sb.append(" + ");
            sb.append(term.toString());
        }
        return sb.toString();
    }


    public static class Term {
         private int coefficient;
         private HashMap<Character,Integer>variables;

         public Term(int coefficient, HashMap<Character,Integer>variables){
             this.coefficient = coefficient;
             this.variables = variables;
         }
        
    public int getCoffient(){
        return coefficient;
    }

    public HashMap<Character,Integer> getVariable(){
        return variables;
    }
    public int getDegree() {
       int sum = 0;
        for(Character str:variables.keySet()){
           sum += variables.get(str);
        }
        return sum;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(coefficient);
        for (Character var : variables.keySet()) {
            sb.append(var).append("^").append(variables.get(var));
        }
        return sb.toString();
    }

    
    }
    public static void main(String[] args) {
     
        
                // Term 1: 3x^2y^4
                HashMap<Character, Integer> vars1 = new HashMap<>();
                vars1.put('x', 2);
                vars1.put('y', 4);
                Term term1 = new Term(3, vars1);
        
                // Term 2: -5z^2
                HashMap<Character, Integer> vars2 = new HashMap<>();
                vars2.put('z', 2);
                Term term2 = new Term(-5, vars2);
        
                // Term 3: Constant 20
                Term term3 = new Term(20, new HashMap<>());
        
                // Create Polynomial and add terms
                Polynomial poly = new Polynomial();
                poly.addTerm(term1);
                poly.addTerm(term2);
                poly.addTerm(term3);
        
                // Print Polynomial
                System.out.println("Polynomial: " + poly.toString());
                System.out.println("Degree: " + poly.getDegree());
            
        }
    
}
