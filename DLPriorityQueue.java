
public class DLPriorityQueue {

	private DLinkedList list;
	
	/**
	 * Creates a new empty priority queue implemented using a doubly linked list
	 */
	public DLPriorityQueue()
	{
		list = new DLinkedList();
	}
	
	/**
	 * Adds a new element to the priority queue, placing it in the proper spot
	 * @param element Element to store in queue
	 */
	public void add(Comparable element)
	{
		ListIterator iter = list.listIterator();

		while(iter.hasNext())
		{
			Object nextObj = iter.next();
			Comparable<Object> next = (Comparable<Object>)nextObj;
			if(element.compareTo(next) > 0)
			{
				iter.previous();
				iter.add(element);
				return;
			}
		}
		iter.add(element);
	}
	
	/**
	 * Returns the next object in the priority queue
	 * @return Object next object
	 */
	public Object peek()
	{
		return list.getLast();
	}
	
	/**
	 * Removes the next object in the priority queue and returns it
	 * @return Object removed object
	 */
	public Object remove()
	{
		return list.removeLast();
	}
}
