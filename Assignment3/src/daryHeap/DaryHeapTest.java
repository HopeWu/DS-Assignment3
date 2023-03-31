package daryHeap;

import task.Task;

public class DaryHeapTest {
    public static void main(String[] args) {
    	int dataSize = 1000;
    	for (int d = 3; d <=10; d++) {
        	int val = dataSize;
    		DaryHeap daryHeap = new DaryHeap(dataSize, d);
    		for (int i = 1; i < dataSize; i++) {
    			Task task = new Task(i);
    			daryHeap.insert(task);
    		}
    		while (!daryHeap.isEmpty()) {
    			Task task = daryHeap.delete(0);
//    			System.out.println(task.getImportance());
    			assert task.getImportance() == val--;   			
    		}
    		System.out.println("No errors found when d = " + d);
    	}

	}
}
