package daryHeap;
import task.Task;


/**
 * This is a D-ary heap class, a generalization of the binary heap(D=2) in which each node has up to D children 
 * (D is an integer greater than or equal to 2).  
 * Just like binary heap, D-ary heaps can also be divided into two categories: Max D-ary heap, Min D-ary heap. 
 * The D-ary Heap in this project is a Max D-ary heap, in which the key at root is greater than all descendants 
 * and it is the case recursively for all nodes. 

 * @author Yan
 *
 */
public class DaryHeap {
	/**
	 * The array which represents the heap
	 */
    private Task[] heapArray; 
    /**
     * The maximum number of children
     */
    private int d;
    /**
     * The number of elements in the heap
     */
    private int size; 
    
    /**
     * Constructs an empty heap with the specified capacity
     * @param capacity The capacity of the heap when initialized
     * @param d The number of children each node has
     */
    public DaryHeap(int capacity, int d) {
        heapArray = new Task[capacity];
        this.d = d;
        size = 0;
    }
    
    /**
     * Checks if the heap is full.
     * @return boolean, returns true if the heap is full, false otherwise.
     */
    public boolean isFull() {
        return size == heapArray.length;
    }
    
    /**
     * Checks if the heap is empty.
     * @return boolean, returns true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
	/**
	 * Clears the heap.
	 */
    public void clear(){
    	size = 0;
    }
    
    /**
     * Gets the index of its parent if given a node with index i.
     * @param i the index of the given node
     * @return the index of the node's parent
     */
    public int parent(int i) {
        return (i - 1) / d;
    }
    
    /**
     * Gets the index of the kth child of a given node with index i.
     * @param i the index of the given node
     * @param k the order of the child
     * @return the index of the node's kth child
     */
    public int kthChild(int i, int k) {
        return d * i + k;
    }
    
    /**
     * Inserts a new node into the heap.
     * @param task the task to be inserted.
     */
    public void insert(Task task) {
        if (isFull()) {
            throw new RuntimeException("The heap is full.");
        }
        
        heapArray[size] = task;
        size++;
        heapifyUp(size - 1);
    }
    
    /**
     * Deletes a node with index i from the heap.
     * @param i the index of the node to be deleted
     * @return the node which has been deleted
     */
    public Task delete(int i) {
        if (isEmpty()) {
            throw new RuntimeException("The heap is empty.");
        }
        
        Task task = heapArray[i];
        heapArray[i] = heapArray[size - 1]; // fill the current position with the last element of the heap array
        size--;
        heapifyDown(i); // restore the heap to maintain heap property
        
        return task;
    }
    
    /**
     * Restores a given node up in the heap. This is used in insertion operations.
     * If the value of the current node is greater than that of its parent, then swaps the node and the parent.
     * @param i
     */
    private void heapifyUp(int i) {
        Task insertTask = heapArray[i];
        while (i > 0 && insertTask.getImportance() > heapArray[parent(i)].getImportance()) {
            heapArray[i] = heapArray[parent(i)];
            i = parent(i);
        }
        heapArray[i] = insertTask;
    }
    
    /**
     * Restores the heap downwards from a given index. This is used in deletion operations.
     * Runs a loop to find the maximum of all children, swaps if the max value is greater than the node's own value.
     * @param i the index of the given node
     */
    private void heapifyDown(int i) {
        int maxChild;
        Task temp = heapArray[i];
        
        while (kthChild(i, 1) < size) {
            maxChild = maxChild(i);
            if (temp.getImportance() >= heapArray[maxChild].getImportance()) {
                break;
            }
            heapArray[i] = heapArray[maxChild];
            i = maxChild;
        }
        heapArray[i] = temp;
    }
   
    /**
     * Finds the index of a given node's child which has the largest importance 
     * @param i the index of the given node
     * @return the index of the child having the largest importance
     */
    private int maxChild(int i) {
        int leftChild = kthChild(i, 1);
        int maxChild = leftChild;
        int k = 2;
        
        while (k <= d && kthChild(i, k) < size) {
            if (heapArray[kthChild(i, k)].getImportance() > heapArray[maxChild].getImportance()) {
                maxChild = kthChild(i, k);
            }
            k++;
        }
        
        return maxChild;
    }
}
