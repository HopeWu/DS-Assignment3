package maxHeapBinaryTree;

import java.util.ArrayList;

import queue.Queue;
import standardQueue.StandardQueueByLinkedListOptim;
import task.Task;
import test.EfficiencyTest;

public class MaxHeapBinaryTree extends Queue {
	public MaxHeapBinaryTree() {
		this.root = null;
		this.last = null;
	}
	private HeapNode root;
	/**
	 * the very right and lowest node
	 */
	private HeapNode last;

	/**
	 * to save all the nodes as a list. A linked list would suffice, preferably a
	 * doubly-linked one. For convenience, ArrayList is used here.
	 */
	ArrayList<HeapNode> nodeLink = new ArrayList<HeapNode>();

	/**
	 * To check if the current layer is full.
	 * 
	 * @return
	 */
	private boolean isLayerFull() {
		int depthLeft = 0;
		int depthRight = 0;
		HeapNode node = root;
		while (node != null) {
			depthLeft += 1;
			node = node.leftChild;
		}
		node = root;
		while (node != null) {
			depthRight += 1;
			node = node.rightChild;
		}
		return depthLeft == depthRight;
	}

	/**
	 * Enqueue a task and maintain the max heap property Enqueue steps: 1.
	 */
	@Override
	public void enqueue(Task task) {
		HeapNode newNode = new HeapNode(task);

		nodeLink.add(newNode);

		/**
		 * find the right spot for the insert, which is the right most and bottom most
		 * one. To find the right spot, the parent to be added child has to be found
		 * first. The current version of doing this assumes no help from nodeList. With
		 * the help of nodeList, the right parent will be
		 * nodeList.get((nodeList.size()-1)/2). 
		 */
		if (this.root == null) {
			// if the heap was the empty, enqueue the first node as the root and the job is
			// done
			this.root = newNode;
			newNode.parent = this.root;
			last = newNode;
			return;
		} else {
			// find the next spot in the tree to put this node
			if (last.isLeftChild()) {
				// put the new node into the right of its parent

				last.parent.rightChild = newNode;
				newNode.parent = last.parent;
			}
			// if the layer is full, insert the node into the very left node of a new layer
			else if (isLayerFull()) {
				HeapNode leftest = this.root;
				while (leftest.leftChild != null) {
					leftest = leftest.leftChild;
				}

				leftest.leftChild = newNode;
				newNode.parent = leftest;
			}
			/**
			 * need to find the next inserting spot on the current layer
			 */
			else {
				// get the nearest ancestor that is a left child
				HeapNode nearestLeftAncestor = last;
				while (nearestLeftAncestor.isRightChild())
					nearestLeftAncestor = nearestLeftAncestor.parent;
				// find the right sibling and insert into the leftest spot of the right sibling
				HeapNode theRightSibling = nearestLeftAncestor.parent.rightChild;
				while (theRightSibling.leftChild != null)
					theRightSibling = theRightSibling.leftChild;

				theRightSibling.leftChild = newNode;
				newNode.parent = theRightSibling;
			}
			// updating the last node
			last = newNode;

			/**
			 * maintain the max heap structure by comparing the new node and its ancestors
			 */
			HeapNode current = last;
			Task tmp;
			while (current.parent.data.getImportance() < current.data.getImportance()) {
				/**
				 * exchange happens here
				 */
				tmp = current.parent.data;
				current.parent.data = current.data;
				current.data = tmp;

				current = current.parent;
			}
		}
	}

	@Override
	public Task dequeue() {
		// save the to-be-returned task
		Task maxTask = root.data;
		// pop the last from the nodeList
		HeapNode last = nodeLink.remove(nodeLink.size() - 1);
		// disown the last from its parent
		if (last.isLeftChild())
			last.parent.leftChild = null;
		else
			last.parent.rightChild = null;
		// put the last's data into the root's position
		root.data = last.data;

		/**
		 * maintain the heap from the root
		 */
		HeapNode biggerChild = null, currentNode = root;
		// get the bigger child
		biggerChild = getBiggerChild(currentNode);

		Task tmp = null;
		while (biggerChild != null && currentNode.data.getImportance() < biggerChild.data.getImportance()) {
			// exchange the tasks between bigger child and currentNode
			tmp = biggerChild.data;
			biggerChild.data = currentNode.data;
			currentNode.data = tmp;

			// move on to the next
			currentNode = biggerChild;
			biggerChild = getBiggerChild(currentNode);
		}
		return maxTask;
	}

	/**
	 * get the bigger child of the current parent
	 * 
	 * @param parent
	 * @return
	 */
	private HeapNode getBiggerChild(HeapNode parent) {
		if (parent == null)
			return null;
		HeapNode biggerChild = null;
		if (parent.leftChild != null)
			biggerChild = parent.leftChild;
		if (biggerChild == null)
			biggerChild = parent.rightChild;
		else if (parent.rightChild != null && biggerChild.data.getImportance() < parent.rightChild.data.getImportance())
			biggerChild = parent.rightChild;
		return biggerChild;
	}

	/**
	 * peek the root
	 */
	@Override
	public Task peek() {
		if (root == null)
			return null;
		return root.data;
	}

	/**
	 * empty the whole queue
	 */
	@Override
	public void empty() {
		this.root = null;
	}

	/**
	 * check if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return null == this.root;
	}

	/**
	 * never full
	 */
	@Override
	public boolean isFull() {
		return false;
	}

	/**
	 * return the size of the queue
	 */
	@Override
	public int size() {
		return nodeLink.size();
	}

	public static void main(String[] args) {

		EfficiencyTest efficiencyTest = new EfficiencyTest();

		Queue queue1 = new StandardQueueByLinkedListOptim();
		Queue queue2 = new MaxHeapBinaryTree();

		efficiencyTest.setQueue1(queue1);
		efficiencyTest.setQueue2(queue2);
		final int S = 1;
		efficiencyTest.setDatasize(S * 1000);
		efficiencyTest.setBatchSize(S * 100);
		efficiencyTest.setDatasetProbability(100, 0.1);
		efficiencyTest.setDatasetProbability(1, 0.9);
		efficiencyTest.run();
	}
}
