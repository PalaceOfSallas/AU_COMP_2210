import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * Implementing Class for RandomizedList
 */
public class DEL<T> implements DoubleEndedList<T> {

 /**
  * Instance Variables
  */
   private Node front;
   private Node rear;
   private int size;
   
  /**
   * Constructor.
   */
   public DEL() {
      front = null;
      rear = null;
      size = 0;
   
   }
   
   /**
    * Returns the number of elements in this list.
    */
   public int size() {
      return size;
   }
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return size == 0;
   }
   
   /**
    * Creates and returns an iterator over the elements of this list.
    */
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }
      
   /**
    * Adds element to the front of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   public void addFirst(T element) {
   
     // Exception Handling
      if (element == null) {
         throw new IllegalArgumentException();
      }
     
      // Creating New Node
      Node n = new Node(element);
      
      // Linking new node to current first node
      // OR if list is empty
      // Linking new node to rear
      if (front != null) {
         front.prev = n;
      }
      else {
         rear = n;
      }
      
      // Linking new node to front   
      n.next = front;
      
      // Linking front to new node
      front = n;
      
      // Increasing Size
      size++;
   }
   
   /**
    * Adds element to the end of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   public void addLast(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      
      Node n = new Node(element);
      
      if (rear != null) {
         rear.next = n;
      }
      else {
         front = n;
      }
         
      n.prev = rear;
      
      rear = n;
      
      size++;
      
   
   }
      
   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    */
   public T removeFirst() {
      if (isEmpty()) {
         return null;
      }
      
      T removed = front.element;
      
      if (size == 1) {
         front = null;
         rear = null;
      }
      else {
         front = front.next;
         front.prev = null;
      }
      
      
      size--;
      
      return removed;
   }
   
   
   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    */
   public T removeLast() {
      if (isEmpty()) {
         return null;
      }
      
      T removed = rear.element;
      
      if (size == 1) {
         front = null;
         rear = null;
      }
      else {
         rear = rear.prev;
         rear.next = null;
      }
      
      size--;
      
      return removed;
   }
   
   /**
    * Node Class
    */
   public class Node {
     // Instance Variables
      private T element;
      private Node next;
      private Node prev;
      
      // Constructor #1
      public Node(T x) {
         element = x;
      }
      
      // Constructor #2
      public Node(T x, Node y) {
         element = x;
         next = y;
      }
      
      // Constructor #3
      public Node(T x, Node y, Node z) {
         element = x;
         next = y;
         prev = z;
      }
   }
   
   public class LinkedIterator implements Iterator<T> {
      private Node current = front;
      
      public boolean hasNext() {
         return current != null;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T result = current.element;
         current = current.next;
         return result;
      }
   }
   
   

   

   
   
   
}