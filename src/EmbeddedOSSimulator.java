import java.util.*;

public class EmbeddedOSSimulator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Queue<Process> readyQueue = new LinkedList<>();

        List<Process> allProcesses = new ArrayList<>();

        ResourceManager rm = new ResourceManager();

        System.out.print("Enter number of tasks: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {

            System.out.print("Enter burst time for task " + i + ": ");
            int bt = sc.nextInt();

            System.out.print("Enter memory required: ");
            int mem = sc.nextInt();

            if (rm.allocate(mem)) {

                Process p = new Process(i, bt, mem);
                readyQueue.add(p);
                allProcesses.add(p);

            } else {

                System.out.println("Not enough memory for task " + i);

            }
        }

        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();

        System.out.println("\n---- Embedded OS Simulation Start ----\n");

        roundRobin(readyQueue, quantum, rm, allProcesses);
        System.out.println("\nAll tasks completed.");

    }

    static void displayTable(List<Process> processes) {

        System.out.printf("\n%-5s %-12s %-8s %-10s\n", "PID", "STATE", "MEMORY", "REMAINING");

        for (Process p : processes) {
            System.out.printf(
                    "%-5d %-12s %-8d %-10d\n",
                    p.pId,
                    p.state,
                    p.memory,
                    p.remainingTime
            );
        }

        System.out.println();
    }

    static void roundRobin(Queue<Process> queue, int quantum, ResourceManager rm, List<Process> all){

        while (!queue.isEmpty()) {

            Process p = queue.poll();

            p.state = "RUNNING";
            displayTable(all);

            int execTime = Math.min(quantum, p.remainingTime);

            System.out.println("Running Task " + p.pId + " for " + execTime + " units");

            p.remainingTime -= execTime;

            try {
                Thread.sleep(1000);
            } catch (Exception e) {}

            if (p.remainingTime > 0) {

                p.state = "READY";
                queue.add(p);

            } else {

                p.state = "TERMINATED";
                rm.release(p.memory);

                System.out.println("Task " + p.pId + " completed\n");

            }

        }

    }
}