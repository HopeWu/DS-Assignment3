package pairingHeap;

import task.Task;

/**
 * Represents a pairing heap data structure for storing and managing tasks 
 * based on their importance.
 */
public class PairingHeap {
	 PairingHeapNode root;

	 /**
	 * Constructs an empty PairingHeap.
	 */
	 public PairingHeap() {
	    root = null;
	 }

	 /**
	 * Checks if the heap is empty.
	 * @return true if the heap is empty, false otherwise
	 */
	 public boolean isEmpty() {
	    return root == null;
	 }

	 /**
	 * Inserts a task into the heap.
	 * @param task the TaskNode to be inserted into the heap
	 */
	 public void insert(Task task) {
		 PairingHeapNode newNode = new PairingHeapNode(task);
	     if (root == null) {
	    	 root = newNode;
	     } 
	     else {
	         root = merge(root, newNode);
	     }
	 }
	 
	 /**
	 * Finds the task with the maximum importance in the heap.
	 * @return the task with the maximum importance
	 * @throws IllegalStateException if the heap is empty
	 */
	 public Task findMax() {
		 if (isEmpty()) {
			 throw new IllegalStateException("Heap is empty.");
	     }
	     return root.getTask();
	 }

	 /**
	 * Removes and returns the task with the maximum importance from the heap.
	 * @return the task with the maximum importance
	 * @throws IllegalStateException if the heap is empty
	 */	 
	 public Task deleteMax() {
		 if (isEmpty()) {
			 throw new IllegalStateException("Heap is empty.");
	     }
	     Task minTask = root.getTask();
	     if (root.getChild() == null) {
	         root = null;
	     } 
	     else {
	    	 root = mergePairs(root.getChild());
	         root.setPrev(null);
	     }
	     return minTask;
	 }
	 
	/**
	* Merges two PairingHeapNodes based on their tasks' importance, maintaining the maximum importance at the root.
	* If tasks have the same importance, their order is determined by comparing their reference values.
	* @param first the first PairingHeapNode to merge
	* @param second the second PairingHeapNode to merge
	* @return the merged PairingHeapNode with the maximum importance task at the root
	*/
	private PairingHeapNode merge(PairingHeapNode first, PairingHeapNode second) {
		if (first == null) return second;
	    if (second == null) return first;

	    int importanceComparison = Integer.compare(first.getTask().getImportance(), second.getTask().getImportance());
	    
	    if (importanceComparison > 0|| (importanceComparison == 0 && System.identityHashCode(first) < System.identityHashCode(second))) { // Changed from '<' to '>'
	        second.setPrev(first);
	        first.setNext(second.getNext());
	        if (first.getNext() != null) {
	            first.getNext().setPrev(first);
	        }
	        second.setNext(first.getChild());
	        if (second.getNext() != null) {
	            second.getNext().setPrev(second);
	        }
	        first.setChild(second);
	        return first;
	    } else {
	        first.setPrev(second);
	        second.setNext(first.getNext());
	        if (second.getNext() != null) {
	            second.getNext().setPrev(second); 
	        }
	        first.setNext(second.getChild());
	        if (first.getNext() != null) {
	            first.getNext().setPrev(first);
	        }
	        second.setChild(first);
	        return second;
	    }
	}
	
	/**
     * Merges pairs of subtrees in the heap after the deletion of the root node.
     * @param first the first PairingHeapNode in the chain of siblings to be merged
     * @return the resulting PairingHeapNode after merging all pairs of siblings
     */
	private PairingHeapNode mergePairs(PairingHeapNode first) {
        if (first == null || first.getNext() == null) {
            return first;
        }

        PairingHeapNode second = first.getNext();
        PairingHeapNode remaining = second.getNext();
        first.setNext(null);
        second.setNext(null);

        return merge(merge(first, second), mergePairs(remaining));
    }
}
