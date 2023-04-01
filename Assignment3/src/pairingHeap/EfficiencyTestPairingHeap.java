package pairingHeap;

import daryHeap.DaryHeap;
import halfPrioQueue.HalfPrioQueueByArr;
import maxHeapBinaryTree.MaxHeapBinaryTree;
import priorityQueue.PriorityQueueByArr;
import priorityQueue.PriorityQueueByLinkedListOptim;
import queue.Queue;
import standardQueue.StandardQueueByLinkedListOptim;
import test.EfficiencyTest;

public class EfficiencyTestPairingHeap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=50000;i<=500000;i=i+50000) {
			experiment(i,0.3);
		}
	}
	
	static void experiment(int batch_size, double rate) {
		EfficiencyTest efficiencyTest2 = new EfficiencyTest();
		
		efficiencyTest2.setQueue1(new PairingHeap());
		efficiencyTest2.setQueue2(new DaryHeap(1000000, 4));
		
		efficiencyTest2.setDatasize(batch_size);
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
		
		efficiencyTest2.run2();
	}

	
}
