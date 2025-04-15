package Graph;
import java.util.*;

interface Graph {
    boolean isConnected(); // Check if the graph is connected
    Set<Integer> reachable(int a); // Get all nodes reachable from a
    Map<Integer, List<Edge>> mst(); // Minimum Spanning Tree using greedy approach
    List<Integer> shortestPath(int a, int b); // Shortest path from a to b using Dijkstra's algorithm
    public static class Edge {
        int target;
        int weight;
    
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
}


class UndirectedWeightedGraph implements Graph {
    private final Map<Integer, List<Edge>> adjacencyList = new HashMap<>();

    

    void addEdge(int u, int v, int weight) {
        adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, weight));
        adjacencyList.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, weight)); 
    }

    @Override
    public boolean isConnected() {
        if (adjacencyList.isEmpty()) return false;
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int startNode = adjacencyList.keySet().iterator().next();
        stack.push(startNode);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                for (Edge edge : adjacencyList.getOrDefault(node, new ArrayList<>())) {
                    stack.push(edge.target);
                }
            }
        }
        return visited.size() == adjacencyList.size();
    }

    @Override
    public Set<Integer> reachable(int a) {
        Set<Integer> reachableNodes = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(a);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!reachableNodes.contains(node)) {
                reachableNodes.add(node);
                for (Edge edge : adjacencyList.getOrDefault(node, new ArrayList<>())) {
                    stack.push(edge.target);
                }
            }
        }
        return reachableNodes;
    }


    @Override
public Map<Integer, List<Edge>> mst() {
    Map<Integer, List<Edge>> mstGraph = new HashMap<>();
    PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
    Set<Integer> visited = new HashSet<>();
    int startNode = adjacencyList.keySet().iterator().next();
    
    visited.add(startNode);
    pq.addAll(adjacencyList.get(startNode));

    while (!pq.isEmpty()) {
        Edge edge = pq.poll();
        if (!visited.contains(edge.target)) {
            visited.add(edge.target);
            mstGraph.computeIfAbsent(startNode, k -> new ArrayList<>()).add(edge);
            pq.addAll(adjacencyList.get(edge.target));
            startNode = edge.target;
        }
    }
    
    return mstGraph;
}

    @Override
    public List<Integer> shortestPath(int a, int b) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> previousNodes = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        distances.put(a, 0);
        pq.add(a);

        while (!pq.isEmpty()) {
            int current = pq.poll();
            for (Edge edge : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                int newDist = distances.getOrDefault(current, Integer.MAX_VALUE) + edge.weight;
                if (newDist < distances.getOrDefault(edge.target, Integer.MAX_VALUE)) {
                    distances.put(edge.target, newDist);
                    previousNodes.put(edge.target, current);
                    pq.add(edge.target);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (Integer at = b; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path.contains(a) ? path : Collections.emptyList();
    }
}