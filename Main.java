/* 
tested working on onlinegdb.com

important notes from (https://www.youtube.com/watch?v=T0FXvTHcYi4)
given -> max, allocation, available
max - allocation = need
available + allocation = new available (checkSafe: include boolean)
*/


import java.util.*; // import java packages

public class Main { // make sure Main.java is created

    static int p; // number of processes
    static int r; // number of resources

    static int[][] max;
    static int[][] allocation;
    static int[][] need;
    static int[] available;

    public static void main(String[] args) { // main function
        // process in-order 
        scanVal();         // get input from user 
        calculateNeed();   // calculate the need matrix (need = max - allocation) -- only for printMatrix
        printMatrix();     // print all matrices (max, allocation, available, need, new available)
        isSafe();          // check system safety and safest state 
    }

    public static void scanVal() { // function to get all necessary user input
        Scanner scan = new Scanner(System.in); // scan input

        System.out.print("Enter the number of processes: "); // scan p = processes
        p = scan.nextInt();
        System.out.print("Enter the number of resources: "); // scan r = resources
        r = scan.nextInt();

        max = new int[p][r]; // initialize necessary matrices
        allocation = new int[p][r];
        need = new int[p][r];
        available = new int[r];

        System.out.println("\nEnter the Max matrix:"); // scan max 
        for (int i = 0; i < p; i++) {
            System.out.print("P" + i + ": ");
            for (int j = 0; j < r; j++) {
                max[i][j] = scan.nextInt();
            }
        }

        System.out.println("\nEnter the Allocation matrix:"); // scan allocation
        for (int i = 0; i < p; i++) {
            System.out.print("P" + i + ": ");
            for (int j = 0; j < r; j++) {
                allocation[i][j] = scan.nextInt();
            }
        }

        System.out.println("\nEnter Available resources:"); // scan available
        for (int i = 0; i < r; i++) {
            available[i] = scan.nextInt();
        }
    }

    // modified the code of 29AjayKumar: https://www.geeksforgeeks.org/program-bankers-algorithm-set-1-safety-algorithm/
    static void calculateNeed() { // function to find the need of each process
        for (int i = 0; i < p; i++)
            // calculating need of each p
            for (int j = 0; j < r; j++)
                // need = max - allocation
                need[i][j] = max[i][j] - allocation[i][j];
    }

    static void printMatrix() { // function to print matrices
        System.out.println("\nMax Matrix:"); // print max matrix
        for (int i = 0; i < p; i++) {
            System.out.print("P" + i + ": ");
            for (int j = 0; j < r; j++) {
                System.out.print(max[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nAllocation Matrix:"); // print allocation matrix
        for (int i = 0; i < p; i++) {
            System.out.print("P" + i + ": ");
            for (int j = 0; j < r; j++) {
                System.out.print(allocation[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nAvailable Resources:"); // print available resources
        for (int i = 0; i < r; i++) {
            System.out.print("R" + i + ": " + available[i] + " ");
        }
        System.out.println();

        System.out.println("\nNeed Matrix:"); // print need matrix
        for (int i = 0; i < p; i++) {
            System.out.print("P" + i + ": ");
            for (int j = 0; j < r; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }
    }

    // modified the code of 29AjayKumar: https://www.geeksforgeeks.org/program-bankers-algorithm-set-1-safety-algorithm/
    static boolean isSafe() { // function to find the system is in safe state or not
        // function to calculate the need matrix
        // calculateNeed(); -- removed from reference

        // mark all processes as unfinished
        boolean[] finish = new boolean[p];
        int[] work = new int[r];

        // to store safe sequence
        int[] safeSeq = new int[p];

        // make a copy of available resources
        for (int i = 0; i < r; i++)
            work[i] = available[i];

        // while all processes are not finished or system is not in safe state.
        int count = 0;
        while (count < p) {
            // find a process which is not finished and whose needs can be satisfied with current work[] resources
            boolean found = false;
            for (int i = 0; i < p; i++) {
                if (finish[i] == false) {
                    // check if for all resources of current P need is less than work
                    int j;
                    for (j = 0; j < r; j++)
                        if (need[i][j] > work[j])
                            break;

                    // if all needs of p (i) were satisfied
                    if (j == r) {
                        // add the allocated resources of current p (i) to the available/work resources i.e. free the resources
                        for (int k = 0; k < r; k++)
                            work[k] += allocation[i][k];

                        // add this process to safe sequence.
                        safeSeq[count++] = i;
                        
                        // mark this p (i) as finished
                        finish[i] = true;
                        
                        found = true;
                    }
                }
            }

            // if we could not find a next process in safe sequence.
            if (found == false) {
                System.out.println("\nSystem is not in safe state.");
                return false;
            }
        }

        // if system is in safe state then safe sequence will be as below
        System.out.print("\nSystem is in safe state.\nSafe sequence is: ");
        for (int i = 0; i < p; i++)
            System.out.print("P"+ safeSeq[i] + " ");
        System.out.println();
        return true;
    }
}
