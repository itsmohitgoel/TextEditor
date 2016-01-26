package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode();
		tail = new LLNode();
		head.next = tail;
		tail.prev = head;
		size = 0;
		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		// If element is null, then throw NPException
		if(element == null){
			throw new NullPointerException("Add: Null insertion is not allowed");
		}
		LLNode newNode = new LLNode(element);
		
		// If list is empty, re-adjust pointers only.
		if(size == 0){
			newNode.next = head.next;
			newNode.prev = head;
			head.next = newNode;
			tail.prev = newNode;
			
			++size;
			return true;
		}
		
		// If list is not empty
		newNode.prev = tail.prev;
		tail.prev.next = newNode;
		tail.prev = newNode;
		newNode.next = tail;
		
		++size;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(size == 0 || index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Check out of bounds or size");
		}
		LLNode traverseNode = head.next;
		
		for(int i = 0; i < index; i++){
			traverseNode = traverseNode.next;
		}
			
		return (E)traverseNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Check Upper or Lower bounds while inserting "
					+ "element at particular index.");
		}
		
		// If element is null, then throw NPException
		if (element == null) {
			throw new NullPointerException("Add: Null insertion is not allowed");
		}
		
		LLNode<E> newNode = new LLNode<E>(element); 
		
		if(size == 0){
			newNode.next = head.next;
			newNode.prev = tail.prev;
			head.next = newNode;
			tail.prev = newNode;
			
			++size;
			return;
		}
		
		LLNode<E> traversePtr = head.next;
		for (int i = 0; i < index; i++) {
			traversePtr = traversePtr.next;
		}
		
		newNode.next = traversePtr;
		newNode.prev = traversePtr.prev;
		traversePtr.prev.next = newNode;
		traversePtr.prev = newNode;
		
		++size;
		return;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("check bounds while deletion");
		}
		LLNode<E> traversePtr = head.next;
		for (int i = 0; i < index; i++) {
			traversePtr = traversePtr.next;
		}
		traversePtr.prev.next = traversePtr.next;
		traversePtr.next.prev = traversePtr.prev;
		
		--size;
		return traversePtr.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Set: check bounds.");
		}
		if(element == null){
			throw new NullPointerException("Set: Null insertion is not allowed");
		}
		LLNode<E> traversePtr = head.next;
		for (int i = 0; i < index; i++) {
			traversePtr = traversePtr.next;
		}
		E returnData = traversePtr.data;
		traversePtr.data = element;
		return returnData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(){
		this.data = null;
		this.next = null;
		this.prev = null;
	}
}
