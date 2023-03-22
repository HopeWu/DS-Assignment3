package linkedList;

public class DoubleLink<T> {
	/**
	 * This is the structure of the doubly linked list
	 */
	private DoubleTaskNode<T> head;
	private DoubleTaskNode<T> tail;
	private int length;

	// this is the constructor
	public DoubleLink() {
		this.head = null;
		this.tail = null;
		length=0;
	}
	
	// get the node with the specific index
	public DoubleTaskNode<T> getNode(int index){
		if(index>this.getLength()|| index<1) {
			System.out.println("This index is out of range");
		    return null;
		}
		else if(index==1) {
		    return head;
		}
		else{
			DoubleTaskNode<T> node = head;
		    for(int i=1; i<index; i++) {
		    	node = node.getNext();
		    }
		    return node;
		}
	}	
	
	// get the head of the linked list
	public DoubleTaskNode<T> getHead() {
		if(tail==null) {
			System.out.println("This linked list is null");
			System.out.println("");
			return null;
		}
		return head;
	}

	// get the tail of the linked list
	public DoubleTaskNode<T> getTail() {
		if(tail==null) {
			System.out.println("This linked list is null");
			System.out.println("");
			return null;
		}
		return tail;
	}
    
	// get the length of the linked list
    public int getLength() {
        return length;
    }
    

	
	// insert node from head
    public void insertFromHead(T data) {
    	DoubleTaskNode<T> node = new DoubleTaskNode<T>(data, head, null);
    	if(data == null) {
        	System.out.println("The insert task is null");
        	length --;
        }
    	else if(length==0) {
        	head = node;
        	tail = node;
        }
        else {
        	node.setNext(head);
            head = node;
            head.getNext().setBefore(node);
        }
        length ++;
    }
    
 
    // insert node from tail
    public void insertFromTail(T data) {
    	if(data == null) {
        	System.out.println("The insert task is null");
        }
    	else if (head == null) {
    		insertFromHead(data);
        } else {
        	DoubleTaskNode<T> node = new DoubleTaskNode<T>(data, null, tail.getBefore());
            tail.setNext(node);
            node.setBefore(tail);
            tail = node;
            length++;
        }
    }
    
 
    // delete the node with the specific index
    public T remove(int index){
    	if (getLength() == 0){
    		System.out.println("This linked list is null");
         	return null;
         }
    	if(index>this.getLength()|| index<1) {
        	System.out.println("This index is out of range");
        	return null;
        }
        else if(index==1) {
        	return removeFromHead();
        }
        else{
        	DoubleTaskNode<T> node = head;
        	for(int i=1; i<index-1; i++) {
        		node = node.getNext();
        	}
        	DoubleTaskNode<T> removedNode = node.getNext();
        	DoubleTaskNode<T> temp = node.getNext();
        	temp = node.getNext();
        	if(temp != tail) {
        		node.setNext(temp.getNext());
        		temp.getNext().setBefore(node);
        		temp.setNext(null);
        		temp.setBefore(null);
        		length--;
        		return removedNode.getData();
        	}
        	else {
        		return removeFromTail();
        	}
        }
    }
    // delete node from head
    public T removeFromHead() {
        if(length==0) {
        	System.out.println("This linked list is null");
        	return null;
        }
        DoubleTaskNode<T> LastHead = head;
        DoubleTaskNode<T> node = head.getNext();
        node.setBefore(null);
        head = node;
        length --;
        return LastHead.getData();
    }

    // delete node from tail
    public T removeFromTail() {
    	if(length==0){
    		System.out.println("This linked list is null");
        	return null;
        }
    	DoubleTaskNode<T> node = null;
    	DoubleTaskNode<T> LastTail = tail;
    	if(head!=tail) {
    		node = tail.getBefore();
    		node.setNext(null);
    	    tail = node;
    	}
    	else {
    		tail = node;
    		head = node;
    	}
        length --;
		return LastTail.getData();
    }

 // print the details of each node in the linked list
    public void detailPrint(){
        if (length == 0) {
        	System.out.println("This linked list is null");
        }
        
        DoubleTaskNode<T> node = head;
        while (node != null) {
            System.out.print("former point：");
            System.out.printf("%-5s",node.getBefore() == null ? "null\t" : node.getBefore().getData()+"   \t");
            System.out.print("current point：");
            System.out.printf("%-6s",node.getData() + "\t");
            System.out.print("next point：");
            System.out.printf("%-5s",node.getNext() == null ? "null \t" : node.getNext().getData()+"\t");
            System.out.println();
            node = node.getNext();
        }
        System.out.println();
    }

    public boolean isFull() {
    	return false;
    }
    
    // Clear the linked list
    public void clear(){
    	this.head = null;
		this.tail = null;
		length=0;
    }

}
