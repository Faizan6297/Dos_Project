package dsa;
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Scanner; 
public class BankersAlgotithm { 
 int[][] max; 
 int[][] allocation; 
 int[][] need; 
 
 int[] available; 
 int numOfProcesses; 
 int numOfResources; 
 public BankersAlgotithm(int[][] max, int[][] allocation, int[] 
available) { 
 this.max = max; 
 this.allocation = allocation; 
 this.available = available; 
 numOfProcesses = max.length; 
 numOfResources = available.length; 
 need = new int[numOfProcesses][numOfResources]; 
 // Calculate the need matrix
 for (int i = 0; i < numOfProcesses; i++) { 
 for (int j = 0; j < numOfResources; j++) { 
 need[i][j] = max[i][j] - allocation[i][j]; 
 } 
 } 
 } 
 public boolean isSafeState() { 
 int[] work = Arrays.copyOf(available, numOfResources); 
 boolean[] finish = new boolean[numOfProcesses]; 
 int count = 0; 
 while (count < numOfProcesses) { 
 boolean found = false; 
 for (int i = 0; i < numOfProcesses; i++) { 
 if (!finish[i]) { 
 boolean canAllocate = true; 
 for (int j = 0; j < numOfResources; j++) { 
 if (need[i][j] > work[j]) { 
 canAllocate = false; 
break; 
 } 
 } 
 if (canAllocate) { 
 for (int j = 0; j < numOfResources; j++) { 
 work[j] += allocation[i][j]; 
 } 
 finish[i] = true; 
 count++; 
found = true; 
 } 
 } 
 } 
 
 
if (!found) { 
 break; 
 } 
 } 
 return count == numOfProcesses; 
 } 
 public int requestResources(int processNum, int[] request) { 
 for (int i = 0; i < numOfResources; i++) { 
 if (request[i] > available[i] || request[i] > 
need[processNum][i]) { 
 return -1; 
 } 
 } 
 for (int i = 0; i < numOfResources; i++) { 
 available[i] -= request[i]; 
 allocation[processNum][i] += request[i]; 
 need[processNum][i] -= request[i]; 
 } 
 if (!isSafeState()) { 
 for (int i = 0; i < numOfResources; i++) { 
 available[i] += request[i]; 
 allocation[processNum][i] -= request[i]; 
 need[processNum][i] += request[i]; 
 } 
 return 0; 
 } 
 return 1; 
 } 
 public ArrayList<Integer> getSafeSequence() { 
 int[] work = Arrays.copyOf(available, numOfResources); 
 boolean[] finish = new boolean[numOfProcesses]; 
 ArrayList<Integer> safeSeq = new ArrayList<>(); 
 for (int k = 0; k < numOfProcesses; k++) { 
 for (int i = 0; i < numOfProcesses; i++) { 
 if (!finish[i]) { 
 boolean canAllocate = true; 
 for (int j = 0; j < numOfResources; j++) { 
 if (need[i][j] > work[j]) { 
 canAllocate = false; 
break; 
 }} 
 
 
if (canAllocate) {
 for (int j = 0; j < numOfResources; j++) { 
 work[j] += allocation[i][j]; 
 } 
 safeSeq.add(i); 
finish[i] = true; 
 } 
 } 
 } 
 } 
 if (safeSeq.size() != numOfProcesses) { 
 return null; 
 } 
 return safeSeq; 
 } 
 public static void main(String[] args) { 
 Scanner scanner = new Scanner(System.in); 
 System.out.print("Enter the number of processes: "); 
 int numOfProcesses = scanner.nextInt(); 
 System.out.print("Enter the number of resources: "); 
 int numOfResources = scanner.nextInt(); 
 int[][] max = new int[numOfProcesses][numOfResources]; 
 int[][] allocation = new int[numOfProcesses][numOfResources]; 
 int[] available = new int[numOfResources]; 
 System.out.println("Enter the Max matrix:"); 
 for (int i = 0; i < numOfProcesses; i++) { 
 for (int j = 0; j < numOfResources; j++) { 
 max[i][j] = scanner.nextInt(); 
 } 
 } 
 System.out.println("Enter the Allocation matrix:"); 
 for (int i = 0; i < numOfProcesses; i++) { 
 for (int j = 0; j < numOfResources; j++) { 
 allocation[i][j] = scanner.nextInt(); 
 } 
 } 
 System.out.println("Enter the Available resources:"); 
 for (int i = 0; i < numOfResources; i++) { 
 available[i] = scanner.nextInt(); 
 } 
 
 
 System.out.println("Enter the Available resources:"); 
 for (int i = 0; i < numOfResources; i++) { 
 available[i] = scanner.nextInt(); 
 } 
 BankersAlgotithm banker = new BankersAlgotithm(max, 
allocation, available); 
 System.out.println("Need Matrix:"); 
 for (int i = 0; i < banker.numOfProcesses; i++) { 
 System.out.println(Arrays.toString(banker.need[i])); 
 } 
 ArrayList<Integer> safeSequence = banker.getSafeSequence(); 
 if (safeSequence != null) { 
 System.out.println("The system is in a safe state."); 
 System.out.println("Safe sequence: " + safeSequence); 
 } else { 
 System.out.println("The system is not in a safe state."); 
 } 
 System.out.println("Enter the number of instances of type R2 that P3 wants to request:"); 
 int requestR2 = scanner.nextInt(); 
 int processNum = 2; 
 int[] request = {0, requestR2, 0, 0}; 
 int result = banker.requestResources(processNum, request); 
 if (result == 1) { 
 System.out.println("Request can be granted immediately."); 
 } else if (result == 0) { 
 System.out.println("Request denied as it leads to an unsafe state."); 
 } else { 
 System.out.println("Requested resources exceed available or need."); 
 } 
 scanner.close(); 
 } 
} 
