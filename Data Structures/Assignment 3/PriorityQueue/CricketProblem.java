package PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

public class CricketProblem extends PriorityQueue {
    public CricketProblem(int totalBolwers) {

        super(totalBolwers);
    }

    public static void arrangement(PriorityQueue Cricket,int ballViratGoingToPlay) {
        ArrayList<Bolwer> ans = new ArrayList<>();
        System.out.println();
        while (Cricket.size() > 0 && ballViratGoingToPlay > 0) {
            Bolwer b = Cricket.peek();
            ans.add(b);
            Cricket.extractMax();
            if (b.getPriority() > 1) {
                b.setRemainingBall(b.getPriority() - 1);
                Cricket.insert(b);

            }
            ballViratGoingToPlay --;
        }
        System.out.println("Bowler Order to minimize virat's run:");
        System.out.print("[");
        for(Bolwer bolwer : ans){
            System.out.print(bolwer.getName()+", ");
        }
        System.out.println("]");

    }

    public static void main(String[] args) {

        PriorityQueue Cricket = new CricketProblem(10);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the  total number of balls Virat is going to play: ");
        int ballViratGoingToPlay = scanner.nextInt();
        System.out.print("Enter the number of bowlers: ");
        int numBowlers = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numBowlers; i++) {
            System.out.println("Enter the name of Bolwer " + (i + 1) + ":");
            String name = scanner.nextLine();
            System.out.println("Enter the number  of balls "+ name +" going to ball:");
            int ball = scanner.nextInt();
            Cricket.insert(new Bolwer(name, ball));
            scanner.nextLine();
        }
        arrangement(Cricket,ballViratGoingToPlay);
        scanner.close();
    }
}


