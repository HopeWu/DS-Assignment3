package maxHeapBinaryTree;

import task.Task;

public class HeapNode {
	public Task data;
	public HeapNode leftChild;
	public HeapNode rightChild;
	public HeapNode parent;

	public HeapNode(Task task) {
		this.data = task;
	}

	/**
	 * Check if this node is a left child
	 * if this is the root, raise an error
	 * @return
	 */
	public boolean isLeftChild() {
		return this.parent.leftChild == this;
	}

	/**
	 * Check if this node is a right child
	 * if this is the root, raise an error
	 * @return
	 */
	public boolean isRightChild() {
		return this.parent.rightChild == this;
	}
}
