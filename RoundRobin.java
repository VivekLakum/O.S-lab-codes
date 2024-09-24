import java.util.Scanner;

public class RoundRobinScheduling {

    public static void roundRobinScheduling() {
        Scanner sc = new Scanner(System.in);
        
        String[] processNames = new String[20];
        int[] arrivalTimes = new int[20];
        int[] burstTimes = new int[20];
        int[] completionTimes = new int[20];
        int[] tempBurstTimes = new int[20];
        int[] waitingTimes = new int[20];
        int[] turnaroundTimes = new int[20];
        
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalCompletionTime = 0;
        
        System.out.print("Enter number of processes: ");
        int numProcesses = sc.nextInt();
        sc.nextLine(); 
        
        System.out.println("Enter the process names: ");
        for (int i = 0; i < numProcesses; i++) {
            processNames[i] = sc.nextLine();
        }
        
        System.out.println("Enter arrival times: ");
        for (int i = 0; i < numProcesses; i++) {
            arrivalTimes[i] = sc.nextInt();
        }
        
        System.out.println("Enter CPU burst times: ");
        for (int i = 0; i < numProcesses; i++) {
            burstTimes[i] = sc.nextInt();
            tempBurstTimes[i] = burstTimes[i]; 
        }
        
        System.out.print("Enter quantum time: ");
        int quantumTime = sc.nextInt();
        
        int currentTime = 0;
        int completedProcesses = 0;
        
        while (completedProcesses < numProcesses) {
            boolean done = true;
            for (int i = 0; i < numProcesses; i++) {
                if (tempBurstTimes[i] > 0) {
                    done = false; 
                    if (tempBurstTimes[i] > quantumTime) {
                        currentTime += quantumTime;
                        tempBurstTimes[i] -= quantumTime;
                    } else {
                        currentTime += tempBurstTimes[i];
                        completionTimes[i] = currentTime;
                        tempBurstTimes[i] = 0;
                        completedProcesses++;
                    }
                }
            }
            if (done) {
                break;
            }
        }
        
        for (int i = 0; i < numProcesses; i++) {
            waitingTimes[i] = completionTimes[i] - arrivalTimes[i] - burstTimes[i];
            totalWaitingTime += waitingTimes[i];
            turnaroundTimes[i] = completionTimes[i] - arrivalTimes[i];
            totalTurnaroundTime += turnaroundTimes[i];
            totalCompletionTime += completionTimes[i];
        }
        
        double averageWaitingTime = (double) totalWaitingTime / numProcesses;
        double averageTurnaroundTime = (double) totalTurnaroundTime / numProcesses;
        double averageCompletionTime = (double) totalCompletionTime / numProcesses;
        
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println(processNames[i] + "\t" + arrivalTimes[i] + "\t" + burstTimes[i] + "\t" +
                               completionTimes[i] + "\t" + turnaroundTimes[i] + "\t" + waitingTimes[i]);
        }
        
        System.out.printf("Total completion time = %.2f\n", (double) totalCompletionTime);
        System.out.printf("Total turn around time = %.2f\n", (double) totalTurnaroundTime);
        System.out.printf("Total waiting time = %.2f\n", (double) totalWaitingTime);
        System.out.printf("Average completion time: %.2f\n", averageCompletionTime);
        System.out.printf("Average turn around time: %.2f\n", averageTurnaroundTime);
        System.out.printf("Average waiting time: %.2f\n", averageWaitingTime);
        System.out.println("\n" + "=".repeat(40) + "\n");  
    }

    public static void main(String[] args) {
  
        for (int i = 0; i < 3; i++) {
            roundRobinScheduling();
        }
    }
}
