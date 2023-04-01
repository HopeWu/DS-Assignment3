package test;

import halfPrioQueue.HalfPrioQueueByArr;
import halfPrioQueue.HalfPrioQueueByLinkedList;
import priorityQueue.PriorityQueueByArr;
import halfPrioQueue.HalfPrioQueueByLinkedListImp1;
import priorityQueue.PriorityQueueBySinglyLinkedList;
import priorityQueue.PriorityQueueByLinkedListOptim;
import queue.Queue;
import standardQueue.StandardQueueByLinkedListOptim;
import heapArrayImpl.MaxHeapArray;
import heapArrayImpl.MaxHeapDynArray;
import maxHeapBinaryTree.MaxHeapBinaryTree;
import priorityQueue.PriorityQueueByLinkedListOptim;

public class TestDrive {
	 public static void main(String[] string) {
//		experimentOne();
		//testPerformance();
		//experiement_MaxHeapArr();
		experiment_MaxHeap_ArrVsTree();
		//experiment_MaxHeap_DynArrvsTree();
		 //experiement_MaxHeapArr();
	}
	/**
	Experiment functions. Its contents will be changed across different experiments. e.g. threshold, probability, datasize.
	*/
	static void experimentOne() {
		
		EfficiencyTest efficiencyTest = new EfficiencyTest();
		
		Queue queue1 = new StandardQueueByLinkedListOptim();
		HalfPrioQueueByLinkedListImp1 queue2 = new HalfPrioQueueByLinkedListImp1();
		queue2.setThreshold(50);
		
		efficiencyTest.setQueue1(queue1);
		efficiencyTest.setQueue2(queue2);
		final int S = 1;
		efficiencyTest.setDatasize(S*1000);
		efficiencyTest.setBatchSize(S*100);
		efficiencyTest.setDatasetProbability(100, 0.1);
		efficiencyTest.setDatasetProbability(1, 0.9);
		efficiencyTest.run();
	}
	
	/**
	Experiments wrappers.
	*
	static void testPerformance() {
		PerformanceTest performanceTest = new PerformanceTest();
		performanceTest.run();
	}
	
	/**
	Experiments wrappers.
	*/
	static void experimentTwo_LinkedList(int batch_size, double rate) {
		EfficiencyTest efficiencyTest2 = new EfficiencyTest();
		
		efficiencyTest2.setQueue1(new HalfPrioQueueByLinkedList());
		efficiencyTest2.setQueue2(new PriorityQueueBySinglyLinkedList());
		efficiencyTest2.setDatasize(5000);
		efficiencyTest2.setBatchSize(batch_size);
		if(rate == 0.1) {
			efficiencyTest2.setDatasetProbability(100, rate);
			efficiencyTest2.setDatasetProbability(1, 1.0);
		}
		else if(rate==0.9){
			efficiencyTest2.setDatasetProbability(100, 1.0);
			efficiencyTest2.setDatasetProbability(1, 1-rate);
		}
		else {
			efficiencyTest2.setDatasetProbability(100, rate);
			efficiencyTest2.setDatasetProbability(1, 1-rate);
		}
		efficiencyTest2.run();
	}
	
	/**
	Experiments wrappers.
	*/
	static void experimentTwo_Array(int batch_size, double rate) {
		EfficiencyTest efficiencyTest2 = new EfficiencyTest();
		
		efficiencyTest2.setQueue1(new HalfPrioQueueByArr(5000));
		efficiencyTest2.setQueue2(new PriorityQueueByArr(5000));
		efficiencyTest2.setDatasize(5000);
		efficiencyTest2.setBatchSize(batch_size);
		if(rate == 0.1) {
			efficiencyTest2.setDatasetProbability(100, rate);
			efficiencyTest2.setDatasetProbability(1, 1.0);
		}
		else if(rate==0.9){
			efficiencyTest2.setDatasetProbability(100, 1.0);
			efficiencyTest2.setDatasetProbability(1, 1-rate);
		}
		else {
			efficiencyTest2.setDatasetProbability(100, rate);
			efficiencyTest2.setDatasetProbability(1, 1-rate);
		}
		efficiencyTest2.run();
	}

