package heapArrayImpl;

import java.util.Vector;

import queue.Queue;
import task.Task;

/**
 * Max Heap Implementation using Dynamic Array
 * 
 * Heap ADT is an effective way
 * to implement Priority Queues and sorting
 * (in place) using heapsort. The underlying
 * structure resembles to that of Binary tree
 * but avoids its structural ramification by
 * using array indices to store parent-child
 * locations.
 * 
 * Dynamic array is used to tune the performance
 * using Vector in java Collection.
 * 
 * @author Prateek Dash
 */
public class MaxHeapDynArray extends Queue {
	
	/**
	 * Parameterized constructor which instantiates
	 * the task array with the supplied {@code} var-
	 * iable.
	 * 
	 * @param length - Length of the task array.
	 */
	public MaxHeapDynArray(int length) {
		tasks = new Vector<Task>();
		tasks.setSize(length);
		size = 0;
	}

	/**
	 * Invokes the insert method to add task object
	 * in the priority queue based on Max Heap.
	 * 
	 * @param task - Task object to be enqueued in the
	 *               priority queue.
	 */
	@Override
	public void enqueue(Task task) {
		insert(task);
	}
	
	/**
	 * Removes the task from the priority queue with
	 * highest importance using extractMax function.
	 * 
	 *  @return task with highest importance.
	 */
	@Override
	public Task dequeue() {
		return extractMax();
	}

	/**
	 * Retrieves the task object of highest import-
	 * -ance from the priority queue.
	 * 
	 * @return Task object with highest importance
	 */
	@Override
	public Task peek() {
		if(isEmpty()) {
			throw new RuntimeException("The Priority Queue using Heap ADT is full......");
		}
		else {
			return tasks.get(1);
		}
	}
	
	/**
	 * Empties the task array and resets the size member.
	 *  
	 */
	@Override
	public void empty() {
		size = 0;
		tasks = new Vector<Task>(tasks.size());
	}

	/**
	 * Checks the count of task are zero or not.
	 * 
	 * @return true- if queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Checks whether the number of tasks exceed the
	 * pre-assigned size of the priority queue.
	 * 
	 * @return true- if queue is full, false otherwise
	 */
	@Override
	public boolean isFull() {
		return size == tasks.size();
	}
	
	/**
	 * Retrieves the number of tasks present 
	 * in the priority queue.
	 * 
	 * @return Size of the priority queue.
	 */
	@Override
	public int size() {
		return size;
	}	
	
	private Vector<Task> tasks;
	private int size;
	
	/**
	 * Adds the task in the heap with appropriate
	 * position
	 * 
	 * @param task - The task to be inserted
	 */
	private void insert(Task task) {
		if(isFull()) {
			throw new RuntimeException("The Priority Queue using Heap ADT is full......");
		}
		else {
			++size;
			tasks.setElementAt(task,size);
			bubbleUp(size);
		}
	}
		
	/**
	 * Rejigs the task elements within heap to maintain
	 * the dominant(max) element at the root(first) in-
	 * -dex (Max-Heap priniciple).
	 * 
	 * @param task_index - Index of the task to be rejigged.
	 */
	private void bubbleUp(int task_index) {
		if(getIndexOfParent(task_index) == -1) {
			// Presently at root of heap, hence no parent.
			return;
		}
		
		if((tasks.get(task_index)!= null && 
			tasks.get(getIndexOfParent(task_index))!= null) &&
			(tasks.get(task_index).getImportance() >
		    tasks.get(getIndexOfParent(task_index)).getImportance())) {
			swapTask(task_index, getIndexOfParent(task_index));
			bubbleUp(getIndexOfParent(task_index));
		}
	}
	
	/**
	 * Returns the index of the parent of the given task index by taking
	 * "floor" of its division by 2.
	 *  
	 * @param task_index - Index of task whose parent index needs to be determined.
	 * @return Index of parent for the given task.
	 */
	private int getIndexOfParent(int task_index) {
		if(task_index == 1) {
			return -1;
		}
		
		return (task_index/2);
	}
	
	/**
	 * Returns the index of the youngest child for the given node.
	 * 
	 * @param task_index Index of the task object.
	 * @return Index of the task.
	 */
	private int getIndexOfYoungChild(int task_index) {
		return (2 * task_index);
	}
	
	/**
	 * Swaps the two tasks for the given indices.
	 * 
	 * @param firstIndex - Index of the first task to be swapped
	 * @param secondIndex - Index of the second task to be swapped
	 */
	private void swapTask(int firstIndex, int secondIndex) {
		Task tempTask =  tasks.get(firstIndex);
		tasks.set(firstIndex, tasks.get(secondIndex));
		tasks.set(secondIndex, tempTask);
	}
	
	/**
	 * Retrieves and removes the task of highest importance
	 * from the Heaps ADT priority queue.
	 * 
	 * @return Task - Task with highest importance.
	 */
	private Task extractMax() {
		Task maxTask = null;
		
		if(isEmpty()) {
			throw new RuntimeException("The Priority Queue using Heap ADT is empty......");
		}
		else {
			maxTask = tasks.get(1);
			tasks.set(1, tasks.get(size));
			--size;
			bubbleDown(1);
		}
		return maxTask;
	}
	
	/**
	 * Recursively rejigs the task elements until the balance
	 * of the dominating(max heap) element is established.
	 * 
	 * @param task_index - index of task to be rejigged downwards.
	 */
	private void bubbleDown(int task_index) {
		int childIndex;
		int max_index;
		int i;
		
		childIndex = getIndexOfYoungChild(task_index);
		max_index = task_index;
		
		for(i = 0; i <= 1; i++) {
			if ((childIndex + i) <= size) {
				if((tasks.get(max_index)!=null && 
					tasks.get(childIndex + i)!=null) && 
				    (tasks.get(max_index).getImportance() < 
				    tasks.get(childIndex + i).getImportance())) {
				   max_index = childIndex + i;
				}
			}
		}
		
		if(max_index != task_index) {
			swapTask(task_index, max_index);
			bubbleDown(max_index);
		}
	}
	
	public static void main(String[] args) {
		Queue queue1 = new MaxHeapDynArray(6);
		queue1.enqueue(new Task(2));
		queue1.enqueue(new Task(7));
		queue1.enqueue(new Task(1));
		queue1.enqueue(new Task(90));
		queue1.enqueue(new Task(3));
		
		System.out.println(queue1.dequeue().getImportance());
		System.out.println(queue1.dequeue().getImportance());
	}
}