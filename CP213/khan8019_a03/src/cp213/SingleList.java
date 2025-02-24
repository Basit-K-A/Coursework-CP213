package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> object contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2024-09-01
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The object to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {
	SingleNode<T> current = this.front;
	SingleNode<T> previous = null;

	if(this.contains(key)) {
	    
	    while(current.getObject().compareTo(key) != 0) {
		if (current.getNext() != null) {
		    previous = current;
		    current = current.getNext();
		}
	    }
	}

	return previous;
    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param data The object to append.
     */
    public void append(final T data) {

	SingleNode<T> n = new SingleNode<T>(data, null);
	
	if(this.isEmpty()){
	    this.front = n;
	    this.rear = this.front;
	}
	else {
	    this.rear.setNext(n);
	    this.rear = n;
	}
	this.length++;

	return;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each object formerly present in this SingleList. The first occurrence of
     * each object is preserved.
     */
    public void clean() {
	
	SingleNode<T> curr = this.front;

	while(curr != null) {
	    T key = curr.getObject();
	    int count = this.count(key);
	    
	    SingleNode<T> current = curr.getNext(); // go to next, to skip first occurrence
	    SingleNode<T> prev = curr;
	    
	    while(count > 1) {
		if(curr.getObject().equals(key)) {
		    
		    prev.setNext(current.getNext());
		    
		    current = prev.getNext();
		    current = prev.getNext();
		    
		    this.length++;
		    count--;
		}
		else {
		    prev = current;
		    current = current.getNext();
		}
	    }
	    curr = curr.getNext();
	}

	return;
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	while(!left.isEmpty() && !right.isEmpty()) {
	    this.moveFrontToRear(left);
	    this.moveFrontToRear(right);
	}
	while(!left.isEmpty()) {
	    this.moveFrontToRear(left);
	}
	while(!right.isEmpty()) {
	    this.moveFrontToRear(right);
	}

	return;
    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key object to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {

	SingleNode<T> curr = this.front;
	boolean contains = false;
	
	if(!this.isEmpty()) {
	    int i = 0;
	    
	    while((i < this.length) && (curr.getObject().compareTo(key) != 0)) {
		if(curr.getNext() != null) {
		    curr = curr.getNext();
		}
		i++;
	    }
	    if( i < this.length) {
		contains = true;
	    }
	}

	return contains;
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The object to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {

	int count = 0;
	
	if(this.contains(key)) {
	    SingleNode<T> curr = this.front;
	    
	    while(curr != null) {
		if(curr.getObject().compareTo(key) == 0) {
		    count++;
		}
		curr = curr.getNext();
	    }
	}

	return count;
    }

    /**
     * Finds and returns the object in list that matches key.
     *
     * @param key The object to search for.
     * @return The object that matches key, null otherwise.
     */
    public T find(final T key) {

	T value = null;
	
	if(this.contains(key)) {
	    SingleNode<T> pointer = this.linearSearch(key);
	    value = pointer.getNext().getObject();
	}

	return value;
    }

    /**
     * Get the nth object in this SingleList.
     *
     * @param n The index of the object to return.
     * @return The nth object in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {
	
	T value = null;
	int i = n;
	SingleNode<T> curr = this.front;
	while(i > 0) {
	    i--;
	    
	    if(curr.getNext() != null) {
		curr = curr.getNext();
	    }
	}
	
	value = curr.getObject();

	return value;
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same objects in the same order
     *         as source, false otherwise.
     */
    public boolean equals(final SingleList<T> source) {
	
	boolean identical = true;
	if(this.length == source.length) {
	    SingleNode<T> curr = this.front;
	    SingleNode<T> currS = source.front;
	    
	    while(curr != null) {
		if(curr.getObject().compareTo(currS.getObject()) != 0) {
		    identical = false;
		}
		curr = curr.getNext();
		currS = currS.getNext();
	    }
	}
	else {
	    identical = false;
	}

	return identical;
    }

    /**
     * Finds the first location of a object by key in this SingleList.
     *
     * @param key The object to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {

	int index = 0;
	SingleNode<T> curr = this.front;
	
	while(curr.getObject().compareTo(key) != 0 && curr.getNext() != null) {
	    curr = curr.getNext();
	    
	    index += 1;
	}
	if(index == this.length) {
	    index = -1;
	}

	return index;
    }

    /**
     * Inserts object into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i    The index to insert the new data at.
     * @param data The new object to insert into this SingleList.
     */
    public void insert(int i, final T data) {

	if(i > this.length) {
	    this.append(data);
	}
	else {
	    SingleNode<T> node = new SingleNode<T>(data, null);
	    SingleNode<T> current = this.front;
	    SingleNode<T> prev = this.front;

	    while (i > 0) {

		if (current.getNext() != null) {
		    prev = current;
		    current = current.getNext();
		}

		i -= 1;
	    }

	    prev.setNext(node);
	    node.setNext(current);
	    this.length += 1;
	}

	return;
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then objects from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

	SingleNode<T> left_curr = left.front;
	
	while(left_curr != null) {
	    SingleNode<T> right_curr = right.front;
	    
	    while(right_curr != null) {
		if(left_curr.getObject().equals(right_curr.getObject())) {
		    if(!(this.contains(left_curr.getObject()))) {
			this.append(left_curr.getObject());
		    }
		}
		right_curr = right_curr.getNext();
	    }
	    left_curr = left_curr.getNext();
	}

	return;
    }

    /**
     * Finds the maximum object in this SingleList.
     *
     * @return The maximum object.
     */
    public T max() {

	T max = this.front.getObject();
	SingleNode<T> curr = this.front;
	
	while(curr != null) {
	    if(curr.getObject().compareTo(max) > 0) {
		max = curr.getObject();
	    }
	    curr = curr.getNext();
	}

	return max;
    }

    /**
     * Finds the minimum object in this SingleList.
     *
     * @return The minimum object.
     */
    public T min() {

	T min = this.front.getObject();
	SingleNode<T> curr = this.front;
	
	while(curr != null) {
	    if(curr.getObject().compareTo(min) < 0) {
		min = curr.getObject();
	    }
	    curr = curr.getNext();
	}

	return min;
    }

    /**
     * Inserts object into the front of this SingleList.
     *
     * @param data The object to insert into the front of this SingleList.
     */
    public void prepend(final T data) {
	if (this.isEmpty()) {
	    // if empty, new node is the only node
	    SingleNode<T> node = new SingleNode<T>(data, null);
	    this.front = node;
	    this.rear = this.front;
	    this.length += 1;
	}

	else {
	    // make new node point to front, making it the new front
	    SingleNode<T> node = new SingleNode<T>(data, this.front);
	    this.front = node;
	    this.length += 1;
	}

	return;
    }

    /**
     * Finds, removes, and returns the object in this SingleList that matches key.
     *
     * @param key The object to search for.
     * @return The object matching key, null otherwise.
     */
    public T remove(final T key) {

	T value = null;

	if (this.contains(key)) {
	    SingleNode<T> current = this.front;
	    SingleNode<T> prev = this.front;
	    int n = this.index(key); // get location of key

	    while (n > 0) {
		// traverse until location is found
		if (current.getNext() != null) {
		    prev = current;
		    current = current.getNext();
		}

		n -= 1;
	    }

	    value = current.getObject();
	    // set previous node to point to next node, removing node in between
	    prev.setNext(current.getNext());
	    this.length -= 1;
	}

	return value;
    }

    /**
     * Removes the object at the front of this SingleList.
     *
     * @return The object at the front of this SingleList.
     */
    public T removeFront() {

	T value = null;
	
	if(!this.isEmpty()) {
	    value = this.front.getObject();
	    this.front = this.front.getNext();
	    this.length--;
	}

	return value;
    }

    /**
     * Finds and removes all objects in this SingleList that match key.
     *
     * @param key The object to search for.
     */
    public void removeMany(final T key) {
	int count = this.count(key);
	SingleNode<T> current = this.front;
	SingleNode<T> prev = null;

	while (count > 0) {
	    if (current.getObject().compareTo(key) == 0) {
		// key is located

		if (prev == null) {
		    // if key is at the front, replace it with new front
		    this.front = this.front.getNext();
		}

		else {
		    // make previous node point to next node, skipping node in between
		    prev.setNext(current.getNext());
		}

		count -= 1;
		this.length -= 1;
	    }

	    else if (current.getNext() != null) {
		// proceed to next node, if none were removed
		prev = current;
		current = current.getNext();
	    }

	}
	return;
    }

    /**
     * Reverses the order of the objects in this SingleList.
     */
    public void reverse() {
	if (!this.isEmpty()) {

	    int i = this.length - 1;

	    while (i > 0) {
		if (i == this.length) {
		    this.insert(i + 1, this.removeFront());
		}

		else {
		    this.insert(i, this.removeFront());
		}

		i -= 1;
	    }
	}

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move object or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * object than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {
	int size = (int) Math.floor(length / 2);
	int i = this.length;

	while (i > size) {
	    left.moveFrontToRear(this);
	    i -= 1;
	}

	while (i > 0) {
	    right.moveFrontToRear(this);
	    i -= 1;
	}

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move object or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {
	while (this.length > 1) {
	    left.moveFrontToRear(this);
	    right.moveFrontToRear(this);

	}

	if (this.length == 1) {
	    left.moveFrontToRear(this);
	}

	return;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies object
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then objects from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	SingleNode<T> left_node = left.front;
	SingleNode<T> right_node = right.front;

	while (left_node != null) {
	    this.append(left_node.getObject());
	    left_node = left_node.getNext();
	}

	while (right_node != null) {
	    this.append(right_node.getObject());
	    right_node = right_node.getNext();
	}

	return;
    }
}
