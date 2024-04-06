import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  YOUR NAME (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T min = null;
      
      for (T y : coll) {
         min = y;
         break;
      }
      
      for (T x : coll) {
         if (comp.compare(x, min) < 0) {
            min = x;
         }
      }
   
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T max = null;
      
      for (T y : coll) {
         max = y;
         break;
      }
      
      for (T x : coll) {
         if (comp.compare(x, max) > 0) {
            max = x;
         }
      }
   
      return max;
   
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> sortedColl = new ArrayList<T>(coll);
      
      if (sortedColl.size() == 1 && k > 1) {
         throw new NoSuchElementException();
      }
      
      if (sortedColl.size() > 1) {
         if (sortedColl.get(0) == sortedColl.get(1) && k == 2) {
            throw new NoSuchElementException();
         }
      }
      
      java.util.Collections.sort(sortedColl, comp);
      
      int i = 0;
      int j = 1;
      
      while (j != k) {
         if (j == 1) {
            i++;
            j++;
            while (sortedColl.get(i) == sortedColl.get(i - 1)) {
               i++;
            }
         }
         else {
            if (sortedColl.get(i) == sortedColl.get(i - 1)) {
               i++;
            }
            else {
               i++;
               j++;
               while (sortedColl.get(i) == sortedColl.get(i - 1)) {
                  i++;
               }
            }
         }
      }
           
      return sortedColl.get(i);
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
       
      ArrayList<T> sortedColl2 = new ArrayList<T>(coll);
      
      if (sortedColl2.size() == 1 && k > 1) {
         throw new NoSuchElementException();
      }
      
      if (sortedColl2.size() > 1) {
         if (sortedColl2.get(0) == sortedColl2.get(1) && k == 2) {
            throw new NoSuchElementException();
         }
      }
     
      
      java.util.Collections.sort(sortedColl2, comp);
      
      ArrayList<T> sortedColl = new ArrayList<T>();
      for (int i = sortedColl2.size() - 1; i >= 0; i--) {
         sortedColl.add(sortedColl2.get(i));
      }
         
      int i = 0;
      int j = 1;
      
      while (j != k) {
         if (j == 1) {
            i++;
            j++;
            while (sortedColl.get(i) == sortedColl.get(i - 1)) {
               i++;
            }
         }
         else {
            if (sortedColl.get(i) == sortedColl.get(i - 1)) {
               i++;
            }
            else {
               i++;
               j++;
               while (sortedColl.get(i) == sortedColl.get(i - 1)) {
                  i++;
               }
            }
         }
      }
           
      return sortedColl.get(i);
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> newColl = new ArrayList<T>();
      
      for (T x : coll) {
         if (comp.compare(x, low) >= 0 && comp.compare(x, high) <= 0) {
            newColl.add(x);
         }
      }
      
      if (newColl.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      return newColl;
          
      
      
      
      
      
      
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T possibleCeiling = null;
      T ceiling = null;
      
      for (T x : coll) {
         if (comp.compare(x, key) >= 0) {
            possibleCeiling = x;
            if (ceiling == null) {
               ceiling = possibleCeiling;
            }
            else if (comp.compare(possibleCeiling, ceiling) < 0) {
               ceiling = possibleCeiling;
            }
         }
      }
      
      if (ceiling == null) {
         throw new NoSuchElementException();
      }
      
      return ceiling;
            
         
   
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T possiblefloor = null;
      T floor = null;
      
      for (T x : coll) {
         if (comp.compare(x, key) <= 0) {
            possiblefloor = x;
            if (floor == null) {
               floor = possiblefloor;
            }
            else if (comp.compare(possiblefloor, floor) > 0) {
               floor = possiblefloor;
            }
         }
      }
      
      if (floor == null) {
         throw new NoSuchElementException();
      }
      
      return floor;
   
   
   }

}
