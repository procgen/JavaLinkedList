
public class DLinkedList {
	
	private Node first;
	private Node last;
	
	public DLinkedList()
	{
		this.first = null;
		this.last = null;
	}
	
	/**
	 * Adds a new element to the first position in the linked list
	 * @param data An object containing the data of the element to store
	 */
	public void addFirst(Object data)
	{
		
		if(this.first != null)
		{
			Node newNode = new Node(data, this.first, null);
			this.first.previous = newNode;
			this.first = newNode;
		}
		else
		{
			Node newNode = new Node(data, null, null);
			this.last = newNode;
			this.first = newNode;
		}
	}
	
	/**
	 * Adds a new element to the last position in the linked list
	 * @param data An object containing the data of the element to store
	 */
	public void addLast(Object data)
	{
		if(this.last != null)
		{
			Node newNode = new Node(data, null, this.last);
			this.last.next = newNode;
			this.last = newNode;
		}
		else
		{
			Node newNode = new Node(data, null, null);
			this.last = newNode;
			this.first = newNode;
		}
	}
	
	/**
	 * Removes the first element in the linked list and returns its value
	 * @return data The object that was stored in the linked list
	 */
	public Object removeFirst()
	{
		if(this.first == this.last)
			return removeSingle();
		else
		{
			Object removed = this.first.data;
			this.first = this.first.next;
			this.first.previous = null;
			return removed;
		}
	}
	
	/**
	 * Removes the last element in the linked list and returns its value
	 * @return data The object that was stored in the linked list
	 */
	public Object removeLast()
	{
		if(this.first == this.last)
			return removeSingle();
		else
		{
			Object removed = this.last.data;
			this.last = this.last.previous;
			this.last.next = null;
			return removed;
		}
	}
	
	//Private method to remove an element if it is the last element in the linked list
	private Object removeSingle()
	{
		Object removed = this.first.data;
		this.first = null;
		this.last = null;
		return removed;
	}
	
	/**
	 * Find the value of the first element in the linked list and return it
	 * @return Object value of first element in list
	 */
	public Object getFirst()
	{
		return this.first.data;
	}
	
	/**
	 * Find the value of the last element in the linked list and return it
	 * @return Object value of last element in list
	 */
	public Object getLast()
	{
		return this.last.data;
	}
	
	/**
	 * Returns a LinkedListIterator object to be used to iterate through the linked list
	 * @return LinkedListIterator object
	 */
	public LinkedListIterator listIterator()
	{
		return new LinkedListIterator(first);
	}
	
	/**
	 * Returns a string containing all the elements in the linked list
	 * @return String 
	 */
	public String toString()
	{
		String str = "";
		Node curNode = this.first;
		str += "[" + curNode.data;
		while(true)
		{
			if (curNode.next == null)
			{
				break;
			}
			else
			{
				curNode = curNode.next;
				str += ", " + curNode.data;
			}
		}
		str += "]\n";
		return str;
	}
	
	private class Node
	{
		private Node next;
		private Node previous;
		private Object data;
		
		public Node(Object data, Node next, Node previous)
		{
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
		
		public String toString()
		{
			String str = "";
			str += "Data: " + this.data + "\n";
			if(this.next != null)
			{
				str += "Next: " + this.next.data + "\n";
			}
			if(this.previous != null)
			{
				str += "Prev: " + this.previous.data + "\n";
			}
			return str;
		}
	}
	
	private class LinkedListIterator implements ListIterator
	{
		
		private Node current;
		private Node previous;
		private boolean nextCalled;
		private boolean prevCalled;
		
		public LinkedListIterator(Node current)
		{
			this.current = current;
			this.previous = null;
			nextCalled = false;
			prevCalled = false;
		}

		@Override
		/**
		 * Returns the next object in the linked list, and advances the iterator
		 * @return Object next
		 */
		public Object next() {
			if (!hasNext()) throw new IllegalStateException();
			Object data = current.data;
			previous = current;
			current = current.next;
			nextCalled = true;
			prevCalled = false;
			return data;
		}

		@Override
		/**
		 * Returns the previous object in the linked list, and moves the iterator back
		 * @return Object previous
		 */
		public Object previous() {
			if (!hasPrevious()) throw new IllegalStateException();
			Object data = previous.data;
			current = previous;
			previous = current.previous;
			prevCalled = true;
			nextCalled = false;
			return data;
		}

		@Override
		/**
		 * Returns true if a call to next() will return a value, false otherwise
		 * @return Boolean
		 */
		public boolean hasNext() {
			return current != null;
		}

		@Override
		/**
		 * Returns true if a call to previous() will return a value, false otherwise
		 * @return Boolean
		 */
		public boolean hasPrevious() {
			return previous != null;
		}

		@Override
		public void add(Object element) {
			Node newElement = new Node(element, current, previous);
			if(current != null)
				current.previous = newElement;
			else
				last = newElement;
			if(previous != null)
				previous.next = newElement;
			else
				first = newElement;
			current = newElement;
		}

		@Override
		/**
		 * If a recent call to next() or previous() returned a value, remove that value from the list
		 * and return it. Otherwise throws IllegalStateException
		 * @throws IllegalStateException if remove() is called twice in a row
		 * @return Object removed from linked list
		 */
		public Object remove() {
			if(!nextCalled && !prevCalled)
				throw new IllegalStateException();
			if(nextCalled)
			{
				nextCalled = false;
				Object removed = previous.data;
				removeNext();
				return removed;
			}
			if(prevCalled)
			{
				prevCalled = false;
				Object removed = current.data;
				removePrevious();
				return removed;
			}
			return null;
		}
		
		//Removes the element last returned by next()
		private void removeNext()
		{
			if(previous.previous != null)
				previous.previous.next = current; //The node before the previous is now linked to the current
			else
				first = current; //Else we know we are removing the first element, adjust linked list accordingly
			if(current != null)
			{
				current.previous = previous.previous; //The current node is now linked to the node before the previous
				previous = current.previous; //Now the previous iterator node is the same as the previous of the current node
			}
			else
			{
				previous = previous.previous; //If current is null we avoid resolving its pointer
				last = previous; //We also know we must have removed the last element, so adjust linked list accordingly
			}
		}
		
		//Removes the element last returned by previous()
		private void removePrevious()
		{
			if(current.next != null)
				current.next.previous = previous;
			else //otherwise we were at the end of the list
				last = current.previous;
			if(previous != null)
				previous.next = current.next;
			else //otherwise we know we are at the beginning of the linked list
				first = current.next; //adjust linked list accordingly
			current = current.next;
		}

		@Override
		/**
		 * If a recent call to next() or previous() returned a value, set that value to element. Otherwise throws 
		 * IllegalStateException
		 * @throws IllegalStateException if next() or previous() was not called last
		 */
		public void set(Object element) {
			if(!nextCalled && !prevCalled)
				throw new IllegalStateException();
			if(nextCalled)
				previous.data = element;
			if(prevCalled)
				current.data = element;
		}
		
	}

}
