import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Stephen Sallas (sms0107@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  TODAY
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
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) 
      throws IllegalArgumentException {
      
      if (a == null || a.length == 0) {
         throw new  IllegalArgumentException();
      }
    
      
      int min = a[0];
      
      for (int x : a) {
         if (x < min) {
            min = x;
         }
      }
      
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) 
      throws IllegalArgumentException {
      
      if (a == null || a.length == 0) {
         throw new  IllegalArgumentException();
      }
      
      int max = a[0];
      
      for (int x : a) {
         if (x > max) {
            max = x;
         }
      } 
      
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
   
      if (a == null || a.length == 0) {
         throw new  IllegalArgumentException();
      }
      
      if (k < 1 || k > a.length) {
         throw new  IllegalArgumentException();
      }
      
      if (a.length > 1) {
         if (a[0] == a[1] && k == 2) {
            throw new IllegalArgumentException();
         }
      }
      
      int[] newArray = new int[a.length];
      int c = 0;
      for (int x : a) {
         newArray[c] = x;
         c++;
      }
         
      Arrays.sort(newArray);
      
      int kmin;
      int i = 0;
      int j = 1;
      
      while (j != k) {
         if (j == 1) {
            i++;
            j++;
            while (newArray[i] == newArray[i - 1]) {
               i++;
            }
         }
         else {
            if (newArray[i] == newArray[i - 1]) {
               i++;
            }
            else {
               i++;
               j++;
               while (newArray[i] == newArray[i - 1]) {
                  i++;
               }
            }
         }
      }
       
      kmin = newArray[i];
      return kmin; 
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      
      if (a == null || a.length == 0) {
         throw new  IllegalArgumentException();
      }
      
      if (k < 1 || k > a.length) {
         throw new  IllegalArgumentException();
      }
      
      if (a.length > 1) {
         if (a[0] == a[1] && k == 2) {
            throw new IllegalArgumentException();
         }
      }
      
      int[] newArray = new int[a.length];
      int c = 0;
      for (int x : a) {
         newArray[c] = x;
         c++;
      }
         
      Arrays.sort(newArray);
      
      int[] newArray2 = new int[a.length];
      int d = a.length - 1;
      for (int x : newArray) {
         newArray2[d] = x;
         d--;
      }
         
      
      int kmax;
      int i = 0;
      int j = 1;
      
      while (j != k) {
         if (j == 1) {
            i++;
            j++;
            while (newArray2[i] == newArray2[i - 1]) {
               i++;
            }
         }
         else {
            if (newArray2[i] == newArray2[i - 1]) {
               i++;
            }
            else {
               i++;
               j++;
               while (newArray2[i] == newArray2[i - 1]) {
                  i++;
               }
            }
         }
      }
       
      kmax = newArray2[i];
      return kmax; 
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) 
      throws IllegalArgumentException {
      
      if (a == null || a.length == 0) {
         throw new  IllegalArgumentException();
      }
      
      int length = 0; 
      
      for (int x: a) { 
         if ((x >= low) && (x <= high)) {
            length++;
         }
      }
      
      int[] range = new int[length]; 
      
      int i = 0;  
       
      for (int x: a) { 
         if ((x >= low) && (x <= high)) {
            range[i] = x;
            i++;
         }
      }
             
      return range;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new  IllegalArgumentException();
      }
      
      int ceiling = 9999999; 
      int possibleCeiling = -9999999;
      
      for (int x : a) {
         if (x >= key) {
            possibleCeiling = x;
            if (x < ceiling) {
               ceiling = x;
            }
         }
      }
      
      if (possibleCeiling == -9999999) {
         throw new  IllegalArgumentException();
      }
      
      return ceiling;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new  IllegalArgumentException();
      }
      
      int floor = -9999999;
      int possibleFloor = -9999999; 
      
      for (int x : a) {
         if (x <= key) {
            possibleFloor = x;
            if (x > floor) {
               floor = x;
            }
         }
      }
      
      if (possibleFloor == -9999999) {
         throw new  IllegalArgumentException();
      }
   
      
      return floor;
   }
}
