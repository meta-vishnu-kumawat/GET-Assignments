import java.util.Arrays;
import java.util.Scanner;

public class JobScheduler {
    private int[][] processes; 
    private int[] completionTime;
    private int[] waitingTime;
    private int[] turnaroundTime;
    
    public JobScheduler(int[][] processes) {
        this.processes = processes;
        int n = processes.length;
        completionTime = new int[n];
        waitingTime = new int[n];
        turnaroundTime = new int[n];
        calculateTimes();
    }

    // Method to calculate Completion Time, Turnaround Time, and Waiting Time
    private void calculateTimes() {
        int n = processes.length;
        int currentTime = 0;

        for (int i = 0; i < n; i++) {
            int arrivalTime = processes[i][0];
            int burstTime = processes[i][1];

            
            if (currentTime < arrivalTime) {
                currentTime = arrivalTime;
            }

            
            completionTime[i] = currentTime + burstTime;
            turnaroundTime[i] = completionTime[i] - arrivalTime;
            waitingTime[i] = turnaroundTime[i] - burstTime;

         
            currentTime = completionTime[i];
        }
    }

    
    public int[] getCompletionTime() {
        return completionTime;
    }


    public int[] getWaitingTime() {
        return waitingTime;
    }

    
    public int[] getTurnaroundTime() {
        return turnaroundTime;
    }

  
    public double getAverageWaitingTime() {
        int totalWaitingTime = Arrays.stream(waitingTime).sum();
        return (double) totalWaitingTime / waitingTime.length;
    }

   
    public int getMaxWaitingTime() {
        return Arrays.stream(waitingTime).max().orElse(0);
    }

    // Display results
    public void displayResults() {
        System.out.println("\nProcess\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        for (int i = 0; i < processes.length; i++) {
            System.out.printf("%d\t%d\t%d\t%d\t\t%d\t\t%d\n",
                i + 1, processes[i][0], processes[i][1], 
                completionTime[i], turnaroundTime[i], waitingTime[i]);
        }
        System.out.printf("\nAverage Waiting Time: %.2f\n", getAverageWaitingTime());
        System.out.println("Maximum Waiting Time: " + getMaxWaitingTime());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        int[][] inputProcesses = new int[numProcesses][2];

       
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            inputProcesses[i][0] = scanner.nextInt();
            
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            inputProcesses[i][1] = scanner.nextInt();
        }

       
        scanner.close();

        
        JobScheduler scheduler = new JobScheduler(inputProcesses);
        scheduler.displayResults();
    }
}