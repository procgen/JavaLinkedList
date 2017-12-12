
public class DLPriorityQueue {

	private DLinkedList list;
	
	public void add(Comparable<Object> element)
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
	
	public Object peek()
	{
		return list.getLast();
	}
	
	public Object remove()
	{
		return list.removeLast();
	}
}
