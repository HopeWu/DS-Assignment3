package pairingHeap;

import daryHeap.DaryHeap;
import priorityQueue.PriorityQueueByArr;
import priorityQueue.PriorityQueueByLinkedListOptim;
import queue.Queue;
import task.Task;
import test.Cpu;
import test.Dataset;


public class TestPairingHeap {
	 public static void main(String[] args) {
		// configure the distribution for the data
			Dataset dataset = new Dataset();
			Task[] tasks = dataset.getData(100);
			PairingHeap heap = new PairingHeap();
			for (Task task: tasks) {
				heap.insert(task);
			}
			

	        System.out.println("Max importance task: " + heap.findMax().getImportance()); // Should output "Task 10"
	        System.out.println("Size: " + heap.size()); 

	        Task deletedTask = heap.deleteMax();
	        System.out.println("Deleted max importance task: " + deletedTask.getImportance()); // Should output "Task 10"
	        System.out.println("Size: " + heap.size()); 

	        System.out.println("New max importance task: " + heap.findMax().getImportance()); // Should output "Task 10"

	        deletedTask = heap.deleteMax();
	        System.out.println("Deleted max importance task: " + deletedTask.getImportance()); // Should output "Task 10"
	        System.out.println("Size: " + heap.size()); 
	 }
	
}