	/**
	Experiments wrappers.
	*/
	static void experimentTwo_OptimizedLinkedList(int batch_size, double rate) {
		EfficiencyTest efficiencyTest2 = new EfficiencyTest();
		
		efficiencyTest2.setQueue1(new HalfPrioQueueByLinkedList());
		efficiencyTest2.setQueue2(new PriorityQueueByLinkedListOptim());
		efficiencyTest2.setDatasize(5000);
		efficiencyTest2.setBatchSize(batch_size);
		if(rate == 0.1) {
			efficiencyTest2.setDatasetProbability(100, rate);
			efficiencyTest2.setDatasetProbability(1, 1.0);
		}
		else if(rate==0.9){
			efficiencyTest2.setDatasetProbability(100, 1.0);
			efficiencyTest2.setDatasetProbability(1, 1-rate);
		}
		else {
			efficiencyTest2.setDatasetProbability(100, rate);
			efficiencyTest2.setDatasetProbability(1, 1-rate);
		}
		efficiencyTest2.run();
	}
	
	/**
	 * Experiment that measures the enqueue operation
	 * on various datasizes for max heap using array.
	 */
	static void experiement_MaxHeapArr() {
     EfficiencyTest efficiencyTest = new EfficiencyTest();
		
		Queue queue1 = new MaxHeapArray(10000001);
		efficiencyTest.setQueue1(queue1);
		efficiencyTest.setDatasize(10000000);
		efficiencyTest.setBatchSize(1000000);
		efficiencyTest.setDatasetProbability(100, 0.1);
		efficiencyTest.setDatasetProbability(1, 0.9);
		efficiencyTest.speedTest();
	}
	
	/**
	 * Experiment that compare efficiency of
	 * Max Heap Array versus Max Heap Tree.
	 */
	static void experiment_MaxHeap_ArrVsTree() {
		EfficiencyTest efficiencyTest = new EfficiencyTest();
		
		Queue queue1 = new MaxHeapArray(10000001);
		Queue queue2 = new MaxHeapBinaryTree();
		
		efficiencyTest.setQueue1(queue1);
		efficiencyTest.setQueue2(queue2);
		efficiencyTest.setDatasize(10000000);
		efficiencyTest.setBatchSize(1000000);
		efficiencyTest.setDatasetProbability(100, 0.1);
		efficiencyTest.setDatasetProbability(1, 0.9);
		efficiencyTest.run();
	}
	
	/**
	 * Experiment that compare efficiency 
	 * of tuned priority queue using lin- 
	 * -ked list from assignment 1 versu-
	 * s Max Heap Array.
	 */
	static void experiment_MaxHeap_ArrVsPriQueue_Optim() {
        EfficiencyTest efficiencyTest = new EfficiencyTest();
		
		Queue queue1 = new MaxHeapArray(5000001);
		Queue queue2 = new PriorityQueueByLinkedListOptim();
		
		efficiencyTest.setQueue1(queue1);
		efficiencyTest.setQueue2(queue2);
		efficiencyTest.setDatasize(1000000);
		efficiencyTest.setBatchSize(100000);
		efficiencyTest.setDatasetProbability(100, 0.6);
		efficiencyTest.setDatasetProbability(1, 0.4);
		efficiencyTest.run();
	}
	
	/**
	 * Experiment that demonstrates effect of tuning 
	 * on Max Heap Array using dynamic array compared
	 * to Max Heap using Binary Tree.
	 */
	static void experiment_MaxHeap_DynArrvsTree() {
		EfficiencyTest efficiencyTest = new EfficiencyTest();
		
		Queue queue1 = new MaxHeapDynArray(500001);
		Queue queue2 = new MaxHeapBinaryTree();
		
		efficiencyTest.setQueue1(queue1);
		efficiencyTest.setQueue2(queue2);
		efficiencyTest.setDatasize(500000);
		efficiencyTest.setBatchSize(50000);
		efficiencyTest.setDatasetProbability(100, 0.1);
		efficiencyTest.setDatasetProbability(1, 0.9);
		efficiencyTest.run();
	}
}