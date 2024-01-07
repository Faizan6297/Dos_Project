package dsa;

	import java.util.Scanner; 
	public class dos_project1 { 
	 public static void main(String[] args) { 
	 Scanner sc = new Scanner(System.in); 
	 boolean fcfsChosen = false; 
	 while (true) { 
	 System.out.println(); 
	 System.out.println("Choose the scheduling algorithm:"); 
	 System.out.println("1. First-Come, First-Served (FCFS)"); 
	 System.out.println("2. Round Robin (RR)"); 
	 System.out.println("3. Terminate Program"); 
	 int choice = sc.nextInt(); 
	 switch (choice) { 
	 case 1: 
	 if (!fcfsChosen) { 
	 fcfsAlgorithm(sc); 
	fcfsChosen = true; 
	 } else { 
	 rrAlgorithm(sc); 
	 } 
	 break; 
	 case 2: 
	 rrAlgorithm(sc); 
	break; 
	 case 3: 
	 System.out.println("Terminating the program..."); 
	 System.exit(0); 
	 break;
	 default: 
	 System.out.println("Invalid choice!"); 
	 } 
	 } 
	 } 
	 private static void fcfsAlgorithm(Scanner sc) { 
	 System.out.print("Enter the number of processes: "); 
	 int n = sc.nextInt(); 
	 int burstTimes[] = new int[n]; 
	 int arrivalTimes[] = new int[n]; 
	 System.out.println("\nEnter the Burst Time for each process."); 
	 for (int i = 0; i < n; i++) { 
	 System.out.print("\nFor Process " + (i + 1) + ": "); 
	 burstTimes[i] = sc.nextInt(); 
	 } 
	 System.out.println("\nEnter the arrival time for each process."); 
	 for (int j = 0; j < n; j++) { 
	 System.out.print("\nFor Process " + (j + 1) + ": "); 
	 arrivalTimes[j] = sc.nextInt(); 
	 } 
	 calculateAndDisplayTimes(n, burstTimes, arrivalTimes); 
	 } 
	 private static void rrAlgorithm(Scanner sc) { 
	 System.out.print("Enter the number of processes: "); 
	 int n = sc.nextInt(); 
	 int processes[] = new int[n]; 
	 int burstTimes[] = new int[n]; 
	 int arrivalTimes[] = new int[n]; 
	 for (int i = 0; i < n; i++) { 
	 System.out.print("Enter burst time for process " + (i + 1) + ": "); 
	 burstTimes[i] = sc.nextInt(); 
	 processes[i] = i + 1; 
	 } 
	 for (int i = 0; i < n; i++) { 
	 System.out.print("Enter arrival time for process " + (i + 1) + ":");
	 arrivalTimes[i] = sc.nextInt(); 
	 } 
	 System.out.print("Enter the time quantum: "); 
	 int quantum = sc.nextInt(); 
	 findAvgTime(processes, n, burstTimes, quantum, arrivalTimes); 
	 } 
	 private static void calculateAndDisplayTimes(int n, int[] 
	burstTimes, int[] arrivalTimes) { 
	 int wt[] = new int[n]; 
	 int rt[] = new int[n]; 
	 int ct[] = new int[n];
	wt[0] = 0; 
	 ct[0] = burstTimes[0]; 
	 for (int i = 1; i < n; i++) { 
	 wt[i] = ct[i - 1] - arrivalTimes[i]; 
	 if (wt[i] < 0) { 
	 wt[i] = 0; 
	 } 
	 rt[i] = wt[i]; 
	 ct[i] = ct[i - 1] + burstTimes[i]; 
	 } 
	 System.out.println("\nProcesses || Burst Time || Arrival Time || Waiting Time || Response Time || Completion Time "); 
	 float awt = 0; 
	 float art = 0; 
	 float att = 0; 
	 for (int i = 0; i < n; i++) { 
	 System.out.println((i + 1) + "\t ||\t" + burstTimes[i] + 
	"\t||\t" + arrivalTimes[i] + "\t||\t" + wt[i] + "\t||\t "
	 + rt[i] + "\t||\t " + ct[i]); 
	 awt += wt[i]; 
	 art += rt[i]; 
	 att += (ct[i] - arrivalTimes[i]); 
	 } 
	 awt = awt / n; 
	 art = art / n; 
	 att = att / n; 
	 System.out.println("\nAverage waiting time = " + awt); 
	 System.out.println("\nAverage response time = " + art); 
	 System.out.println("\nAverage turnaround time = " + att); 
	 } 
	 static void findAvgTime(int processes[], int n, int burstTimes[], 
	int quantum, int arrivalTimes[]) { 
	 int wt[] = new int[n], tat[] = new int[n], ct[] = new int[n], 
	rt[] = new int[n]; 
	 double total_wt = 0, total_tat = 0, total_rt = 0; 
	 findWaitingTime(processes, n, burstTimes, wt, quantum, 
	arrivalTimes, ct, rt); 
	 findTurnAroundTime(processes, n, burstTimes, wt, tat, ct, 
	arrivalTimes); 
	 System.out.println("Processes " + " Burst time " + " Waiting time " + " Turnaround time " + " Response time"); 
	 for (int i = 0; i < n; i++) { 
	 total_wt += wt[i]; 
	 total_tat += tat[i];
	total_rt += rt[i];
	 System.out.println(" " + processes[i] + "\t\t" + 
	burstTimes[i] + "\t " + wt[i] + "\t\t " + tat[i] + "\t\t " + rt[i]); 
	 } 
	 System.out.println("Average waiting time = " + total_wt / 
	n); 
	 System.out.println("Average turnaround time = " + total_tat
	/ n); 
	 System.out.println("Average response time = " + total_rt / 
	n); 
	 // Compare the efficiency of algorithms based on average waiting time
	 compareAlgorithmsEfficiency(total_wt / n, 
	calculateFCFSAvgWaitingTime(burstTimes, arrivalTimes)); 
	 
	 } 
	 static void findWaitingTime(int processes[], int n, int
	burstTimes[], int wt[], int quantum, int arrivalTimes[], int ct[], 
	int rt[]) { 
	 int rem_bt[] = new int[n]; 
	 for (int i = 0; i < n; i++) 
	 rem_bt[i] = burstTimes[i]; 
	 int t = 0; 
	 boolean visited[] = new boolean[n]; 
	 while (true) { 
	 boolean done = true; 
	 for (int i = 0; i < n; i++) { 
	 if (rem_bt[i] > 0 && arrivalTimes[i] <= t) { 
	 done = false; 
	 if (!visited[i]) { 
	 rt[i] = t - arrivalTimes[i]; 
	 visited[i] = true; 
	 } 
	 if (rem_bt[i] > quantum) { 
	 t += quantum; 
	rem_bt[i] -= quantum; 
	 } else { 
	 t += rem_bt[i]; 
	 wt[i] = t - burstTimes[i] - arrivalTimes[i]; 
	 rem_bt[i] = 0; 
	ct[i] = t; 
	 }}}
	 if (done) 
	 break; 
	 } 
	 } 
	 static void findTurnAroundTime(int processes[], int n, int
	burstTimes[], int wt[], int tat[], int ct[], int arrivalTimes[]) { 
	 for (int i = 0; i < n; i++) 
	 tat[i] = ct[i] - arrivalTimes[i]; 
	 } 
	 static double calculateFCFSAvgWaitingTime(int[] burstTimes, 
	int[] arrivalTimes) { 
	 int n = burstTimes.length; 
	 int wt[] = new int[n]; 
	 int ct[] = new int[n]; 
	 int prevCT = 0; 
	 for (int i = 0; i < n; i++) { 
	 wt[i] = prevCT - arrivalTimes[i]; 
	 if (wt[i] < 0) { 
	 wt[i] = 0; 
	 } 
	 ct[i] = prevCT + burstTimes[i]; 
	 prevCT = ct[i]; 
	 } 
	 double total_wt = 0; 
	 for (int i = 0; i < n; i++) { 
	 total_wt += wt[i]; 
	 } 
	 return total_wt / n; 
	 } 
	 static void compareAlgorithmsEfficiency(double avgWaitingTimeRR, 
	double avgWaitingTimeFCFS) { 
	 if (avgWaitingTimeRR < avgWaitingTimeFCFS) { 
	 System.out.println("Round Robin (RR) algorithm results in the minimum average waiting time = "+avgWaitingTimeRR); 
	 } else if (avgWaitingTimeRR > avgWaitingTimeFCFS) { 
	 System.out.println("FCFS algorithm results in the minimum average waiting time = "+avgWaitingTimeFCFS); 
	 } else { 
	 System.out.println("Both algorithms have the same average waiting time."); 
	 } 
	 } 
	}



