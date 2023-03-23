package pairingHeap;

import task.Task;

/**
 * Represents a node in a pairing heap containing a task and 
 * references to child, next, and previous nodes.
 */
public class PairingHeapNode {
	private Task task;
	private PairingHeapNode child;
	private PairingHeapNode next;
	private PairingHeapNode prev;

	
	 /**
     * Constructs a PairingHeapNode with a specified task.
     * @param task the task to be stored in the node
     */
    public PairingHeapNode(Task task) {
        this.setTask(task);
        this.setChild(null);
        this.setNext(null);
        this.setPrev(null);
    }

	public PairingHeapNode getPrev() {
		return prev;
	}

	public void setPrev(PairingHeapNode prev) {
		this.prev = prev;
	}

	public PairingHeapNode getNext() {
		return next;
	}

	public void setNext(PairingHeapNode next) {
		this.next = next;
	}

	public PairingHeapNode getChild() {
		return child;
	}

	public void setChild(PairingHeapNode child) {
		this.child = child;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	
}
