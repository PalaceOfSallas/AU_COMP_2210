import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * Implementing Class for RandomizedList
 */
public class RL<T> implements RandomizedList<T> {

  /**
   * Instance Variables
   */
   private T[] elements;
   private int rear;
   
  /**
   * Constructor.
   */
   @SuppressWarnings("unchecked")
   public RL() {
      elements = (T[]) new Object[10];
      rear = 0;
   }
   
   /**
    * Returns the number of elements in this list.
    */
   public int size() {
      int count = 0;
      for (T element : elements) {
         if (element != null) {
            count++;
         }
      }
      return count;
   }
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return size() == 0;
   }
   
   /**
    * Creates and returns an iterator over the elements of this list.
    */
   @SuppressWarnings("unchecked")
   public Iterator<T> iterator() {
      return new ArrayIterator(elements, size());
   }
      
   /**
    * Adds the specified element to this list. If the element is null, this
    * method throws an IllegalArgumentException.
    */
   public void add(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
   
      if (rear == elements.length) {
         resize(elements.length * 2);
      }
      
      elements[rear] = element;
      rear++;
   }
  
   /**
    * Selects and removes an element selected uniformly at random from the
    * elements currently in the list. If the list is empty this method returns
    * null.
    */
   public T remove() {
      if (isEmpty())
      {
         return null;
      }
      
      Random generator = new Random();
      int randNum = generator.nextInt(size());
      
      T removed = elements[randNum];
      
      elements[randNum] = elements[rear - 1];
      elements[rear - 1] = null;
      rear--;
      
      return removed;
   }
   
   /**
    * Selects but does not remove an element selected uniformly at random from
    * the elements currently in the list. If the list is empty this method
    * return null.
    */
   public T sample() {
      if (isEmpty())
      {
         return null;
      }
      
      Random generator = new Random();
      int randNum = generator.nextInt(size());
      
      return elements[randNum];
   }
   
  /**
    * Resizes instance variable array if needed
    */
   @SuppressWarnings("unchecked")
   public void resize(int x) {
     // Creating placeholder array
      T[] placeHolder = (T[]) new Object[x];
      
      // Copying elements into placeholder
      int i = 0;
      for (T element : elements) {
         placeHolder[i] = element;
         i++;
      }
      
      // Replacing "elements" with placeHolder
      elements = placeHolder;
   }
   
   /**
    * Iterator Class
    */
   public class ArrayIterator<T> implements Iterator<T> {
      private T[] items;
      private int count;
      private int current;
      
      public ArrayIterator(T[] elements, int size) {
         items = elements;
         count = size;
         current = 0;
      }
      
      public boolean hasNext() {
         return (current < count);
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         return items[current++];
      }
   }

   
   

   

   
   
   
}