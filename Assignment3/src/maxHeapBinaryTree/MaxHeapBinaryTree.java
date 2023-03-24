package maxHeapBinaryTree;

import queue.Queue;
import task.Task;

public class MaxHeapBinaryTree extends Queue {
	private HeapNode root;
	/**
	 * the very right and lowest node
	 */
	private HeapNode last;

	/**
	 * the depth of the very left node
	 */
	private int depthLeft;
	/**
	 * the depth of the very right node
	 */
	private int depthRight;

	/**
	 * To check if the current layer is full.
	 * 
	 * @return
	 */
	private boolean isLayerFull() {
		return depthLeft == depthRight;
	}

	/**
	 * Enqueue a task and maintain the max heap property Enqueue steps: 1.
	 */
	@Override
	public void enqueue(Task task) {
		HeapNode newNode = new HeapNode(task);
		if (this.root == null) {
			// if the heap was the empty, enqueue the first node as the root and the job is
			// done
			this.root = newNode;
			newNode.parent = this.root;
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
			
		}
	}

	@Override
	public Task dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void empty() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return null == this.root;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
