package test;

import java.util.ArrayList; 
import java.util.Hashtable;

import daryHeap.DaryHeap;
import halfPrioQueue.HalfPrioQueueByArr;
import halfPrioQueue.HalfPrioQueueByLinkedList;
import priorityQueue.PriorityQueueByArr;
import halfPrioQueue.HalfPrioQueueByLinkedListImp1;
import priorityQueue.PriorityQueueBySinglyLinkedList;

import priorityQueue.PriorityQueueByLinkedListOptim;
import priorityQueue.PriorityQueueByDoublyLinkedList;

import queue.Queue;
import standardQueue.StandardQueueByArr;
import standardQueue.StandardQueueByLinkedList;
import standardQueue.StandardQueueByLinkedListOptim;
import task.Task;

public class TestDrive {
	static public void main(String[] string) {
//		experimentOne();
		testDaryHeap();
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
	 * Tests the performance of D-ary heap.
	 * Runs each experiment 5 times and work out an average time for each data size. 
	 */
	private static void testDaryHeap() {
		
		// Create test data
		Dataset dataset = new Dataset();

		// Create a data set with the same probabilities of tasks with importance ranging from 1 to 10		
		for (int i = 1; i <= 10; i++) {
			dataset.setProbability(i, 0.1);
		}
		System.out.println("****************D-ary Heap****************");
		final int ITERATIONS = 5;
		int[] dArr = {2, 3, 4, 6, 8, 10, 16, 32, 64, 128};
		System.out.println("Data Size,D,Execution Time(ms)");
		for (int N = 50000; N <= 500000; N += 50000) {
			for (int D : dArr) {
				// Create the queue to be used in CPUs first.
				Queue daryHeap = new DaryHeap(N, D);
				
				// Integrate the queue with the correspondent CPU
				Cpu daryHeapCpu = new Cpu(daryHeap);
				
				// Generate the tasks
				Task[] tasks = dataset.getData(N);
				
				long elapsedTimeSum = 0;
				for (int it = 0; it < ITERATIONS; it++) {
					long start = System.currentTimeMillis();
					daryHeapCpu.perform2(tasks);
					long end = System.currentTimeMillis();
					elapsedTimeSum += end - start;
				}		
				long elapsedTime = elapsedTimeSum / ITERATIONS;	
				System.out.println(N + "," + D + "," + elapsedTime);
			}
			System.out.println("-----------------------------------");			
		}
	}
}
