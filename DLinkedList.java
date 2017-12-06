
public class DLinkedList {
	
	private Node first;
	private Node last;
	
	public DLinkedList()
	{
		this.first = null;
		this.last = null;
	}
	
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
	
	private Object removeSingle()
	{
		Object removed = this.first.data;
		this.first = null;
		this.last = null;
		return removed;
	}
	
	public Object getFirst()
	{
		return this.first.data;
	}
	
	public Object getLast()
	{
		return this.last.data;
	}
	
	public LinkedListIterator listIterator()
	{
		return new LinkedListIterator(first);
	}
	
	public String toString()
	{
		String str = "";
		Node curNode = this.first;
		str += curNode.toString();
		while(true)
		{
			if (curNode.next == null)
			{
				break;
			}
			else
			{
				curNode = curNode.next;
				str += curNode.toString();
			}
		}
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
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public boolean hasPrevious() {
			return previous != null;
		}

		@Override
		public void add(Object element) {
			// TODO Auto-generated method stub
			
		}

		@Override
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
			if(current != null)
			{
				current.previous = previous.previous; //The current node is now linked to the node before the previous
				previous = current.previous; //Now the previous iterator node is the same as the previous of the current node
			}
			else
			{
				previous = previous.previous; //If current is null we avoid resolving its pointer
			}
		}
		
		private void removePrevious()
		{
			if(current.next != null)
				current.next.previous = previous;
			if(previous != null)
				previous.next = current.next;
			current = current.next;
		}

		@Override
		public void set(Object element) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
