import java.util.Scanner;

class Process {
    int pid;
    int AT;
    int BT;
    int rBT;
    int WT;
    int TAT;
    int CT;
    int lastExecTime;
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();

        System.out.println("Enter the time quantum: ");
        int TQ = sc.nextInt();

        Process[] proc = new Process[n];
        int currTime = 0, completed = 0;
        double avgWT = 0, avgTAT = 0, avgCT = 0;

        for (int i = 0; i < n; i++) {
            proc[i] = new Process();

           
            System.out.print("Process ID: ");
            proc[i].pid = sc.nextInt();

            System.out.print("Arrival Time: ");
            proc[i].AT = sc.nextInt();

            System.out.print("Burst Time: ");
            proc[i].BT = sc.nextInt();

            proc[i].rBT = proc[i].BT;
            proc[i].WT = 0;
            proc[i].lastExecTime = proc[i].AT;
        }

        while (completed < n) {
            boolean processExecuted = false;
            for (int i = 0; i < n; i++) {
                if (proc[i].rBT == 0 || proc[i].AT > currTime) continue;
                else if (proc[i].rBT <= TQ) {
                    currTime += proc[i].rBT;
                    proc[i].rBT = 0;
                    proc[i].CT = currTime;
                    proc[i].TAT = proc[i].CT - proc[i].AT;
                    completed++;
                    processExecuted = true;
                } else {
                    currTime += TQ;
                    proc[i].rBT -= TQ;
                    processExecuted = true;
                }
                proc[i].lastExecTime = currTime;
            }
            if (!processExecuted) break;
        }

        for (int i = 0; i < n; i++) {
            proc[i].WT = proc[i].TAT - proc[i].BT;
            avgTAT += proc[i].TAT;
            avgWT += proc[i].WT;
            avgCT += proc[i].CT;
        }

        avgWT /= n;
        avgTAT /= n;
        avgCT /= n;

        System.out.printf("Average Completion Time: %.2f\n", avgCT);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTAT);
        System.out.printf("Average Waiting Time: %.2f\n", avgWT);

        sc.close();
    }
}
