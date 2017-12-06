
public interface ListIterator {
	
	public Object next();
	public Object previous();
	
	public boolean hasNext();
	public boolean hasPrevious();
	
	public void add(Object element);
	
	public Object remove();
	
	public void set(Object element);

}
