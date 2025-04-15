package Graph;
import java.util.*;

public class GraphMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph();

        while (true) {
            System.out.println("\nGraph Menu:");
            System.out.println("1. Add Edge");
            System.out.println("2. Check if Graph is Connected");
            System.out.println("3. Find Reachable Nodes");
            System.out.println("4. Get Minimum Spanning Tree (MST)");
            System.out.println("5. Find Shortest Path");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter node 1: ");
                    int u = scanner.nextInt();
                    System.out.print("Enter node 2: ");
                    int v = scanner.nextInt();
                    System.out.print("Enter weight: ");
                    int weight = scanner.nextInt();
                    graph.addEdge(u, v, weight);
                    System.out.println("Edge added successfully.");
                    break;
                
                case 2:
                    System.out.println("Graph is connected: " + graph.isConnected());
                    break;

                case 3:
                    System.out.print("Enter starting node: ");
                    int startNode = scanner.nextInt();
                    System.out.println("Reachable nodes: " + graph.reachable(startNode));
                    break;

                case 4:
                    System.out.println("Minimum Spanning Tree (MST): " + graph.mst());
                    break;

                case 5:
                    System.out.print("Enter start node: ");
                    int src = scanner.nextInt();
                    System.out.print("Enter destination node: ");
                    int dest = scanner.nextInt();
                    System.out.println("Shortest path: " + graph.shortestPath(src, dest));
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}