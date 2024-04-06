import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {
   

   @Test public void minTest1() {
      int array1[] = {2, 8, 7, 3, 4, 8, 2};
      int array2[] = {};
      int array3[] = null;
      
      //Assert.assertEquals(Selector.min(array1), 2);
      //Assert.assertEquals(Selector.min(array2), );
      //Assert.assertEquals(Selector.min(array3), 0);  
   }
   
   @Test public void maxTest1() {
      int array1[] = {2, 8, 7, 3, 4, 8, 2};
      int array2[] = {};
      int array3[] = null;
      
      //Assert.assertEquals(Selector.max(array1), 8);
      //Assert.assertEquals(Selector.min(array2), 0);
      //Assert.assertEquals(Selector.min(array3), 0);  
   }
   
   @Test public void rangeTest1() {
      int array1[] = {2, 8, 7, 3, 4, 8, 2};
      int answer[] = {8, 7, 3, 4, 8};
      int array2[] = {};
      int array3[] = null;
      
      // Assert.assertArrayEquals(Selector.range(array1, 3, 8), answer);
      //Assert.assertEquals(Selector.min(array2), 0);
      //Assert.assertEquals(Selector.min(array3), 0);  
   }
   
   @Test public void kminTest1() {
      int array1[] = {2, 8, 7, 3, 4, 8, 2};
      int array2[] = {7};
      int array4[] = {3,7,3,3,1,9,1,1,1,5};
      int array3[] = null;
      
      Assert.assertEquals(Selector.kmin(array4, 3), 5);
   }
   
   @Test public void kmaxTest1() {
      int array1[] = {5,7};
      int array2[] = {3,7,1,9,5}; 
      
      Assert.assertEquals(Selector.kmax(array2, 1), 9);
   }



}
